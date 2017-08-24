package info.rewiring.basics

abstract class BaseClass {
  val message: String
}

class Message extends BaseClass {
  val message = "I am an instance of BaseClass"
}

trait Loud extends BaseClass {
  def loudMessage = message.toUpperCase()
}

class SomeClass extends Message with Loud