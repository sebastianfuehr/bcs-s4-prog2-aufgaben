package vererbung.animals

class Penguin(override val name: String) extends Bird (name) with Swimming {

  override val swimMessage: String = "I'm a penguin."

}
