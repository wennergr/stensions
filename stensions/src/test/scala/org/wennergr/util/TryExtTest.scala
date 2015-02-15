package org.wennergr.util

import org.scalatest.{Matchers, FreeSpec}

import org.wennergr.util.TryExt._

import scala.util.Try

class TryExtTest extends FreeSpec with Matchers {

  "The eventually method" - {
    "should be executed in case of exception thrown" in {

      var f = false
      Try {
        throw new RuntimeException("Exception")
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
}
