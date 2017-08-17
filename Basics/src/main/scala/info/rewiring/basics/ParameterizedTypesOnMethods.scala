package info.rewiring.basics

class ParameterizedTypesOnMethods {

    def decide[T](b: Boolean, x: T, y: T): T = {
      if(b) x else y
    }

}
