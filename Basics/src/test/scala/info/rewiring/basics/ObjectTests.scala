package info.rewiring.basics

import org.scalatest.FunSpec

class ObjectTests extends FunSpec{

  describe("Object test suite") {

    describe("Basic Object tests") {

      it("should not be able to instantiate it") {
        assertDoesNotCompile("new FunctionalObject")
      }

      it("should be just one instance and same by val and ref") {
        val a = FunctionalObject
        val b = FunctionalObject

        assert(a == b) // Same by val
        assert(a eq b) // Same by ref
      }

      it("should be able to invoke the method in the object") {
        val a = FunctionalObject
        assert(a.sum(1, 1) == 2)
      }

      it("should be able to invoke the function in the object") {
        val a = FunctionalObject
        assert(a.addOne(2) == 3)
      }

      it("should be able to extend it by a class (cannot do so by an object, of course") {
        val emp = ThisEmployee

        assert(emp.firstName == "John")
      }

      it("should be able to invoke methods from the extended class") {
        val emp = ThisEmployee

        assert(emp.fullName() == "John Doe")
      }

    }


  }

}
