package info.rewiring.basics

/**
  *
  * Scala impl example 1 + 2 which is 1.+(2)
  *
  * @param x - Int
  */
class Foo(x: Int) {


  def addA(y: Int): Int = x + y

  def addTwo(a: Int, b: Int): Int = x + a + b

  def alsoAdd(i: Int) = new Foo(x + i)

  /**
    * If a method name ends in ":", then we *have to use* it by right associative colon's if using
    * infix operators
    * E.g.: Typically invoked as foo ~: 5 (compile error in this case)
    *       But in this case it MUST be invoked as 5 ~: foo
    *
    * * Used mostly in List and streaming operations
    *
    * @param z - Int
    * @return
    */
  def ~:(z: Int): Int = x + z
}
