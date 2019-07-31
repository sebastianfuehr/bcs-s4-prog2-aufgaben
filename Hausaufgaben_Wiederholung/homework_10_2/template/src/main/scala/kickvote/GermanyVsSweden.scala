package kickvote

import kickvote.Router.{MatchEvent, MatchId}

object GermanyVsSweden {
  import kickvote.Kickvote._

  val germanPlayers =
    Set("Kimmich", "Boateng", "Rüdiger", "Hector").map(Player(_, Back)) ++
    Set("Rudy", "Kroos").map(Player(_, Midfield)) ++
    Set("Müller", "Reus", "Draxler", "Werner").map(Player(_, Forward)) +
      Player("Neuer", Goalie)

  val swedishPlayers =
    Set("Augustinsson", "Granqvist", "Lidelöf", "Lustig").map(Player(_, Back)) ++
    Set("Forsberg", "Ekdal", "Larsson", "Claessson").map(Player(_, Midfield)) ++
    Set("Berg", "Toivonen").map(Player(_, Forward)) +
      Player("Olsen", Goalie)

  val events: List[Event] = List(
    FirstHalf,
    Substitution(
      minute = 31,
      playerIn = Player("Gündoğan", Midfield),
      playerOut = germanPlayers.find(_.name == "Rudy").get
    ),
    Goal(32, swedishPlayers.find(_.name == "Toivonen").get),
    HalfTime(45),
    SecondHalf,
    Substitution(
      46,
      playerIn = Player("Gómez", Forward),
      playerOut = germanPlayers.find(_.name == "Draxler").get
    ),
    Goal(48, germanPlayers.find(_.name == "Reus").get),
    YellowCard(52, swedishPlayers.find(_.name == "Ekdal").get),
    YellowCard(71, germanPlayers.find(_.name == "Boateng").get),
    Substitution(
      minute = 74,
      playerIn = Player("Durmaz", Midfield),
      playerOut = swedishPlayers.find(_.name == "Claessson").get
    ),
    Substitution(
      minute = 78,
      playerIn = Player("Guidetti", Forward),
      playerOut = swedishPlayers.find(_.name == "Toivonen").get
    ),
    RedCard(82, germanPlayers.find(_.name == "Boateng").get),
    Substitution(
      minute = 87,
      playerIn = Player("Brandt", Forward),
      playerOut = germanPlayers.find(_.name == "Hector").get
    ),
    Substitution(
      minute = 90,
      playerIn = Player("Thelin", Forward),
      playerOut = swedishPlayers.find(_.name == "Berg").get
    ),
    Goal(95, germanPlayers.find(_.name == "Kroos").get),
    YellowCard(97, Player("Larsson", Midfield)),
    GameOver(98)
  )

  val germany = Team("Germany", germanPlayers)
  val sweden = Team("Sweden", swedishPlayers)

  def mkMatchEvents(matchId: MatchId) =
    events.map(MatchEvent(matchId, _))

}
