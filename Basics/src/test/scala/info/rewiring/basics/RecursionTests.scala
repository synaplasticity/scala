package info.rewiring.basics

import org.scalatest.FunSpec

class RecursionTests extends FunSpec {

  describe("Recursive test suite") {

    describe("Method in method tests") {
      it("should return 120 factorial for 5 as input") {
        val methodInMethods = new MethodInMethods()
        assert(methodInMethods.factorial(5) == 120)
      }
    }

    describe("tail-recursion tests") {
      it("should return 120 factorial for 5 as input") {
        val tailRecursiveOptimization = new TailRecursiveOptimization()
        assert(tailRecursiveOptimization.factorial(5) == 120)
      }
    }

    describe("basic recursion tests") {
      it("should return 120 factorial for 5 as input") {
        val basicRecursion = new BasicRecursion
        assert(basicRecursion.factorial(5) == 120)
      }
    }

  }

}
