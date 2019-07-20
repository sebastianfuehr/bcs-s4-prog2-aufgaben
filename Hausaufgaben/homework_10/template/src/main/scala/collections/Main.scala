package collections

import collections.enums.MaleNames

import scala.util.Random

object Main extends App {

  val NUMBEROFPERSONS: Int = 20 // # generierte Personen

  val ran: Random = new Random()

  var list: List[Person] = List() // !Muss als Variable definiert sein!
  for (x <- 1 to NUMBEROFPERSONS) {
    //println(Person.generatePerson(true).toString())
    val adult: Boolean = if (ran.nextInt(2) == 1) true else false
    list :+= Person.generatePerson(adult) // prepend und speichern in list
  }


  // Aufgabe: Listen
  println(list.length)
  var filteredList = list.filter((p: Person) => p.age < 18) // alle Minerjährigen zurückgeben
  var filteredListShort = list.filter(_.age < 18) // TODO -> kürzere ALTERNATIVE
  filteredList.foreach((p: Person) => println(p.toString()))
  for (x <- 1 to 10) filteredList :+= Person.generatePerson(false)

  val avgAge = filteredList.map(p => p.age.toDouble).reduce((x, y) => (x+y)) / filteredList.length
  // TODO ODER filteredList.map(_.age.toDouble).sum / filteredList.length
  println(s"Durchschnittsalter: $avgAge")
  var sum = 0.0
  for (x <- filteredList) sum += x.age.toDouble
  if (avgAge != (sum/filteredList.length)) println(s"Durchschnittsalter ist falsch! Richtig: ${sum/filteredList.length}")


  // Aufgabe: Maps
  val colors = Map("red" -> 0xFF0000, // oder "#FF0000"
                   "tured" -> 0xC50F1E,
                   "green" -> 0x008000,
                   "yellow" -> 0xFFFF00,
                   "blue" -> 0x0000FF,
                   "white" -> 0xFFFFFF,
                   "black" -> 0x000000,
                   "orange" -> 0xFFA500,
                   "brown" -> 0xA52A2A,
                   "gray" -> 0x808080,
                   "pink" -> 0xFFC0CB)


  // Methoden über Methoden
  /*
  foldLeft() ist äquivalent zu reduceLeft(), nur dass ein Startwert (seed value) für den Akkumulator vergeben werden kann.
  foldRight() funktioniert analog. Aber:
  foldLeft((x, y) => (x + y)) entspricht (((x1 + x2) + x3) + x4)
  foldRight((x, y) => (x + y)) entspricht (x1 + (x2 + (x3 + x4)))
  => Für KOMMUTATIVE Operationen ist die Reihenfolge der Ausführung egal (also foldLeft = foldRight)
   */
  // Äquivalenter Ausdruck zu reduce Methode oben
  val newAvgAge = filteredList.map(_.age.toDouble).foldLeft(0.0)((x,y) => x + y) / filteredList.length
  if (newAvgAge != sum/filteredList.length) println(s"Durchschnittsalter ist falsch! Richtig: ${sum/filteredList.length}")


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
