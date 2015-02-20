package org.wennergr.util

object FunctorExt {

  trait Functor[M[_]] { self =>
    def map[A, B](fb: M[A])(f: A => B): M[B]
  }

  /**
   * These functions will be added into a class that
   * has an implicit conversion to the Functor trait. (Pimp my library pattern)
   *
   * @see org.wennergr.util.OptionExt
   */
  class FunctorOps[F[_], Z](val self: F[Z])(implicit val F: Functor[F])  {

    /**
     * Lift out the Functor F[Z] from the map operation
     */
    def fLift[B](f: Z => B): F[Z] => F[B] = F.map(_)(f)

    /**
     * Return a pair F[(Z,Z)] by duplicating the value of Z
     * @return
     */
    def fPair(): F[(Z,Z)] = F.map(self)(x => (x,x))

    /**
     * Transforms F[Z] to F[B] no matter what the value of F[Z] is
     */
    def fAs[B](b: B) = F.map(self)(_ => b)
  }

}
