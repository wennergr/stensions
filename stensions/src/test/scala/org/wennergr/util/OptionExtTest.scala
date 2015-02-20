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

  "The fPair function" - {
    "should create a new cartesian product (a x a)" in {
      Some("foo").fPair() should be(Some( ("foo", "foo") ))
    }

    "should return None if input option is none" in {
      Option[String](null).fPair() should be(None)
    }
  }

  "The fAs function" - {
    "should change a F[A] to a F[B]" in {
      Some("foo").fAs(12) should be(Some(12))
      None.fAs("bar") should be(None)
    }
  }

  "The fLift function" - {
    "should lift out the function f: Try[A] => Try[B]" in {
      val f = Some("oldValue").fLift(_ + "bar")
      f(Some("foo")) should be(Some("foobar"))
      f(None) should be(None)
    }
  }
}
