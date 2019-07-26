package kickvote

import leet.Leetspeak
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class LeetSpec extends FlatSpec with Matchers {
  """Leetspeak""" should """compute leet results""" in {
    Leetspeak.leet("""leetspeak""") shouldBe "13375934|<"
    Leetspeak.leet("""Have you tried turning it off and on again?""") shouldBe """#4\/3 `/0|_| 72!3|) 7|_|2|\|!|\|6 !7 0|=|= 4|\||) 0|\| 464!|\|?"""
  }
}
