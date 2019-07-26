package kickvote
import java.io.IOException

import Kickvote._
import akka.actor.{Actor, ActorLogging, ActorRef, Props}

/**
  * Represents a football game
  *
  * @param team1 team number one
  * @param team2 team number two
  */
class FootballMatch(team1: Team, team2: Team) extends Actor with ActorLogging {
  import FootballMatch._
  var score1 = 0 // score of team 1
  var score2 = 0 // score of team 2
  var totalTime = 0 // total running time of the game
  var isRunning = false // is the game currently running?
  var isOver = false // is the game over?
  // team names and players on the team
  val teamName1 = team1.name
  val teamName2 = team2.name
  var playersTeam1 = team1.players
  var playersTeam2 = team2.players

  override def receive: Receive = {
    case FirstHalf =>
      isRunning = true
      log.info("The game has started")
    case Goal(minute, player@Player(name, _)) if playersTeam1.contains(player) =>
      log.info("({}) Goal by {} ({}) ", minute, name, teamName1)
      score1 += 1
    case Goal(minute, player@Player(name, _)) if playersTeam2.contains(player) =>
      log.info("({}) Goal by {} ({})", minute, name, teamName2)
      score2 += 1
    case Goal(_, player) =>
      log.error(s"{} is unknown", player.name)
    case HalfTime(minute) =>
      log.info("({}) Half time", minute)
      isRunning = false
      totalTime += minute
    case SecondHalf =>
      log.info("({}) Second half started", SecondHalf.minute)
      isRunning = true
    case GameOver(minute) =>
      log.info("({}) Game is over", minute)
      isRunning = false
      isOver = true
      totalTime += minute - 45
    case YellowCard(minute, Player(name, _)) =>
      log.info("({}) Yellow card for {}", minute, name)
    case RedCard(minute, p@Player(name, _)) if playersTeam1.contains(p) =>
      playersTeam1 -= p
      log.info("({}) Red card for {}", minute, name)
    case RedCard(minute, p@Player(name, _)) if playersTeam2.contains(p) =>
      playersTeam2 -= p
      log.info("({}) Red card for {}", minute, name)
    case RedCard(_, player) =>
      log.error("Unable to find player: {}", player)
    case Substitution(minute, playerIn @ Player(nameIn, _), playerOut @ Player(nameOut, _))
      if playersTeam1.contains(playerOut) =>
      playersTeam1 = (playersTeam1 - playerOut) + playerIn
      log.info("({}) Substitution ({}): {} for {}", minute, teamName1, nameIn, nameOut)
    case Substitution(minute, playerIn @ Player(nameIn, _), playerOut @ Player(nameOut, _))
      if playersTeam2.contains(playerOut) =>
      playersTeam2 = (playersTeam2 - playerOut) + playerIn
      log.info("({}) Substitution ({}): {} for {}", minute, teamName2, nameIn, nameOut)
    case Substitution(_, playerIn, playerOut) =>
      log.error("Unable to perform substitution: in: {}, out: {}", playerIn, playerOut)
    case GetState =>
      sender() ! State(
        Team(teamName1, playersTeam1), score1,
        Team(teamName2, playersTeam2), score2
      )
  }
}

object FootballMatch {
  case object GetState

  def props(team1: Team, team2: Team): Props = Props(new FootballMatch(team1, team2))

  // TODO remove
  case class Formation(name: String,
                       goalie: Player,
                       defense: Set[Player],
                       midfield: Set[Player],
                       forward: Set[Player]
                      )

  def mkFormation(name: String, players: Set[Player]): Formation = {
    val roleToPlayers = players.groupBy(_.role)
    val goalies = roleToPlayers(Goalie)
    Formation(name,
      goalies.head,
      roleToPlayers(Back),
      roleToPlayers(Midfield),
      roleToPlayers(Forward))
  }
}
