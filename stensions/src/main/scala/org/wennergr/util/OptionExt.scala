package org.wennergr.util

/**
 * Extensions to scala.util.Option class
 *
 * OptionOps are for added functions to the Option class
 * OptionCons are static factory (constructor) methods
 */
object OptionExt {

  /**
   * Extension to the Option class
   */
  implicit class OptionOps[A](orig: Option[A]) {

  }

  /**
   * Static factory methods
   */
  object OptionCons {

    /**
     * Creates an option based on a predicate
     */
    def iif[A](p: Boolean, a: => A): Option[A] = p match {
      case true   => Option(a)
      case false  => None
    }
  }
}
