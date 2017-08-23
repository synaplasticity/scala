package info.rewiring.basics

import java.time.LocalDate

import org.scalatest.FunSpec
import info.rewiring.basics.emp.Employee

class CompanionObjectTests extends FunSpec {

  describe("Companion object test suite") {

    describe("Companion object basic tests") {

      it("should ensure that the var (totalbullets) in the companion object is reduced when mulitple objects are used") {
        val bond: SecretAgents = SecretAgents("Jame Bond")
        val bourne: SecretAgents = SecretAgents("Jason Bourne")

        bond.shoot(2500) // remaining = 500
        val remainingbullets = bourne.shoot(500)

        assert(remainingbullets == 0)
        assert(SecretAgents.bullets == 0)
      }


    }

    describe("Companion obect has access to classes private data tests") {
      it("should return the correct hire date") {
        val emp = Employee.create("A", "B", "Programmer")

        assert(emp.getHireDate == LocalDate.now)
      }

    }

  }

}
