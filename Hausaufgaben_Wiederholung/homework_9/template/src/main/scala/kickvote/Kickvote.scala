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
    var score1 = 0 // 9.1.3
    var score2 = 0 // 9.1.3
    var totalTime = 0 // total running time of the game - 9.1.2
    var isRunning = false // 9.1.2
    var isOver = false // is the game over?
    // team names and players on the team
    var Team(teamName1, playersTeam1) = team1
    var Team(teamName2, playersTeam2) = team2

    /**
      * Handles an incoming event and updates the current state
      *
      * @param event the event
      */
    def handleEvent(event: Event): Unit = { // 9.1.1, 9.1.2, 9.1.3
      event match {
        case FirstHalf =>
          isRunning = true
          printOutput("Die erste Halbzeit hat begonnen.", 0)
        case HalfTime(minute) =>
          isRunning = false
          totalTime += minute
          printOutput("Die Halbzeit hat begonnen.", minute)
        case SecondHalf =>
          isRunning = true
          printOutput("Die zweite Halbzeit hat begonnen.", 45)
        case GameOver(minute) =>
          isRunning = false
          totalTime += minute - 45
          printOutput("Das Spiel ist beendet.", minute)
        case Goal(minute, player) =>
          if (team1.players.contains(player)) score1 += 1
          else score2 += 1
          printOutput(s"Tor durch ${player.name}.", minute)
        case YellowCard(minute, player) => printOutput(s"Gelbe Karte für ${player.name}.", minute)
        case RedCard(minute, player) => printOutput(s"Rote Karte für ${player.name}.", minute)
        case Substitution(minute, playerIn, playerOut)
        => printOutput(s"${playerIn.name} wird für ${playerOut.name} ausgewechselt.", minute)
        case _ => println("Error. Message not readable!\n handleEvent(event: Event) in Kickvote.scala")
      }
    }

    /**
     * @param message Messag to be printed on screen.
     * @param minute The minute the event happened.
     */
    def printOutput(message: String, minute: Int): Unit = {
      println(s"$teamName1 vs. $teamName2 ($minute): $message")
    }

    override def toString: String = {s"$teamName1 $score1 : $teamName2 $score2"}
  }

  object FootballMatch {
    type Result = (String, Int, String, Int)

    def apply(team1: Team, team2: Team): FootballMatch = { // 9.2.1
      new FootballMatch(team1, team2)
    }

    def unapply(m: FootballMatch): Option[Result] = { // 9.2.1
      if (!m.isRunning) Some(new Result(m.teamName1, m.score1, m.teamName2, m.score2))
      else None
    }

    /**
      * Did mexico win the match
      * @param tuple the result tuple (String, Int, String, Int)
      * @return true if mexico won
      */
    def didMexicoWin(tuple: Result): Boolean = { // 9.2.2
      tuple match {
        case ("Mexico", scoreM, _, score2) => scoreM > score2
        case (_, score2, "Mexico", scoreM) => scoreM > score2
        case _ => false
      }
    }
  }
}