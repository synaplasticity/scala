package info.rewiring.basics

import org.scalatest.{BeforeAndAfter, FunSpec}

class IteratorTest extends FunSpec with BeforeAndAfter {
  var intIt: IntIterator = null

  describe("Iterator tests suite") {
    describe("Basic iterator tests") {

      before {
        intIt = new IntIterator(10)
      }

      it("should respond with 0 for the first next() call") {
        assert(intIt.next() == 0)
      }
    }
  }
}
