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
