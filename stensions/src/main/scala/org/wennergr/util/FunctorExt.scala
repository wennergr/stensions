package org.wennergr.util

object FunctorExt {

  trait Functor[M[_]] { self =>
    def map[A, B](fb: M[A])(f: A => B): M[B]
  }

  class FunctorOps[F[_], Z](val self: F[Z])(implicit val F: Functor[F])  {
    def fLift[B](f: Z => B): F[Z] => F[B] = F.map(_)(f)
    def fPair(): F[(Z,Z)] = F.map(self)(x => (x,x))
    def fAs[B](b: B) = F.map(self)(_ => b)
  }

}
