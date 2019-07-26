package vererbung

class Animals {}

abstract class Animal {
  def makeNoise: String = "I am an animal which "
}

class Dog(override val name: String) extends Animal with Friend {
  override def isFriend = true
  override def makeNoise = "Wuff"
}

class Cat extends Animal {
  override def makeNoise = "Miau"
}

trait Flying extends Animal {
  val flyMessage: String
  def fly

  override def makeNoise: String = super.makeNoise + "flies, "
}

abstract class Bird(name: String) extends Animal {
  override def makeNoise: String = super.makeNoise + "is a bird, "
}

trait Swimming extends Animal {
  val swimMessage: String
  def swim

  override def makeNoise: String = super.makeNoise + "swims, "
}

class Frigatebird(name: String) extends Bird(name) with Flying {
  override val flyMessage: String = "flying"
  override def makeNoise: String = "frigatebird noise"
  override def fly: Unit = println(flyMessage)
}

class Duck(name: String) extends Bird(name) with Flying with Swimming {
  override val flyMessage: String = "flying"
  override val swimMessage: String = "swimming"
  override def makeNoise: String = super.makeNoise + "but most importantly, I'm a duck!"
  override def fly: Unit = println(flyMessage)
  override def swim: Unit = println(swimMessage)
}

class Ostrich(name: String) extends Bird(name) {
  override def makeNoise: String = "ostrich noise"
}

class Penguin(name: String) extends Bird(name) with Swimming {
  override val swimMessage: String = "swimming"
  override def makeNoise: String = "penguin noise"
  override def swim: Unit = println(swimMessage)
}
