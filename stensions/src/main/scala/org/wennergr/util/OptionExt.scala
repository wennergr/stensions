package org.wennergr.util

import org.wennergr.util.MonadExt.{Monad, MonadOps}

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
   * Default implementation of Monad for option
   */
  val optionMonad = new Monad[Option] {
    override def flatMap[A, B](fa: Option[A])(f: A => Option[B]): Option[B] = fa.flatMap(f)
    override def map[A, B](fa: Option[A])(f: A => B): Option[B] = fa.map(f)
  }

  // Attach MonadOps functions to Option[Z]
  implicit def toMonadOps[Z](v: Option[Z]) = new MonadOps[Option,Z](v)(optionMonad)

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
