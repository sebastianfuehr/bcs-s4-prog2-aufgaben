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
    var score1 = 0 // TODO: 9.1.3
    var score2 = 0 // TODO: 9.1.3
    var totalTime = 0 // total running time of the game
    var isRunning = false // TODO: 9.1.2
    var isOver = false // is the game over?
    // team names and players on the team
    var Team(teamName1, playersTeam1) = team1
    var Team(teamName2, playersTeam2) = team2

    /**
      * Handles an incoming event and updates the current state
      *
      * @param event the event
      */
    def handleEvent(event: Event): Unit = // TODO: 9.1.1
  }

  object FootballMatch {
    type Result = (String, Int, String, Int)

    def apply(team1: Team, team2: Team): FootballMatch = // TODO: 9.2.1

    def unapply(m: FootballMatch): Option[Result] = // TODO: 9.2.1

    /**
      * Did mexico win the match
      * @param tuple the result tuple (String, Int, String, Int)
      * @return true if mexico won
      */
    def didMexicoWin(tuple: Result): Boolean = // TODO: 9.2.2
  }

}
