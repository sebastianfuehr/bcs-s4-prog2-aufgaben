package kickvote

object Kickvote {

  // A player's role on the field
  sealed trait Role
  case object Goalie extends Role
  case object Back extends Role
  case object Midfield extends Role
  case object Forward extends Role

  // A player with a name and a role
  case class Player(name: String, role: Role)
  case class Team(name: String, players: Set[Player])

  /**
    * Common sealed supertype for game events
    */
  sealed trait Event { val minute: Int }
  // The game starts (first event)
  case object FirstHalf extends Event { val minute = 0 }
  // Half time starts
  case class HalfTime(minute: Int) extends Event
  // Second half starts (always at 45')
  case object SecondHalf extends Event { val minute = 45 }
  // the game is over
  case class GameOver(minute: Int) extends Event
  // Goal by a player
  case class Goal(minute: Int, player: Player) extends Event
  // A player receives a yellow card
  case class YellowCard(minute: Int, player: Player) extends Event
  // A player receives a red card
  case class RedCard(minute: Int, player: Player) extends Event
  // A player is exchanged for another player
  case class Substitution(minute: Int, playerIn: Player, playerOut: Player) extends Event

  /**
    * Represents a football game
    *
    * @param team1 team number one
    * @param team2 team number two
    */
  class FootballMatch(team1: Team, team2: Team) {
    var score1 = 0
    var score2 = 0
    var totalTime = 0 // total running time of the game
    var isRunning = false
    var isOver = false // is the game over?
    // team names and players on the team
    var Team(teamName1, playersTeam1) = team1
    var Team(teamName2, playersTeam2) = team2

    def printMatchStats(minute: Int): String = {
      s"[$teamName1 vs. $teamName2 ($minute)]: "
    }

    /**
      * Handles an incoming event and updates the current state
      *
      * @param event the event
      */
    def handleEvent(event: Event): Unit = {
      event match {
        case FirstHalf =>
          println(s"${printMatchStats(FirstHalf.minute)} Die erste Halbzeit hat begonnen.")
          isRunning = true
        case SecondHalf =>
          println(s"${printMatchStats(SecondHalf.minute)} Die zweite Halbzeit hat begonnen.")
          isRunning = true
        case HalfTime(minute) =>
          println(s"${printMatchStats(minute)} Die Halbzeit beginnt.")
          isRunning = false
          totalTime += minute
        case GameOver(minute) =>
          println(s"${printMatchStats(minute)} Das Spiel wurde beendet.")
          isRunning = false
          isOver = true
          totalTime += (minute - 45)
        case Goal(minute, player) =>
          println(s"${printMatchStats(minute)} ${player.name} hat ein Tor geschossen.")
          if (playersTeam1.contains(player)) {
            score1 += 1
          } else {
            score2 += 1
          }
        case YellowCard(minute, player) =>
          println(s"${printMatchStats(minute)} ${player.name} hat eine gelbe Karte bekommen.")
        case RedCard(minute, player) =>
          println(s"${printMatchStats(minute)} ${player.name} hat eine rote Karte bekommen und muss das Spiel verlassen.")
        case Substitution(minute, playerIn, playerOut) =>
          println(s"${printMatchStats(minute)} $playerOut wird durch $playerIn ausgewechselt.")

      }
    }

    override def toString: String = s"$teamName1 $score1 : $teamName2 $score2"
  }

  object FootballMatch {
    type Result = (String, Int, String, Int)

    def apply(team1: Team, team2: Team): FootballMatch = new FootballMatch(team1, team2)

    def unapply(m: FootballMatch): Option[Result] = {
      if (m.isOver) Some(m.teamName1, m.score1, m.teamName2, m.score2)
      else None
    }

    /**
      * Did mexico win the match
      * @param tuple the result tuple (String, Int, String, Int)
      * @return true if mexico won
      */
    def didMexicoWin(tuple: Result): Boolean = {
      tuple match {
        case ("Mexico", score1, _, score2) if score1 > score2 => true
        case (_, score1, "Mexico", score2) if score2 > score1 => true
        case (_, _, _, _) => false
      }
    }
  }

}
