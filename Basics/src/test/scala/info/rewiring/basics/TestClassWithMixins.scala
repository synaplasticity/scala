package info.rewiring.basics

import org.scalatest.FunSpec

class TestClassWithMixins extends FunSpec {

  describe("Class composition with mixins test suite") {

    describe("See that we get the right messages") {

      it("should display the message from Message class, while the class member is used") {
        val testClass: SomeClass = new SomeClass
        assert(testClass.message == "I am an instance of BaseClass")
      }

      it("should display message in bols using the Loud trait") {
        val testClass: SomeClass = new SomeClass
        assert(testClass.loudMessage == "I AM AN INSTANCE OF BASECLASS")
      }

    }

  }

}
