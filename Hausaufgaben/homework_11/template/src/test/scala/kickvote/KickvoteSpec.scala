package kickvote

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKit}
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class KickvoteSpec extends TestKit(ActorSystem("KickvoteTestSystem")) with ImplicitSender
  with FlatSpecLike with Matchers with BeforeAndAfterAll {
  import kickvote.Kickvote._

  override protected def afterAll(): Unit = {
    TestKit.shutdownActorSystem(system)
  }

  """The mkFormation method""" should """compute the formation""" in {
    val p: Product = FootballMatch.mkFormation("Mexico", GermanyVsMexico.mexicanPlayers)
    p.productElement(0) shouldBe "Mexico"
    p.productElement(1).asInstanceOf[Player] shouldBe Player("Ochoa", Goalie)
    p.productElement(2).asInstanceOf[Set[_]] should have size 4
    p.productElement(3).asInstanceOf[Set[_]] should have size 2
    p.productElement(4).asInstanceOf[Set[_]] should have size 4
  }

  """The FootballMatch Actor""" should """provide the right State""" in {
    import FootballMatch._
    import scala.concurrent.duration._
    val ger = Team("Germany", GermanyVsSweden.germanPlayers)
    val swe = Team("Sweden", GermanyVsSweden.swedishPlayers)
    val gerVsSweRef = system.actorOf(FootballMatch.props(ger, swe))
    for (e <- GermanyVsSweden.events) {
      expectNoMessage(e.minute.millis)
      gerVsSweRef ! e
    }
    gerVsSweRef ! GetState(self)
    val State(team1, score1, team2, score2) = expectMsgType[FootballMatch.State]
    team1.players.map(_.name) should not contain "Boateng"
    team2.players.map(_.name) should contain ("Durmaz")
    score1 shouldBe 2
    score2 shouldBe 1
  }

  """The router""" should """route events to the right actors""" in {
    import FootballMatch._
    import Kickvote._
    import Router._
    import scala.concurrent.duration._

    val routerRef = system.actorOf(Props[Router], name = "router")
    routerRef ! NewMatch(GermanyVsMexico.germany, GermanyVsMexico.mexico)
    val gerVsMexId = expectMsgType[MatchId]
    val gerVsMexGameEvents =
      for(event <- GermanyVsMexico.events)
        yield MatchEvent(gerVsMexId, event)
    routerRef ! NewMatch(GermanyVsSweden.sweden, GermanyVsSweden.germany)
    val sweVsGerId = expectMsgType[MatchId]
    val sweVsGerEvents =
      for(event <- GermanyVsSweden.events)
        yield MatchEvent(sweVsGerId, event)

    val allEvents =
      (gerVsMexGameEvents ++ sweVsGerEvents).sortBy(_.event.minute)

    for(e <- allEvents) {
      expectNoMessage(e.event.minute.millis)
      routerRef ! e
    }

    routerRef ! GetMatchState(gerVsMexId)
    val result = expectMsgType[State]
    result.team1.name shouldBe "Germany"
    result.score1 shouldBe 0
    result.team2.name shouldBe "Mexico"
    result.score2 shouldBe 1
    routerRef ! GetMatchState(sweVsGerId)
    val result2 = expectMsgType[State]
    result2 should matchPattern {
      case State(Team("Sweden", _), 1, Team("Germany", _), 2) =>
    }
    result2.team1.name shouldBe "Sweden"
    result2.score1 shouldBe 1
    result2.team2.name shouldBe "Germany"
    result2.score2 shouldBe 2
  }
}
