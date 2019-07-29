package de.berlin.tu.prog2

import java.io.File

import akka.actor.Actor

import scala.io.Source

case class FileToCount(fileName: String)
case class WordCount(filename: String, count: Int)
case class StartCounting(docRoot: String, numActors: Int)

class WordCountWorker extends Actor {

  def countWords(fileName: String) = {
    val dataFile = new File(fileName)
    Source.fromFile(dataFile).getLines.foldRight(0)(_.split(" ").size + _)
  }

  override def receive = {
    case FileToCount(fileName: String) => val count = countWords(fileName)
  }

  override def postStop: Unit = {
    println(s"Worker actor is stopped: $self")
  }

}
