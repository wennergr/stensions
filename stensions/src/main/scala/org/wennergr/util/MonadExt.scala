package org.wennergr.util

import org.wennergr.util.FunctorExt.{FunctorOps, Functor}

object MonadExt {

  trait Monad[M[_]] extends Functor[M] { self =>
    def flatMap[A, B](fa: M[A])(f: A => M[B]): M[B]
  }

  final class MonadOps[M[_], Z](override val self: M[Z])(implicit val M: Monad[M]) extends FunctorOps[M, Z](self) {
    def map2[A, B](fb: M[A])(f: (Z, A) => B): M[B] = M.flatMap(self)(a => M.map(fb)(b => f(a,b)))
  }
}
