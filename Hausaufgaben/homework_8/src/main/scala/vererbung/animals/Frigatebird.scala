package vererbung.animals

class Frigatebird(override val name: String) extends Bird(name) with Flying {

  override val flyMessage: String = s"Frigatebird $name has something for you."

  override def fly(): Unit = println(s"$flyMessage")

}
