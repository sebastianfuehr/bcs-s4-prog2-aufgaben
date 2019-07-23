package movies

/**
  * Represents a user
  *
  * @param id the user's id
  * @param birthName the user's birth name ("first name")
  * @param familyName the user's family name ("last name")
  * @param moviesIdOwned list of movie ids, the user owns
  */
class User(val id: Int,
           val birthName: String,
           val familyName: String,
           val moviesIdOwned: Set[Int]) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[User]

  override def equals(other: Any): Boolean = other match {
    case that: User =>
      (that canEqual this) &&
        id == that.id &&
        birthName == that.birthName &&
        familyName == that.familyName &&
        moviesIdOwned == that.moviesIdOwned
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(id, birthName, familyName, moviesIdOwned)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }


  override def toString = s"User(id=$id, birthName=$birthName, lastName=$familyName, movieListIds=$moviesIdOwned)"
}