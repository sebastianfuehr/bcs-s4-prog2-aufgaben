package hof

import scala.util.Random

object HigherOrderFunctions {
  // a
  val greater: (Integer, Integer) => Integer = (a, b) => if (a > b) a else b

  val higherFunction: (Integer, Integer, (Integer, Integer) => Integer) => Integer
    = (a, b, fun) => fun(a, b)

  // b
  val product: Integer => (Integer => Integer) = a => {b => a * b}

  // c
  /*
  def square(m: Double) = m * m
  val sq = square
   */
  def square: Double => Double = m => m * m
  val sq = square
  /* Oder:
  def square(m: Double) = m * m
  val sq: Double => Double = square // siehe 2.7 Eta-Expansion
   */

  // d
  val sum: (Int, Int) => Int = (a, b) => a + b

  def main(args: Array[String]): Unit = {
    val a = Random.nextInt(10)
    val b = Random.nextInt(10)
    println(s"Higher number of $a and $b: ${higherFunction(a, b, greater)}")
  }
}
