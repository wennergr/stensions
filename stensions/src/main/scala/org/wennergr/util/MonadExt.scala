package org.wennergr.util

object MonadExt {

  trait Monad[M[_]] { self =>
    def flatMap[A, B](fa: M[A])(f: A => M[B]): M[B]
    def map[A, B](fb: M[A])(f: A => B): M[B]
  }

  final class MonadOps[M[_], Z](val self: M[Z])(implicit val M: Monad[M])  {
    def map2[A, B](fb: M[A])(f: (Z, A) => B): M[B] = M.flatMap(self)(a => M.map(fb)(b => f(a,b)))
  }
}
