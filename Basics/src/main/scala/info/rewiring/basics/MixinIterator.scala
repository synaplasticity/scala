package info.rewiring.basics


abstract class AbstractIterator {
  type T
  def hasNext: Boolean
  def next: T
}

class StringIterator(s: String) extends AbstractIterator {
  type T = Char
  var i = 0

  override def hasNext: Boolean = i < s.length

  override def next: Char = {
    if (hasNext) {
      val ch = s charAt i
      i += 1
      ch
    } else '0'
  }

}

trait RichIterator extends AbstractIterator {
  def foreach(f: T => Unit): Unit = while(hasNext) f(next)
}

