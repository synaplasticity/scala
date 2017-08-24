package info.rewiring.basics

import org.scalatest.FunSpec

class ScalaOptionTests extends FunSpec{

  describe("Scala option test suite") {

    describe("Scala option basic tests") {

      it("should set the middle name (Option) as Some('value'), when provided") {
        val a = new Associate("John", "K", "Antony")

        assert(a.middleName === Some("K"))
        assert(a.middleName.get === "K")
        assert(a.middleName.getOrElse("No middle name") === "K")
      }

      it("should return a None, when there is no middle name") {
        val a = new Associate("John", "Antony")

        assert(a.middleName === None)
        assert(a.middleName.getOrElse("No middle name") === "No middle name")

      }

      it("should return the option middle name without using .get(OrElse) - using match") {
        val a = new Associate("John", "K", "Antony")

        assert(a.peelMiddleName(a.middleName) === "K")
      }

      it("should return error message for option middle name without using .get(OrElse) - using match") {
        val a = new Associate("John", "Antony")

        assert(a.peelMiddleName(a.middleName) === "No middle name")
      }

    }


  }

}
