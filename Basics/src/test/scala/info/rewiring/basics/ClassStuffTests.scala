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

      it("should throw as exception if firstName is empty") {
        assertThrows[IllegalArgumentException] {
          val emp7 = new Employee("", "Hartfield", "Mr")
        }
      }

      it("should throw as exception if lastName is empty and return a meaningful message") {
        val caught = intercept[IllegalArgumentException] {
          new Employee("John", "", "Mr")
        }

        assert(caught.getMessage.equals("requirement failed: Last name cannot be empty"))
      }

      it("should throw as exception with right message if the title has Senior/Junior") {
        val ex = intercept[IllegalArgumentException] {
          new Employee("John", "Hartfield", "Senior")
        }

        assert(ex.getMessage.equals("Title cannot contain Senior or Junior in it"))
      }

    }

    describe("Subclassing tests" ) {
      it("should return type of Manager for manager") {
        val m: Manager = new Manager("Jane", "Doe", "Ms", new Department("R&D"))
        assert(m.isInstanceOf[Manager])
      }

      it("should return type Manager for undeclared type due to type inference") {
        val m2 = new Manager("Jane", "Doe", "Ms", new Department("R&D"))

        assert(m2.isInstanceOf[Manager])
      }

      it("should not compile for department name for employee") {
        val m3 = new Employee("John", "Doe", "Mr")
        assertDoesNotCompile(" m3.department.name = \"R&D\" ")
      }

    }

    describe("Method overriding tests") {
      it("should use the overridden method in Manager, even though an Employee reference is used to point to a Manager object") {
        val manager: Manager = new Manager("Jane", "Doe", "Ms", new Department("R&D"))
        val mangerAsEmp: Employee = manager // Legal as Manager is also an employee. However,
                                            // it references a Manager object that was constructed
                                            // in the previous line

        assert(manager.fullName().equals("Jane Doe, R&D Manager"))
        assert(mangerAsEmp.fullName().equals("Jane Doe, R&D Manager")) // as it points to Manager object
      }

      it("should return the new title and department as Toys for overloaded copy method") {
        val jane: Manager = new Manager("Jane", "Doe", "Ms", new Department("R&D"))

        val updatedJane: Manager = jane.copy(title = "Mrs")
        assert(updatedJane.title.equals("Mrs"))
        assert(updatedJane.department.name.equals("Toys"))
      }


    }

  }
}
