package info.rewiring.basics

import org.scalatest.FunSpec

class IsAndAsInstanceOfTests extends FunSpec {

  describe("IsAndAsInstanceOf test suite") {

    describe("Instance of tests") {
      it("should return incremented by 1 Int value, while presenting ints") {
        val isAndAsInstanceOf = new IsAndAsInstanceOf
        assert(isAndAsInstanceOf.decide(10) == 11)
      }

      it("should return -1 for all non Int types") {
        val isAndAsInstanceOf = new IsAndAsInstanceOf
        assert(isAndAsInstanceOf.decide("Tes") == -1)
      }

      it("should return -1 for type Any/Float") {
        val isAndAsInstanceOf = new IsAndAsInstanceOf
        val any: Any = 10.0
        assert(isAndAsInstanceOf.decide(any) == -1)
      }


    }


  }

}
