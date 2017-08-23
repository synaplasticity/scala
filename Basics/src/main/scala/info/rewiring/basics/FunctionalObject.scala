package info.rewiring.basics

object FunctionalObject {
 
  val addOne = (x: Int) => x + 1

  def sum(a: Int, b: Int): Int = a + b

}

object ThisEmployee extends Employee("John", "Doe", "Grocer") {

}