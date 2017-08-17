package info.rewiring.basics

import org.scalatest.FunSpec

class TestMixinIterator extends FunSpec{

  describe("MixinIterator test suite") {

    describe("Test StringIterator tests") {
      it("should return c for input string ch for the first next call") {
        val si = new StringIterator("ch")
        assert(si.next == 'c')
      }
      it("should return null for input string c for the 2nd next call") {
        val si = new StringIterator("c")
        si.next
        assert(si.next == '0')
      }
    }

    describe("Rich iterator tests") {

      it("should display each char in the string") {
        class RichStringIter extends StringIterator("String") with RichIterator {
          val iter = new RichStringIter
          iter foreach println
        }
      }
    }

  }

}
