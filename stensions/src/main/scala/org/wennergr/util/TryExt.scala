package org.wennergr.util

import org.wennergr.util.MonadExt.{Monad, MonadOps}

import scala.util.Try

/**
 * Extensions to scala.util.Try class
 *
 * TryOps are for added functions to the Try class
 * TryCons are static factory (constructor) methods
 */
object TryExt {

  /**
   * Default implementation of Monad for try
   */
  val tryMonad = new Monad[Try] {
    override def flatMap[A, B](fa: Try[A])(f: A => Try[B]): Try[B] = fa.flatMap(f)
    override def map[A, B](fa: Try[A])(f: A => B): Try[B] = fa.map(f)
  }

  // Attach MonadOps functions to Option[Z]
  implicit def toMonadOps[Z](v: Try[Z]) = new MonadOps[Try,Z](v)(tryMonad)

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


