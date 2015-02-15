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
}
