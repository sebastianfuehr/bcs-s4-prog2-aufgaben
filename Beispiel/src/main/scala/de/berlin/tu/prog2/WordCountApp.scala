package de.berlin.tu.prog2

import akka.actor.{ActorSystem, Props}

object WordCountApp {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("Word-Count-System")
    val m = system.actorOf(Props[WordCountMaster], name = "master")

    m ! StartCounting("./../../", 2)
  }

}
