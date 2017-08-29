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

        assert(closure.bar(f) === 300) // NOTE: m is in current scope
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