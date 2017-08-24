package info.rewiring.basics

import org.scalatest.FunSpec

class InfixOperatorsTests extends FunSpec{

  describe("Infix operators test suite") {

    describe("Basic infix operations and chaining") {

      it("should return the same value using infix operator - foo addA 5 (Scala e.g.: 1 + 2") {
        val foo = new Foo(10)

        assert(foo.addA(5) === (foo addA 5))
      }

      it("should return the same value using infix operator for more than one arg - foo addTwo (5 + 10") {
        val foo = new Foo(10)

        assert(foo.addTwo(5, 10) === (foo addTwo (5, 10)) )
      }

      it("should be able to chain calls using infix operator - foo alsoAdd 5 alsoAdd 10 alsoAdd 20 addTwo (1, 2)") {
        val foo = new Foo(10)

        assert( foo.alsoAdd(5)
                    .alsoAdd(10)
                    .alsoAdd(20)
                    .addTwo(1, 2)
                        === (foo alsoAdd 5 alsoAdd 10 alsoAdd 20 addTwo (1, 2)) )
      }

    }

    describe("Right associative colon tests") {
      it("should use right associativeness for methods ending with ':' while using infix operators") {
        val foo = new Foo(10)

        assert(foo.~:(5) === (5 ~: foo))

        assertDoesNotCompile("(foo ~: 5)")
      }

    }



  }
}
