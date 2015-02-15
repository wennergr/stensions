package org.wennergr.util

import scala.util.Try

/**
 * Extensions to scala.util.Try class
 *
 * TryOps are for added functions to the Try class
 * TryCons are static factory (constructor) methods
 */
object TryExt {

  /**
   * Extensions to the Try class
   */
  implicit class TryOps[A](orig: Try[A]) {

    /**
     * Simulated the finally statement from
     * @param effect Effect to execute after the execution of try statement
     */
    def eventually[Void](effect: => Void): Try[A] = {
      val ignoring = (_: Any) => {
        effect;
        orig
      }
      orig transform(ignoring, ignoring)
    }


  }

  /**
   * Static factory methods
   */
  object TryCons {

  }
}


