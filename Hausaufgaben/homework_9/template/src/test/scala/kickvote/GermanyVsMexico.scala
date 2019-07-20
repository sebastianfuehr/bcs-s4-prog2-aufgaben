package kickvote

trait GermanyVsMexico {
  import kickvote.Kickvote._
  val germanyPlayers =
    Set("Kimmich", "Boateng", "Hummels", "Plattenhardt").map(Player(_, Back)) ++
      Set("Khedira", "Kroos").map(Player(_, Midfield)) ++
      Set("Müller", "Özil", "Draxler", "Werner").map(Player(_, Forward)) +
      Player("Neuer", Goalie)

  val mexicoPlayers =
    Set("Gallardo", "Moreno", "Ayala", "Salcedo").map(Player(_, Back)) ++
      Set("Guardado", "Herrera").map(Player(_, Midfield)) ++
      Set("Lozano", "Vela", "Layún", "Hernandez").map(Player(_, Forward)) +
      Player("Ochoa", Goalie)

  val germanyVsMexicoEvents = List(
    FirstHalf,
    Goal(35, mexicoPlayers.find(_.name == "Lozano").get),
    YellowCard(40, mexicoPlayers.find(_.name == "Moreno").get),
    HalfTime(46),
    SecondHalf,
    Substitution(
      minute = 58,
      playerIn = Player("Álvarez", Back),
      playerOut = mexicoPlayers.find(_.name == "Vela").get
    ),
    Substitution(
      minute = 60,
      playerIn = Player("Reus", Forward),
      playerOut = germanyPlayers.find(_.name == "Khedira").get
    ),
    Substitution(
      minute = 66,
      playerIn = Player("Jiménez", Forward),
      playerOut = mexicoPlayers.find(_.name == "Lozano").get
    ),
    Substitution(
      minute = 74,
      playerIn = Player("Márquez", Back),
      playerOut = mexicoPlayers.find(_.name == "Guardado").get
    ),
    Substitution(
      minute = 79,
      playerIn = Player("Gómez", Forward),
      playerOut = germanyPlayers.find(_.name == "Plattenhardt").get
    ),
    YellowCard(83, germanyPlayers.find(_.name == "Müller").get),
    YellowCard(84, germanyPlayers.find(_.name == "Hummels").get),
    Substitution(86,
      playerIn = Player("Brandt", Forward),
      playerOut = germanyPlayers.find(_.name == "Werner").get
    ),
    YellowCard(90, mexicoPlayers.find(_.name == "Herrera").get),
    GameOver(94)
  )
}
