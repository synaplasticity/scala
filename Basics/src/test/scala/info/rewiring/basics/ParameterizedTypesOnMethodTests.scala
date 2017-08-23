package info.rewiring.basics

import org.scalatest.FunSpec

class ParameterizedTypesOnMethodTests extends FunSpec{

  describe("Parameterized types on methods test suite") {

    describe("Type tests") {
      val parameterizedTypesOnMethods: ParameterizedTypesOnMethods = new ParameterizedTypesOnMethods

      it("should return Int when all types are Int") {
        assert(parameterizedTypesOnMethods.decide(b = true, 2, 1).isInstanceOf[Int])
      }

      // To avoid this, use the toChar method
      it("should return Int when one of the type is Char as scala converts Char to Int") {
        assert(parameterizedTypesOnMethods.decide(b = true, x = 2, y = 'a').isInstanceOf[Int])
      }

      it("should return Any if one type is Int (AnyVal) and other is String") {
        assert(parameterizedTypesOnMethods.decide(b = true, x = 12, y = "someday").isInstanceOf[Any])
      }

      /**
        * The following will result in a compilation error. Why? :
        * AnyVal does not exist anymore at runtime. Only at compile-time. In other words,
        * it's just a compiler "trick" to consider the JVM primitives as first-class objects.
        * However, the isInstanceOf method is executed at runtime, so it cannot work. Hence
        * the compiler error.
        **/
      it("should fail to compile if both types are of type AnyVal as AnyVal is not available at runtime") {
        assertDoesNotCompile("parameterizedTypesOnMethods.decide(false, 24, 42f).isInstanceOf[AnyVal]")
      }

      it("should return AnyRef for two class references") {
        assert(parameterizedTypesOnMethods.decide(b = true, x = new String("asd"), y = new ParameterizedTypesOnMethods).isInstanceOf[AnyRef])
      }

      it("should return Any for one class reference") {
        assert(parameterizedTypesOnMethods.decide(b = true, x = 'c', y = new ParameterizedTypesOnMethods).isInstanceOf[Any])
      }

    }



  }

}
