package info.rewiring.basics

import org.scalatest.FunSpec

class ParameterizedClassTests extends FunSpec{

  describe("Parameterized class test suite") {

    describe("Class with with single type test (Box.class)") {

      it("should be of type Int, given an Int") {
        val i = Box(1).t
        assert(i.isInstanceOf[Int])
      }

      it("should be a class type, given a class object") {
        assert(Box(Department("Games")).t.isInstanceOf[Department])
      }

      it("should return type of t of the inner box") {
        val doubleBox = Box(Box(0.20f))
        assert(doubleBox.t.t.isInstanceOf[Float])
      }

      it("should return the correct types for nested parameterized types") {
        val couple = Couple("Hello", Couple(42.023, 12)) // Couple[String, Couple[Double, Int]]

        assert(couple.first.isInstanceOf[String])
        assert(couple.second.isInstanceOf[Couple[Double, Int]])
        assert(couple.second.first.isInstanceOf[Double])
      }
    }


  }
}
