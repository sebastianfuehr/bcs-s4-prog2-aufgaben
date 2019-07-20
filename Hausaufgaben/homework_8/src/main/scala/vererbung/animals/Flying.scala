package vererbung.animals

trait Flying extends Animal {

  val flyMessage: String // durch nicht-zuweisen eines Wertes wird das Feld abstract

  def fly(): Unit = println(flyMessage)

  override def makeNoise(): String = s"${super.makeNoise()} while flying"

}
