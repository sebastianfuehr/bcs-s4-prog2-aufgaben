package kickvote

import java.util.UUID

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.util.Timeout

import scala.concurrent.Future

/**
  * Represents a router for FootballMatch actors.
  */
class Router extends Actor with ActorLogging {
  import Kickvote._
  import Router._
  import akka.pattern.{ask, pipe}
  import context.dispatcher

  import scala.concurrent.duration._
  implicit val to = Timeout(3.seconds)
  // maps match-ids to ActorRef
  var matches = Map.empty[MatchId, ActorRef]

  override def receive: Receive = {
    case NewMatch(team1, team2) =>
      val matchRef = context.actorOf(FootballMatch.props(team1, team2))
      val mid = MatchId()
      matches = matches.updated(mid, matchRef)
      sender ! mid
    case me@MatchEvent(id, evt) =>
      matches.get(id) match {
        case None =>
          log.error("Unable to forward event {}", me)
        case Some(ref) =>
          ref forward evt
      }
    case GetMatchState(matchId) =>
      matches(matchId) forward FootballMatch.GetState

    case GetAllStates =>
      Future.traverse(context.children) { ref =>
        (ref ? FootballMatch.GetState).mapTo[State]
      } pipeTo sender
  }
}

object Router {
  import Kickvote._

  // Create a new FootballMatch
  case class NewMatch(team1: Team, team2: Team)

  // Creates a new MatchId. Can be used without arguments.
  case class MatchId(id: String = UUID.randomUUID().toString)

  //
  case class MatchEvent(gameId: MatchId, event: Event)

  // Returns the state for the given MatchId
  case class GetMatchState(matchId: MatchId)

  case class GetMatchStates(matchIds: List[MatchId])

  case object GetAllStates
}
