package info.rewiring.basics

import org.scalatest.FunSpec

class BasicFunctionTests extends FunSpec {


  describe("Basic function tests") {

    describe("Different ways to declare a function tests") {

      it("should return same value irrespective of the way functions are declared") {

        assert(BasicFunctionTests.basicF1(2) === 3)
        assert(BasicFunctionTests.intermediateF1(2) === 3)
        assert(BasicFunctionTests.finalF1(2) === 3)
        assert(BasicFunctionTests.afterTypeInferenceF1(2) === 3)
      }


    }

    describe("Using tuples to return more than one value from functions") {

      it("should return the string and it's size in a tuple") {
        assert(BasicFunctionTests.stringAndSize("Hello") === ("Hello", 5))
      }
      
    }

    describe("Functions vs methods tests") {

      it("One could use the apply method to differentiate a function vs method invocation. " +
        "A function is an object, where as a method is part of a class/object") {

        assert(BasicFunctionTests.addOneF.apply(4) === BasicFunctionTests.addOne(4))
        assert(BasicFunctionTests.addOneF(4) === BasicFunctionTests.addOne(4)) // They look at the same,
                                                                      // but the first one is a function
      }
    }

    describe("Converting methods as function") {

      it("should return the same value after converting a def to a function") {
        val foo = new FooBaz(10)
        val f = foo.add _ // *partially applied functions* - creates a function f, which invokes foo.add

        assert(foo.add(20) === f(20))
        assert(foo.add(20) === f.apply(20))
      }

      it("should return the same value while using a high order function") {
        val foo = new FooBaz(10)
        val f = foo.add _ // creates a function f, which invokes foo.add
        val baz = new Baz(20)

        assert(f(20) === baz.qux(f)) // 20 + 10 from foo
        assert(f(20) === baz.qux(foo.add _)) // 20 + 10 from foo
        assert(f(20) === baz.qux(foo.add)) // Can ignore the trailing "_"
      }

    }

    describe("Closure tests - functions that close around the environment") {
      it("should return the correct value even though one of the inputs in the caller scope") {
        val m = 200 // NOTE: This could be VAR, but can have SIDE EFFECTS as m changes.
        val f = (x: Int) => x + m

        val closure = new Closure(100)

        assert(closure.bar(f) === 300) // NOTE: m is in the caller scope, but f will have access to it
      }

    }

    describe("Function with functions tests") {
      it("should return correct value for different syntatic sugars for func. with func. - Higher order functions") {
        val x: Int = FunctionWithFunctions.f(2, (m: Int) => m + 1)
        val y: Int = FunctionWithFunctions.f(2, m => m + 1) // as we know the type of m is Int
                                                            // from the function def
        val z: Int = FunctionWithFunctions.f(2, _ + 1) // Can replace the var m with placeholder
        val a: Int = FunctionWithFunctions.f(2, 1 + _) // As addition is commutative

        import scala.language.postfixOps // required to suppress warning for the trialing +
        val b: Int = FunctionWithFunctions.f(2, 1+) // As we can ignore the trailing _

        assert(x === y)
        assert(y === z)
        assert(z === a)
        assert(a === b)
      }

      it("should return a closure - currying") {
        // One step at a time.
        val f = FunctionWithFunctions.a(4)
        assert(f(5) === 9)

        assert(FunctionWithFunctions.a(4)(5) === 9)
        assert(FunctionWithFunctions.a.apply(4).apply(5) === 9)

      }

    }

    describe("Currying tests - taking a set of args and turning them int to returning function") {
      it("should return curried form of the function") {
        FunctionWithFunctions.icurried.isInstanceOf[(Int => (Int => Int))]
      }

      it("should return uncurried form of the curried function") {
        FunctionWithFunctions.iuncurried.isInstanceOf[((Int, Int) => Int)]
      }
    }

    describe("Curried parameters - makes partial application of them easier.") {
      it("should be able to create a currying function using a currying method") {
        val cf = FunctionWithFunctions.currying(5) _

        // since it's curried, it's a cleaner FP way of subsequent invocation
        assert(cf(10)(25) === 40)
        assert(cf.apply(10).apply(25) === 40) // for clarity. Method returns a method and then we apply on it
      }

      it("should return a non currying method, whose invocation then is typical OO method calling") {
        val nonc = FunctionWithFunctions.nonCurrying(5, _:Int, _:Int) // this is more uglier than currying declaration

        assert(nonc(10, 25) === 40) // more traditional invocation
      }
    }

    describe("ByName param tests - as it's lazy and allows block of code as arg, excellent for clean up (try etc.") {
      it("should return a valid value for safe division") {
        val d = FunctionWithFunctions.safeDivide {
          val x = 100
          val y = 20

          x / y
        }

        assert(d.get === 5)
      }
     it("should return none for division by 0") {
        val d = FunctionWithFunctions.safeDivide {
          val x = 100
          val y = 0

          x / y
        }

        assert(d === None)
      }


    }

  }

}

object BasicFunctionTests {

  // 1 arg
  val basicF1: Function1[Int, Int] = new Function[Int, Int] {
    def apply(i: Int) = i + 1 // APPLY allows us to directly call the function
  }
  val intermediateF1: (Int => Int) = new Function[Int, Int] {
    def apply(i: Int) = i + 1
  }
  val finalF1: (Int => Int) =  (i: Int) => i + 1 // function by default use apply method.

  val afterTypeInferenceF1 = (i: Int) => i + 1 // As we know i + 1, will return an Int

  // No args
  val intermediateF0: (() => Int)= new Function0[Int] {
    def apply(): Int = 1
  }
  val finalF0: (() => Int) = () => 1

  val afterTypeInferenceF0 = () => 1 // We know the return in Int

  // 2 args
  val intermediateF2: (Int, String) => String = new Function2[Int, String, String] {
    def apply(x: Int, y: String)  = y + x

  }

  val finalF2: (Int, String) => String = (x: Int, y: String) => y + x

  val afterTypeInferenceF2 = (x: Int, y: String) => y + x // We know Int+String is String

  // Using tuples to return more than one value

  val stringAndSize = (x: String) => (x, x.size) // Type inference helps us to get away with the type declaration

  // functions vs methods
  val addOneF  = (i: Int ) => i + 1

  def addOne(i: Int): Int  = i + 1
}
class FooBaz(x: Int) {
  def add(y: Int)  = x + y

}

class Baz(z: Int) {
  def qux(f: Int => Int) = f(z) // Use the class arg z as param
}

class Closure(x: Int) {
  def bar (y: Int => Int) = y(x)
}

object FunctionWithFunctions {

  /**
    * Higher order function
    * A function has a function as param (y: Int => Int)
    */
  //  val f: (Int, Int => Int) => Int = (x: Int, y: Int => Int) => y(x)
  val f = (x: Int, y: Int => Int) => y(x)



  /**
    * Currying. And this one is also a closure as 2nd function closes over x.
    * Function returning function
    *
    */
  //  val a: (Int => Int) => (Int => Int) => Int = (x: Int) => (y: Int) => x + y
  val a = (x: Int) => (y: Int) => x + y

  /**
    * CURRYING
    */
  //  val h: (Int => Int) => (Int => Int) => Int = (x: Int) => (y: Int) => x + y
  val h = (x: Int) => (y: Int) => x + y // curried version of i
  val i = (x: Int, y: Int) => x + y
  val icurried = i.curried
  val iuncurried = Function.uncurried(icurried)


  /** Curried parameters - makes partial application of them easier.
    * Calling nonCurrying partially would look like this - "val f = foo(5, _:Int, _:Int)", which is a bit goofy.
    * Whereas currying a curried method could be called like this "val g = bar(5) _"
    */
  def nonCurrying(x: Int, y: Int, z : Int) = x + y + z

  def currying(x: Int)(y: Int)(z: Int) = x + y + z // curried method

  /**
    * ByValue (Eager) - def byValue(x: Int)(y: Int) = { println("By value"); x + y)
    * Innvocation :
    * val a = byValue(4) {
    *           println("In call")
    *           6
    *         } // prints "In call" first as it's eager
    *
    * byFunction (Lazy but not very clean) - def byFunction(x: Int)(y: ()=>Int) =
    *                                           { println("By Function"); x + y)
    * Innvocation :
    * val b = byFunction(4) ( () => {
    *                                 println("In call")
    *                                 6
    *                                 } ) // prints "In function" so lazy
    *                                               // but the calling code is not clean
    *
    * and byName (Lazy and clean to call. Can send block of code)
    *         - def byName(x: Int)(f: => Int) = { println("By name", x + y}
    * Innovation:
    * val c = byName(4) {
    *           println("In call")
    *           6
    *         } // prints "By name" first as it's lazy and the calling code is clean
    * ** by-name param evaluates only when given **
    * More : https://tpolecat.github.io/2014/06/26/call-by-name.html
    *
    */
  def safeDivide(f: => Int): Option[Int] = {
    try {

      Some(f)

    } catch {
      case ae: ArithmeticException => None
    }
  }




}