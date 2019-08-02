package hof

import scala.util.Random

object HigherOrderFunctions {

  def main(args: Array[String]): Unit = {
    val a: Int = new Random().nextInt(1000)
    val b: Int = new Random().nextInt(1000)
    println(s"Higher number of $a and $b: ${greaterFun(a, b, greater)}")


  }

  val greater: (Int, Int) => Int = (a, b) => if (a > b) a else b

  // Alles zwischen ':' und '=' kann weggelassen werden
  val greaterFun: (Int, Int, (Int, Int) => Int) => Int = (a: Int, b: Int, fun: (Int, Int) => Int) => fun(a, b)

  val product: Int => (Int => Int) = (x: Int) => (y: Int) => x * y

}
