package info.rewiring.basics

import scala.annotation.tailrec

class BasicRecursion {
  // Int is too small, when we feed n as 100 or more. BigInt is a scala class, which supports
  // all Int methods.
  // NOTE: This can still blow up for large number due to stack space
  def factorial(n: BigInt): BigInt = {
    // Factorial for 0 is 1
    if (n == 0 || n ==1) 1
    else n * factorial(n - 1)
  }
}



class TailRecursiveOptimization {
    // rules : There CANNOT be any operation after the recursive call.
    // i.e.: Multiplication by n cannot be done after the call
    // More : http://wiki.c2.com/?TailCallOptimization

  // In this case as *fact is calling itself* as the last call (tail end),
  // this is call *Tail-recursive* optimization.
  // If it was calling another method as the last call, then
  // it's call *tail-call* optimization
  @tailrec
  private def fact(n: BigInt, accumulator: BigInt): BigInt = {
      if (n == 0 || n == 1) accumulator
      else fact(n - 1, n * accumulator)
    }

  def factorial(n: BigInt): BigInt = fact(n, 1)
}

/**
  * USe cases : If a method is doing nothing but calling another method.
  * E.g.: factorial in the previous class
  */
class MethodInMethods {

  def factorial(n: BigInt): BigInt = {
    @tailrec
    def fact(n: BigInt, accumulator: BigInt): BigInt = {
      if (n == 0 || n == 1) accumulator
      else fact(n - 1, n * accumulator)
    }

    fact(n, 1)
  }

}

  object BasicRecursionTest extends App {
    val basicRecursion = new BasicRecursion()
    val tcoRecursion = new TailRecursiveOptimization()
    val methodInMethods = new MethodInMethods()

    println("Tail optimization recursion : ")
    println(tcoRecursion.factorial(5))
    println(tcoRecursion.factorial(100))
    println(tcoRecursion.factorial(1000))
    println(tcoRecursion.factorial(10000))

    println("Method in methods  : ")
    println(methodInMethods.factorial(5))
    println(methodInMethods.factorial(100))
    println(methodInMethods.factorial(1000))
    println(methodInMethods.factorial(10000))

    println("Basic recursion : ")
    println(basicRecursion.factorial(5))
    println(basicRecursion.factorial(100))
    println(basicRecursion.factorial(1000))
    println(basicRecursion.factorial(10000))


  }