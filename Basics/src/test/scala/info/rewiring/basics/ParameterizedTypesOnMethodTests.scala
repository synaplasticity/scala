package info.rewiring.basics

import org.scalatest.FunSpec

class ParameterizedTypesOnMethodTests extends FunSpec{

  describe("Parameterized types on methods test suite") {

    describe("Type tests") {
      val parameterizedTypesOnMethods: ParameterizedTypesOnMethods = new ParameterizedTypesOnMethods

      it("should return Int when all types are Int") {
        assert(parameterizedTypesOnMethods.decide(true, 2, 1).isInstanceOf[Int])
      }

      // To avoid this, use the toChar method
      it("should return Int when one of the type is Char as scala converts Char to Int") {
        assert(parameterizedTypesOnMethods.decide(true, 2, 'a').isInstanceOf[Int])
      }

      it("should return Any if one type is Int (AnyVal) and other is String") {
        assert(parameterizedTypesOnMethods.decide(true, 12, "someday").isInstanceOf[Any])
      }

      /**
        * The following will result in a compilation error. Why? :
        * AnyVal does not exist anymore at runtime. Only at compile-time. In other words, it's just a compiler "trick" to consider the JVM primitives as first-class objects.
        * However, the isInstanceOf method is executed at runtime, so it cannot work. Hence the compiler error.
        **/
//      it("should return AnyVal if both types are of type AnyVal") {
//        assert(parameterizedTypesOnMethods.decide(false, 24, 42f).isInstanceOf[AnyVal])
//      }

      it("should return AnyRef for two class references") {
        assert(parameterizedTypesOnMethods.decide(true, new String("asd"), new ParameterizedTypesOnMethods).isInstanceOf[AnyRef])
      }

      it("should return Any for one class reference") {
        assert(parameterizedTypesOnMethods.decide(true, 'c', new ParameterizedTypesOnMethods).isInstanceOf[Any])
      }

    }



  }

}
