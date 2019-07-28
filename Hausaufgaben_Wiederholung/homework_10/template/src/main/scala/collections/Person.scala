package collections

import collections.enums.{FemaleNames, MaleNames}

import scala.util.Random

class Person(val name: String, val age: Int, val sex: Int) {

  override def toString(): String = s"$name; sex: $sex; age: $age"

}

object Person {
  def generatePerson(adult: Boolean): Person = {
    val ran: Random = new Random

    val ranMax = if (adult) 82 else 18
    val sex = ran.nextInt(2)
    val age = if (adult) 18 + ran.nextInt(ranMax) else ran.nextInt(ranMax)
    val name: String = if (sex == 0) MaleNames.getRandomName else FemaleNames.getRandomName
    val person = new Person(name, age, sex)
    person
  }
}
