package vererbung.animals

trait Swimming extends Animal {

  val swimMessage: String

  def swim(): Unit = println(swimMessage)

  override def makeNoise(): String = super.makeNoise() + " while swimming"

}
