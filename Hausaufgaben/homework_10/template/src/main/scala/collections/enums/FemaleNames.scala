package collections.enums

object FemaleNames extends Enumeration {
  val ALEXA, PETRA, SOPHIE, SOPHIA, PATRIZIA, CAROLINE, MARIA, HILDEGARD, GABRIELE = Value

  def getRandomName: String = {
    scala.util.Random.shuffle(this.values.toList).head.toString
  }
}
