package vererbung.humans

trait Friend {

  val name: String

  def isFriend(): Unit = { //by default public
    println("I am a Friend")
  }

}
