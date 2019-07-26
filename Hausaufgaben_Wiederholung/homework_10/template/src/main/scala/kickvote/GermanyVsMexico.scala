package kickvote

import kickvote.Router.{MatchEvent, MatchId}

object GermanyVsMexico {
  import kickvote.Kickvote._

  val germanPlayers =
    Set("Kimmich", "Boateng", "Hummels", "Plattenhardt").map(Player(_, Back)) ++
      Set("Khedira", "Kroos").map(Player(_, Midfield)) ++
      Set("Müller", "Özil", "Draxler", "Werner").map(Player(_, Forward)) +
      Player("Neuer", Goalie)

  val mexicanPlayers =
    Set("Gallardo", "Moreno", "Ayala", "Salcedo").map(Player(_, Back)) ++
      Set("Guardado", "Herrera").map(Player(_, Midfield)) ++
      Set("Lozano", "Vela", "Layún", "Hernandez").map(Player(_, Forward)) +
      Player("Ochoa", Goalie)

  val events = List(
    FirstHalf,
    Goal(35, mexicanPlayers.find(_.name == "Lozano").get),
    YellowCard(40, mexicanPlayers.find(_.name == "Moreno").get),
    HalfTime(46),
    SecondHalf,
    Substitution(
      minute = 58,
      playerIn = Player("Álvarez", Back),
      playerOut = mexicanPlayers.find(_.name == "Vela").get
    ),
    Substitution(
      minute = 60,
      playerIn = Player("Reus", Forward),
      playerOut = germanPlayers.find(_.name == "Khedira").get
    ),
    Substitution(
      minute = 66,
      playerIn = Player("Jiménez", Forward),
      playerOut = mexicanPlayers.find(_.name == "Lozano").get
    ),
    Substitution(
      minute = 74,
      playerIn = Player("Márquez", Back),
      playerOut = mexicanPlayers.find(_.name == "Guardado").get
    ),
    Substitution(
      minute = 79,
      playerIn = Player("Gómez", Forward),
      playerOut = germanPlayers.find(_.name == "Plattenhardt").get
    ),
    YellowCard(83, germanPlayers.find(_.name == "Müller").get),
    YellowCard(84, germanPlayers.find(_.name == "Hummels").get),
    Substitution(86,
      playerIn = Player("Brandt", Forward),
      playerOut = germanPlayers.find(_.name == "Werner").get
    ),
    YellowCard(90, mexicanPlayers.find(_.name == "Herrera").get),
    GameOver(94)
  )

  val germany = Team("Germany", germanPlayers)
  val mexico = Team("Mexico", mexicanPlayers)

  def mkMatchEvents(matchId: MatchId) =
    events.map(MatchEvent(matchId, _))
}
