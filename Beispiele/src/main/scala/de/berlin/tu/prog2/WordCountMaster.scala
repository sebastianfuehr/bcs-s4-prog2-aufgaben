package de.berlin.tu.prog2

import java.io.File

import akka.actor.{Actor, ActorRef, Props}

class WordCountMaster extends Actor {

  var fileNames: Seq[String] = Nil
  var sortedCount: Seq[(String, Int)] = Nil

  override def receive = {
    case StartCounting(docRoot, numActors) => val workers = createWorkers(numActors)
      fileNames = scanFiles(docRoot)
      beginSorting(fileNames, workers)
    case WordCount(fileName, count) => sortedCount = sortedCount :+ (fileName, count)
      sortedCount = sortedCount.sortWith(_._2 < _._2)
      if (sortedCount.size == fileNames.size) {
        println("final result " + sortedCount)
        finishSorting
      }
  }

  override def postStop: Unit = {
    println(s"Master is stopped $self")
  }

  private def createWorkers(numActors: Int) = {
    for (i <- 0 until numActors) yield context.actorOf(Props[WordCountWorker], name=s"worker-$i")
  }

  private def scanFiles(docRoot: String) = new File(docRoot).list.map(docRoot + _)

  private[this] def beginSorting(fileNames: Seq[String], workers: Seq[ActorRef]): Unit = {
    fileNames.zipWithIndex.foreach(e => {workers(e._2 % workers.size) ! FileToCount(e._1)})
  }

  private[this] def finishSorting: Unit = context.system.terminate

}
