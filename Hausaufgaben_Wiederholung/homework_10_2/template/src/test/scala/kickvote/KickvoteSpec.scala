package kickvote

import akka.actor.{ActorSystem, Props}
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

  """The Router""" should """supervise""" in {
    import Router._
    val routerRef = system.actorOf(Props[Router])
    val newMatch = NewMatch(GermanyVsSweden.germany, GermanyVsSweden.sweden)
    routerRef ! newMatch
    val matchId = expectMsgType[MatchId]
    for(e <- GermanyVsSweden.events) routerRef ! MatchEvent(matchId, e)
    routerRef ! MatchEvent(matchId, Goal(35, null)) // Crash
    routerRef ! GetMatchState(matchId)
    val State(_, score1, _, score2) = expectMsgType[State]
    score1 shouldBe 2
    score2 shouldBe 1
  }
}
