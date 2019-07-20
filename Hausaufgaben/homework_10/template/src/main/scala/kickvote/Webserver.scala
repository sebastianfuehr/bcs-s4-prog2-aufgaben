package kickvote

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.util.Timeout
import kickvote.Router.{GetAllStates, MatchEvent, MatchId, NewMatch}

import scala.concurrent.duration._
import scala.io.StdIn

object Webserver {
  import Kickvote._
  import akka.pattern.ask
  implicit val timeout = Timeout(1.second)

  def main(args: Array[String]): Unit = {
    import scala.concurrent.Future

    implicit val system = ActorSystem ("kickvote-system")
    implicit val actorMaterializer = ActorMaterializer()
    implicit val dispatcher = system.dispatcher

    val routerRef = system.actorOf(Props[Router], name = "router")
    scheduleGameEvents(system, routerRef)
    val routes =
      pathPrefix("matches") {
        pathEndOrSingleSlash {
          get {
            complete { // complete this with a Future[String] (!)
              (routerRef ? GetAllStates)
                  .mapTo[List[Kickvote.State]]
                  .map(x => x.map(
                      state => s"${state.team1.name} ${state.score1} : ${state.score2}, ${state.team2.name}")
                  )
                  .map(x => x.mkString("\n\n"))
              /* Musterlösung
                (routerRef ? GetAllStates)
                  .mapTo[List[State]]
                    .map(games =>
                      val outStrings = for {
                        State(Team(name1, _), score1, Team(name2, _), score2) <- games
                      } yield s"$name1 $score1 : $score2, $name2"
                      outStrings.mkString("\n\n")
                    }
               */
            }
          }
        }
      } ~
      // For testing purposes! Go to http://localhost:8080/hello/something
      pathPrefix("test" / Segment) { hello =>
        pathEndOrSingleSlash {
          get {
            complete {
              Future.successful(s"""Hello $hello""") // Test me
            }
          }
        }
      }

    // Bind
    val bind = Http().bindAndHandle(routes, "localhost", 8080)
    println("running. Press enter to stop!")
    StdIn.readLine()
    bind.flatMap(_.unbind()).onComplete(_ => system.terminate())
  }

  private def scheduleGameEvents(system: ActorSystem, routerRef: ActorRef) = {
    import Kickvote._
    import scala.concurrent.duration._
    implicit val dispatcher = system.dispatcher

    def mkEvents(events: List[Event], matchId: MatchId) =
      events.map(MatchEvent(matchId, _))

    val mexVsGerMatch = NewMatch(GermanyVsMexico.mexico, GermanyVsMexico.germany)
    val gerVsSweMatch = NewMatch(GermanyVsSweden.germany, GermanyVsSweden.sweden)

    val eventualMatchIds =
      (routerRef ? mexVsGerMatch).mapTo[MatchId] zip
        (routerRef ? gerVsSweMatch).mapTo[MatchId]

    // we can use futures like we would use Lists
    // <- means "get the value once the Future completes and then"
    for {
      (mexGerId, sweGerId) <- eventualMatchIds // ask for matchIds
      mexEvents = mkEvents(GermanyVsMexico.events, mexGerId) // create events with matchIds
      sweEvents = mkEvents(GermanyVsSweden.events, sweGerId)
      allSorted = (mexEvents ++ sweEvents).sortBy(_.event.minute) // merge and sort them
      event <- allSorted // for each event
    } system.scheduler.scheduleOnce(
      event.event.minute.millis * 300, routerRef, event
    ) // schedule delivery for a certain point in the future
  }
}
