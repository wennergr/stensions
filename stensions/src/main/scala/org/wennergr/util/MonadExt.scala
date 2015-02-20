package org.wennergr.util

import org.wennergr.util.FunctorExt.{FunctorOps, Functor}

object MonadExt {

  trait Monad[M[_]] extends Functor[M] { self =>
    def flatMap[A, B](fa: M[A])(f: A => M[B]): M[B]
  }

  /**
   * These functions will be added into a class that
   * has an implicit conversion to the Monad trait. (Pimp my library pattern)
   *
   * @see org.wennergr.util.OptionExt
   */
  final class MonadOps[M[_], Z](override val self: M[Z])(implicit val M: Monad[M]) extends FunctorOps[M, Z](self) {

    /**
     * Runs function f(a,b) on Monad[A] and Monad[B]
     *
     * Similar to for comprehension
     * for {
     *   a <- fa
     *   b <- fb
     * } yield f(a,b)
     */
    def map2[A, B](fb: M[A])(f: (Z, A) => B): M[B] = M.flatMap(self)(a => M.map(fb)(b => f(a,b)))
  }
}
