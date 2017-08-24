package info.rewiring.basics

import org.scalatest.FunSpec

class TuplesTests extends FunSpec {

    describe("Tuples test suite") {
      describe("Tuple basic tests") {

        it("should be able to extract the first value of tuples using _1") {
          val t = (1, 42.0, "String")

          assert(t._1 === 1)
        }

        it("should be able to get the exact types of a tuple") {
          val t = (1, 42.0, "String")

          assert(t.isInstanceOf[(Int, Double, String)])

          assert(t.isInstanceOf[Tuple3[Int, Double, String]]) // up to Tuple22
        }

        it("should be able to swap two members of a tuple") {
          val t = (1, "String")

          assert(t.swap === ("String", 1)) // And it's all immutable
          assert(t === (1, "String")) // as the original t is still the same as declared
        }

      }


    }
}
