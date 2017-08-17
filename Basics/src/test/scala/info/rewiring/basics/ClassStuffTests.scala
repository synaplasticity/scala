package info.rewiring.basics

import org.scalatest.FunSpec

class ClassStuffTests extends FunSpec{

  describe("Class stuff test suite") {

    describe("Immutable ways to dealing with state change tests") {

      it("should return full name with space (string interpolation)") {
        val emp = new Employee("John", "Hartfield")
        assert(emp.fullName() == "John Hartfield")
      }

      it("should return Ms as title (default argument value") {
        val emp1 = new Employee("John", "Hartfield")
        assert(emp1.title == "Ms")
      }

      it("should return a new object of Employee (immutable)") {
        val emp2 = new Employee("John", "Hartfield")
        val emp2Changed = emp2.changeLastName("Doe")

        assert(!emp2.equals(emp2Changed))
        assert(emp2Changed.lastName == "Doe")
      }

      it("should return a new object with new or default values") {
        val emp3 = new Employee("John", "Hartfield")
        val emp4 = emp3.copy("Jane", "Doe", "Miss")

        assert(!emp3.equals(emp4))

        assert(emp4.firstName == "Jane")
        assert(emp4.lastName == "Doe")
        assert(emp4.title == "Miss")
      }

      it("should change only first name (default args") {
        val emp5 = new Employee("John", "Hartfield")
        val emp6 = emp5.copy(firstName = "Jane")

        assert(emp6.firstName.equals("Jane"))
        assert(emp6.lastName.equals("Hartfield"))
      }
    }



  }
}
