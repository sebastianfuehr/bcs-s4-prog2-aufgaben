package hof

import scala.util.Random

object TestClass extends App {

  val ran: Random = new Random()

  testHigherNumberFunction
  testHigherNumberFunction
  testProduct
  testProduct
  testSum
  testSum

  def testHigherNumberFunction: Unit = {
    val x = ran.nextInt(100)
    val y = ran.nextInt(100)
    val result = HigherOrderFunctions.higherNumber(x, y, HigherOrderFunctions.greater)
    println(s"Higher number of $x and $y ist $result")
  }

  def testProduct: Unit = {
    val x = ran.nextInt(10)
    val y = ran.nextInt(10)
    val result = HigherOrderFunctions.product(x)(y)
    println(s"Product of $x and $y ist $result")
  }

  def testSum: Unit = {
    val x = ran.nextInt(100)
    val y = ran.nextInt(100)
    val result = HigherOrderFunctions.sum(x, y)
    println(s"Sum of $x and $y ist $result")
  }

}
