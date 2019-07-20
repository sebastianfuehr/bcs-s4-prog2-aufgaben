package leet

object Leetspeak {

  def main(args: Array[String]): Unit = {
    println(leet("Wikipedia"))
  }

  val l33t = Map(
    "A" -> "4", "B" -> "8", "C" -> "(", "D" -> "|)", "E" -> "3", "F" -> "|=",
    "G" -> "6", "H" -> "#", "I" -> "!", "J" -> "_|", "K" -> "|<", "L" -> "1",
    "M" -> """/\/\""", "N" -> """|\|""", "O" -> "0", "P" -> "9", "Q" -> "0",
    "R" -> "2", "S" -> "5", "T" -> "7", "U" -> "|_|", "V" -> """\/""",
    "W" -> """\/\/""", "X" -> "><", "Y" -> "`/", "Z" -> "z", "Ä" -> "43",
    "Ö" -> "03", "Ü" -> "|_|"
  )

  def leet(s: String): String = s.map(c => {
                                    val temp = l33t.get(c.toUpper.toString)
                                    if (temp.isEmpty) c.toString else temp.get
                                  })
                                  .reduce((acc, c) => acc + c)
  // def leet(s: String): String = s.toUpperCase.flatMap(c => l33t.getOrElse(c.toString, c.toString))
  // getOrElse(x, y) - x ist der key nach dem Gesucht wird. y ist der default value
}
