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
        val m: Manager = new Manager("Jane", "Doe", "Ms", Department("R&D"))
        assert(m.isInstanceOf[Manager])
      }

      it("should return type Manager for undeclared type due to type inference") {
        val m2 = new Manager("Jane", "Doe", "Ms", Department("R&D"))

        assert(m2.isInstanceOf[Manager])
      }

      it("should not compile for department name for employee") {
        val m3 = new Employee("John", "Doe", "Mr")
        assertDoesNotCompile(" m3.department.name = \"R&D\" ")
      }

    }

    describe("Method overriding tests") {
      it("should use the overridden method in Manager, even though an Employee reference is used to point to a Manager object") {
        val manager: Manager = new Manager("Jane", "Doe", "Ms", Department("R&D"))
        val mangerAsEmp: Employee = manager // Legal as Manager is also an employee. However,
                                            // it references a Manager object that was constructed
                                            // in the previous line

        assert(manager.fullName().equals("Jane Doe, R&D Manager"))
        assert(mangerAsEmp.fullName().equals("Jane Doe, R&D Manager")) // as it points to Manager object
      }

      it("should return the new title and department as Toys for overloaded copy method") {
        val jane: Manager = new Manager("Jane", "Doe", "Ms", Department("R&D"))

        val updatedJane: Manager = jane.copy(title = "Mrs")
        assert(updatedJane.title.equals("Mrs"))
        assert(updatedJane.department.name.equals("Toys"))
      }

    }

    describe("equals, hashCode tests") {
      it("should return true for identical valued Employee objects") {
        val emp1: Employee = new Employee("A", "B", "Mr")
        val emp2: Employee = new Employee("A", "B", "Mr")

        assert(emp1.equals(emp2))
        // == is same as .equals() in scala
        assert(emp1 == emp2)
      }

      it("should return false if one of the object is Manager") {
        val emp: Employee = new Employee("A", "B", "Mr")
        val man: Manager = new Manager("X", "Y", "Ms", Department("Toys"))

        assert(emp != man)
      }

      it("should return true if one of the object is Manager but has same attributes as the employee") {
        val emp: Employee = new Employee("A", "B", "Mr")
        val man: Manager = new Manager("A", "B", "Mr", Department("Toys"))

        assert(emp == man)
      }

      it("should return true for objects with same reference") {
        val emp1: Employee = new Employee("A", "B", "Mr")
        var emp2: Employee = new Employee("Y", "Z", "Ms")

        emp2 = emp1

        assert(emp2 eq emp1) // eq is an operator equivalent to == in java for reference matching
      }

      // HASH CODES
      it("should return true for identical employee object hashcodes") {
        val emp1: Employee = new Employee("A", "B", "Mr")
        val emp2: Employee = new Employee("A", "B", "Mr")

        assert(emp1.hashCode() == emp2.hashCode())
      }

      it("should return false for non identical employee object hashcodes") {
        val emp1: Employee = new Employee("A", "B", "Mr")
        val emp2: Employee = new Employee("A", "Z", "Ms")

        assert(emp1.hashCode() != emp2.hashCode())
      }
    }


    describe("Case class tests") {

      it("should be able to use access/mutator methods even though args is not declared with val") {
        val dept: Department = Department("Games") // NOTE: You do not need *new* for case classes

        assert(dept.name == "Games")
      }

      it("should provide equals method OOTB do case classes") {
        val dept1: Department = Department("Games") // NOTE: You do not need *new* for case classes
        val dept2: Department = Department("Games")
        assert(dept1 == dept2)
      }

      it("should allow to override default toString impl for case classes") {
        val dept1: Department = Department("Games") // NOTE: You do not need *new* for case classes

        assert(dept1.toString != "Department(Games)")
      }

      it("should allow for a new var to use the automatic pattern matching") {
        val dept1: Department = Department("Games") // NOTE: You do not need *new* for case classes

        val Department(dept2) = dept1 // dept2 has a the value "Games" (String)
        // Typically, one would need a match stmt to do this.

        assert(dept2 == "Games")
        assert(dept2.isInstanceOf[String])
      }

      it("should not be able to access employee method as person val/var") {
        val emp1: Employee = new Employee("A", "B", "Mr")

        val aPerson: Person = emp1 // Both employee and manager can be used as reference

        assertDoesNotCompile("aPerson.fullName()")
      }
    }

  }
}

