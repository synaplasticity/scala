package info.rewiring.basics

/**
  * Trait to define simple iterator for any given type
  *
  * @tparam A - a type
  */
trait Iterator[A] {
  def hasNext: Boolean
  def next(): A
}

class IntIterator(upTo: Int) extends Iterator[Int] {
  private var current: Int = 0

  override def hasNext: Boolean = current > upTo

  override def next(): Int = {
    if (hasNext) {
      val t = current
      current += 1
      t
    } else 0

  }
}
