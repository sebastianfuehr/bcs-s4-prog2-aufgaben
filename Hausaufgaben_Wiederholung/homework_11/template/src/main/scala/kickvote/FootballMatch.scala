package kickvote
import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import kickvote.FootballMatch.{GetState, State}
import kickvote.Kickvote._

/**
  * Represents a football game
  *
  * @param team1 team number one
  * @param team2 team number two
  */
class FootballMatch(team1: Team, team2: Team) extends Actor with ActorLogging {
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

  // def handleEvent(event: Event) = event match { wird zu
  override def receive = {
    case FirstHalf =>
      isRunning = true
      sendLog("The game has started", FirstHalf.minute)
    case Goal(minute, player@Player(name, _)) if playersTeam1.contains(player) =>
      sendLog(s"Goal for $teamName1 ($name)", minute)
      score1 += 1
    case Goal(minute, player@Player(name, _)) if playersTeam2.contains(player) =>
      sendLog(s"Goal for $teamName2 ($name)", minute)
      score2 += 1
    case HalfTime(minute) =>
      sendLog("Half time", minute)
      isRunning = false
      totalTime += minute
    case SecondHalf =>
      sendLog("Second half started", SecondHalf.minute)
      isRunning = true
    case GameOver(minute) =>
      sendLog("Game is over", minute)
      isRunning = false
      isOver = true
      totalTime += minute - 45
    case YellowCard(minute, Player(name, _)) =>
      sendLog(s"Yellow card for $name", minute)
    case RedCard(minute, player@Player(name, _)) =>
      if (playersTeam1.contains(player)) playersTeam1 -= player
      else playersTeam2 -= player
      /* Alternative Lösung
      case RedCard(minute, Player(name, _)) =>
        if (playersTeam.map(p => p.name).contains(name)) {
          playersTeam1 --= playersTeam1.filter(p => p.name == name)
        } else {
          playersTeam2 --= playersTeam2.filter(p => p.name == name)
        }
          ...
       */
      sendLog(s"Red card for $name", minute)
    case Substitution(minute, playerIn@Player(nameIn, _), playerOut@Player(nameOut, _)) =>
      if (playersTeam1.contains(playerOut)) {
        playersTeam1 -= playerOut
        playersTeam1 += playerIn
      } else {
        playersTeam2 -= playerOut
        playersTeam2 += playerIn
      }
      sendLog(s"Substition: $nameIn for $nameOut", minute)
    case GetState(replyTo: ActorRef) =>
      replyTo ! State(Team(teamName1, playersTeam1), score1, Team(teamName2, playersTeam2), score2)
  }

  def sendLog(msg: String, minute: Int): Unit = {
    log.info(s"""[$teamName1] vs. [$teamName2] ($minute)]: $msg""")
  }
}

object FootballMatch {

  case class GetState(replyTo: ActorRef)
  case class State(team1: Team, score1: Int, team2: Team, score2: Int)

  def props(team1: Team, team2: Team): Props = Props(new FootballMatch(team1, team2))

  def mkFormation(name: String, players: Set[Player]) = {
    val torwart: Player = players.filter(p => p.role == Goalie).head
    val verteidiger: Set[Player] = players.filter(p => p.role == Back)
    val mittelfeld: Set[Player] = players.filter(p => p.role == Midfield)
    val stuermer: Set[Player] = players.filter(p => p.role == Forward)
    Formation(name, torwart, verteidiger, mittelfeld, stuermer)
  }
}
