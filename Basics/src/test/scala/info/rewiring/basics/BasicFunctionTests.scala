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


  }

}

object BasicFunctionTests {

  // 1 arg
  val basicF1: Function1[Int, Int] = new Function[Int, Int] {
    def apply(i: Int) = i + 1
  }
  val intermediateF1: (Int => Int) = new Function[Int, Int] {
    def apply(i: Int) = i + 1
  }
  val finalF1: (Int => Int) =  (i: Int) => i + 1

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
}
