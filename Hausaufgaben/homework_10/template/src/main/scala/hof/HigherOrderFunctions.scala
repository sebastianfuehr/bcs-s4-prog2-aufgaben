package hof

object HigherOrderFunctions {

  // TODO Frage: Wann benutzt man val/var und wann def für Funktionen höherer Ordnung?
  // -Methoden können nicht an Funktionen höherer Ordnung übergeben oder
  //  val-/var-Referenzen zugewiesen werden
  // => Methoden können in Funktionen umgewandelt werden!

  val greater: (Int, Int) => Int // Zwei Integer bilden auf einen Integer ab
                = (x, y) => if (x > y) x else y // Funktionsliteral

  def higherNumber: (Int, Int, (Int, Int) => Int) => Int
                = (x, y, op) => op(x,y)

  def product: Int => Int => Int
                = x => {y => x * y} // Funktion als Rückgabetyp // = x => x * _

  def square(m: Double) = m * m
  val sq: Double => Double = square // siehe 2.7 Eta-Expension

  val sum: (Int, Int) => Int = (x, y) => x + y

}
