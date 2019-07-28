package collections.enums

object MaleNames extends Enumeration {
  val PETER, ALEXANDER, FRANZ, JOSEPH, MIKE, JOSHUA, DARIO, DANIEL, BASTI, DAVID = Value

  def getRandomName: String = {
    scala.util.Random.shuffle(this.values.toList).head.toString
  }
}
