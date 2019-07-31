package movies

/**
  * Represents a movie
  *
  * @param id a numeric id of a movie
  * @param name the name
  * @param year the release year
  */
class Movie(val id: Int,
            val name: String,
            val year: Int) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Movie]

  override def equals(other: Any): Boolean = other match {
    case that: Movie =>
      (that canEqual this) &&
        id == that.id &&
        name == that.name &&
        year == that.year
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(id, name, year)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }


  override def toString = s"Movie(id=$id, name=$name, year=$year)"
}
