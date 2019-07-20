package vererbung.animals

abstract class Bird(val name: String) extends Animal {

  override def makeNoise(): String = super.makeNoise() + " as a bird"

}
