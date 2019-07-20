package kickvote

import java.util.UUID

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import kickvote.FootballMatch.GetState

/**
  * Represents a router for FootballMatch actors.
  */
class Router extends Actor with ActorLogging {
  import Router._

  // maps match-ids to ActorRef
  var matches = Map.empty[MatchId, ActorRef]

  override def receive: Receive = { // TODO 11.2.3
    case NewMatch(team1, team2) =>
      val newMatch = context.actorOf(FootballMatch.props(team1, team2))
      val matchId = MatchId()
      matches = matches updated (matchId, newMatch)
      sender ! matchId
    case MatchEvent(gameId, event) => if(matches.get(gameId).nonEmpty) matches(gameId) ! event //äquivalent zu matches.get(gameId).get
    case GetMatchState(matchId) => if (matches.get(matchId).nonEmpty) matches(matchId) ! GetState(sender) //GetState(matches(matchId))
    // self kann innerhalb eines Aktors benutzt werden, um auf meine eigenen Adresse zuzugreifen!!!
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
}