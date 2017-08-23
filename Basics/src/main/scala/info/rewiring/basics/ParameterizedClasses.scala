package info.rewiring.basics

case class Box[T](t: T) {

  /**
    *
    * Returns a Box containing a Couple
    *
    * @param u
    * @tparam U
    * @return
    */
  def coupleWith[U](u: U): Box[Couple[T, U]] = Box(Couple(t, u))
}

case class Couple[A, B](first: A, second: B) {

  /**
    * NOTE: We do not need to declare any new TYPES at the method level as
    * they have been already declared at the Class level
    *
    * @return
    */
  def swap: Couple[B, A] = Couple(second, first)
}
