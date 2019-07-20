package kickvote
import Kickvote._
import akka.actor.{Actor, ActorLogging, ActorRef, ActorSystem, Props}
import akka.event.LoggingAdapter

/**
  * Represents a football game
  *
  * @param team1 team number one
  * @param team2 team number two
  */
class FootballMatch(team1: Team, team2: Team) extends Actor with ActorLogging { // TODO 11.2.1
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
  /*
  def handleEvent(event: Event) = event match {
    case FirstHalf =>
      isRunning = true
      log("The game has started", FirstHalf.minute)
    case Goal(minute, player@Player(name, _)) if playersTeam1.contains(player) =>
      log(s"Goal for $teamName1 ($name)", minute)
      score1 += 1
    case Goal(minute, player@Player(name, _)) if playersTeam2.contains(player) =>
      log(s"Goal for $teamName2 ($name)", minute)
      score2 += 1
    case HalfTime(minute) =>
      log("Half time", minute)
      isRunning = false
      totalTime += minute
    case SecondHalf =>
      log("Second half started", SecondHalf.minute)
      isRunning = true
    case GameOver(minute) =>
      log("Game is over", minute)
      isRunning = false
      isOver = true
      totalTime += minute - 45
    case YellowCard(minute, Player(name, _)) =>
      log(s"Yellow card for $name", minute)
    case RedCard(minute, Player(name, _)) =>
      log(s"Red card for $name", minute)
    case Substitution(minute, Player(nameIn, _), Player(nameOut, _)) =>
      log(s"Substition: $nameIn for $nameOut", minute)
  }
   */

  def log(msg: String, minute: Int) = {
    super.log.info(s"""[$teamName1] vs. [$teamName2] ($minute)]: $msg""")
  }

  override def receive: Receive = {
    case FirstHalf =>
      isRunning = true
      log("The game has started", FirstHalf.minute)
    case Goal(minute, player@Player(name, _)) if playersTeam1.contains(player) =>
      log(s"Goal for $teamName1 ($name)", minute)
      score1 += 1
    case Goal(minute, player@Player(name, _)) if playersTeam2.contains(player) =>
      log(s"Goal for $teamName2 ($name)", minute)
      score2 += 1
    case HalfTime(minute) =>
      log("Half time", minute)
      isRunning = false
      totalTime += minute
    case SecondHalf =>
      log("Second half started", SecondHalf.minute)
      isRunning = true
    case GameOver(minute) =>
      log("Game is over", minute)
      isRunning = false
      isOver = true
      totalTime += minute - 45
    case YellowCard(minute, Player(name, _)) =>
      log(s"Yellow card for $name", minute)
    case RedCard(minute, playerOut) =>  // TODO 11.2.2 // player@Player(name, _) erlaubt Zugriff auf player und name!
      if (playersTeam1 contains playerOut) playersTeam1 -= playerOut else playersTeam2 -= playerOut
      log(s"Red card for ${playerOut.name}", minute)
    case Substitution(minute, playerIn, playerOut) =>
      if (playersTeam1 contains playerOut) {
        playersTeam1 -= playerOut
        playersTeam1 += playerIn
      } else {
        playersTeam2 -= playerOut
        playersTeam2 += playerIn
      }
      log(s"Substition: ${playerIn.name} for ${playerOut.name}", minute)
    case GetState(replyTo: ActorRef) => replyTo ! State(Team(teamName1, playersTeam1), score1, Team(teamName2, playersTeam2), score2)
  }
}

object FootballMatch {

  case class GetState(replyTo: ActorRef)
  case class State(team1: Team, score1: Int, team2: Team, score2: Int)

  def props(team1: Team, team2: Team): Props = {
   Props(new FootballMatch(team1, team2))
  }

  def mkFormation(name: String, players: Set[Player]): Formation = {
    var verteidiger: Set[Player] = Set.empty
    var stuermer: Set[Player] = Set.empty
    var mittelfeldspieler: Set[Player] = Set.empty
    var torwart: Player = null
    for (player <- players) {
      player match {
        case Player(_, Goalie) => torwart = player
        case Player(_, Back) => verteidiger += player
        case Player(_, Midfield) => mittelfeldspieler += player
        case Player(_, Forward )=> stuermer += player
      }
    }
    Formation(name, torwart, verteidiger, mittelfeldspieler, stuermer)
  }
  /*
  def mkFormation(name: String, players: Set[Player]) = {
    val grupedPlayers = players.groupBy(x => x.role)
    Formation(
      name,
      groupedPlayers(Goalie).head,
      groupedPlayers(Back),
      groupedPlayers(Midfield),
      groupedPlayers(Forward)
    )
  }
   */
}
