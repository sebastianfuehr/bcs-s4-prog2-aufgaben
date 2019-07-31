package vererbung

class Humans {}

trait Colleague {
  val name: String
  def isColleague(): Boolean
}

class Human(val name: String) extends Colleague with Friend {
  def isColleague(): Boolean = true
  def isFriend(): Boolean = true
}

class Man(override val name: String) extends Human(name) {}

class Woman(override val name: String) extends Human(name) {}