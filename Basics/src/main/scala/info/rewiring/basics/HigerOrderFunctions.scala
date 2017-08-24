package info.rewiring.basics

class Decorator(left: String, right: String) {
    def layout[A](x: A) = left + x.toString + right
}


object FunctionTest extends App {
  def apply(f: Int => String, v: Int) = f(v)

  val decorator = new Decorator("[", "]")

  println(apply(decorator.layout, 7))

  // This will work too as an method named *apply* will be invoked
  // if the Object name is used (both Scala Object and objects created
  // from classes.
  // This concept is widely used in the Scala language
  println(FunctionTest(decorator.layout, 7))
}