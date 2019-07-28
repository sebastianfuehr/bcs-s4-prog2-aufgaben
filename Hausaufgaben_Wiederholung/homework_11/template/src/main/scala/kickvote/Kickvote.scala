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


}
