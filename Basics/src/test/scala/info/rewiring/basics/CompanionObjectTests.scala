package info.rewiring.basics

import org.scalatest.FunSpec

class CompanionObjectTests extends FunSpec {

  describe("Companion object test suite") {

    describe("Companion object basic tests") {

      it("should ensure that the var (totalbullets) in the companion object is reduced when mulitple objects are used") {
        val bond: SecretAgents = SecretAgents("Jame Bond")
        val bourne: SecretAgents = SecretAgents("Jason Bourne")

        bond.shoot(2500) // remaining = 500
        val remainingbullets = bourne.shoot(500)

        assert(remainingbullets == 0)
      }


    }


  }

}
