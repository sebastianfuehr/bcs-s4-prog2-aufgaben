package vererbung

import vererbung.animals.Duck

object AllLivingThings extends App {

  //def main(args: Array[String]): Unit = {
    var duck: Duck = new Duck("Duck Duckling")
    println(duck.makeNoise())
  //}

}
