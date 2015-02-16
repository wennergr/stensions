package org.wennergr.util

import org.scalatest.{FreeSpec, Matchers}

import org.wennergr.util.OptionExt._

class OptionExtTest extends FreeSpec with Matchers {

  "The iif method" - {
    "should respect the boolean flag" in {
      OptionCons.iif(true, 4) should be(Some(4))
      OptionCons.iif(false, 4) should be(None)
    }

    "should not create a Some if a is null" in {
      OptionCons.iif(true, null) should be(None)
    }

    "should evaluate using call-by-name" in {
      var f = false
      OptionCons.iif(false, { f = true } ) should be(None)
      f should be(false)
    }
  }

  "The map2 function" - {
    "should return none if Option[A] or Option[B] is not defined" in {
      Some("foo").map2(None)(_ + _) should be(None)
      Option[String](null).map2(Some("foo"))(_ + _) should be(None)
    }

    "should apply function f if Option[A] and Option[B] is Some" in {
      Some("foo").map2(Some("bar"))(_ + _) should be(Some("foobar"))
    }
  }
}
