package collections

import scala.collection.mutable.ListBuffer

object Main extends App {

  val list: ListBuffer[Person] = ListBuffer()

  val amount = 12

  // Generieren der amount Personen
  for (i <- 1 to amount/2) {
    list += Person.generatePerson(true)
  }
  for (i <- 1 to amount/2) {
    list += Person.generatePerson(false)
  }

  val filteredList = list.filter(p => p.age <= 18)

  val avgAge = list.map(p => p.age).reduce((acc, b) => acc + b) / amount
  println(s"Durchschnittsalter von $amount Personen: $avgAge")

  val colors: Map[String, String] = Map("white" -> "#ffffff", "black" -> "#000000")

  // Code-Beispiele
  /*
  foldLeft() ist äquivalent zu reduceLeft(), nur dass ein Startwert (seed value) für den Akkumulator vergeben werden kann.
  foldRight() funktioniert analog. Aber:
  foldLeft((x, y) => (x + y)) entspricht (((x1 + x2) + x3) + x4)
  foldRight((x, y) => (x + y)) entspricht (x1 + (x2 + (x3 + x4)))
  => Für KOMMUTATIVE Operationen ist die Reihenfolge der Ausführung egal (also foldLeft = foldRight)
   */
  // Äquivalenter Ausdruck zu reduce Methode oben
  val newAvgAge = filteredList.map(_.age.toDouble).foldLeft(0.0)((x,y) => x + y) / filteredList.length


  // :: und ::: Methoden auf LISTEN
  /*
  :: fügen die Elemente an den Anfang der Liste an
  ::: fügen eine ganze Liste an den Anfang der Liste an
  z.B.
  val pers = new Person()
  val newList = pers::personList      // Präfix-Notation (gilt für alle "Sonderzeichen-Methoden")
  ODER
  val newList = personList.::(pers)
   */

}
