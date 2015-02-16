package org.wennergr.util

import org.scalatest.{Matchers, FreeSpec}

import org.wennergr.util.TryExt._

import scala.util.{Failure, Success, Try}

class TryExtTest extends FreeSpec with Matchers {

  val rt = new RuntimeException("RuntimeException")

  "The eventually method" - {
    "should be executed in case of exception thrown" in {

      var f = false
      Try {
        throw rt
      } eventually {
        f = true
      }

      f should be(true)
    }

    "should be executed in case of normal execution" in {

      var f = false
      Try {
        // Do nothing
      } eventually {
        f = true
      }

      f should be(true)
    }
  }

  "The map2 function" - {
    "should return success(f(a,b)) if both expression a and b are successful" in {

      val expr1 = Try {
        val i = 0; "foo"
      }
      val expr2 = Try {
        val i = 1; "bar"
      }

      expr1.map2(expr2)(_ + _) should be(Success("foobar"))
    }

    "should return failure if either expression a or expression b is a failure" in {
      val expr1 = Try {
        throw rt;
      }
      val expr2 = Try {
        val i = 1; "bar"
      }

      expr1.map2(expr2)(_ + _) should be(Failure(rt))
      expr2.map2(expr1)(_ + _) should be(Failure(rt))
    }
  }
}
