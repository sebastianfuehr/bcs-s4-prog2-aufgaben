package movies

import java.util.concurrent.ThreadLocalRandom

import org.ajbrown.namemachine.NameGenerator
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

import scala.collection.JavaConverters._
import org.scalatest.Inspectors._

@RunWith(classOf[JUnitRunner])
class MoviesSpec extends FlatSpec with Matchers with IMDBTop250 {
  import Movies._

  val numUsers = 1000
  val rng = ThreadLocalRandom.current()

  def pickUpToKMovies(k: Int): Set[Int] = {
    val moviesSize = movies.size
    val mvs = for (_ <- 1 to k) yield rng.nextInt(moviesSize)
    mvs.toSet
  }

  val users = new NameGenerator().generateNames(numUsers).asScala
    .zipWithIndex
    .map {
      case (name, i) => new User(i, name.getFirstName, name.getLastName, pickUpToKMovies(rng.nextInt(10)))
    }.toList

  """The IMDB Movielist""" should """contain 250 movies""" in {
    movies should have size 250
  }

  "Movies" should """find all movies released after the year 2000 (included)""" in {
    findAllMoviesAfter2000(movies) should have size 81
  }

  it should "Filter for films with `the` in the title" in {
    moviesStartingWithThe(movies) should have size 64
  }

  it should "compute which user owns the godfather" in {
    val owners = whoOwnsTheGodfather(users, movies)
    owners should not be empty
    forAll(owners) {
      case (i, godFatherId) =>
        movies(godFatherId).name shouldBe Movies.theGodfather
        users(i).moviesIdOwned should contain(godFatherId)
    }
  }
}
