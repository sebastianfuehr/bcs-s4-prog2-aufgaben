package vererbung

class Humans {}

trait Colleague {
  val name: String
  def isColleague: Boolean
}

class Human(override val name: String) extends Colleague with Friend {
  override def isColleague: Boolean = true
  override def isFriend: Boolean = true
}

class Man(name: String) extends Human(name) {}

class Woman(name: String) extends Human(name) {}