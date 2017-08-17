package info.rewiring.basics

class Decorator(left: String, right: String) {
    def layout[A](x: A) = left + x.toString + right
}


object FunctionTest extends App {
  def apply(f: Int => String, v: Int) = f(v)

  val decorator = new Decorator("[", "]")

  println(apply(decorator.layout, 7))
}