package movies

object Movies {
  /**
    * Example: Returns a list of users with long names
    *
    * @param users
    * @return
    */
  def allLongUsernames(users: List[User]): List[String] = for {
    u <- users
    name = s"${u.birthName} ${u.familyName}"
    if name.length > 20
  } yield name

  /**
    * Find all movies that were released after the year 2000 (including)
    *
    * @param movies the list of movies
    * @return a list of movies that were released after the year 2000
    */
  def findAllMoviesAfter2000(movies: List[Movie]): List[String] = ???

  /**
    * Return all movies with titles starting with "The"
    *
    * @param movies
    * @return
    */
  def moviesStartingWithThe(movies: List[Movie]): List[String] = ???

  val theGodfather = "The Godfather"
  /**
    * Which users own the movie "The Godfather"?
    *
    * @param users a list of users
    * @param movies a list of movies
    * @return a List of Pairs of the user Id and the godfathers's movie Id
    */
  def whoOwnsTheGodfather(users: List[User], movies: List[Movie]): List[(Int, Int)] = ???
}
