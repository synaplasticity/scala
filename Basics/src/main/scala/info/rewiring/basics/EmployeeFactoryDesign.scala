package info.rewiring.basics.emp

import java.time._

// NOTE : The constructor is private
class Employee private(val firstName: String, val lastName: String, val title: String, val hireDate: LocalDate) {
  def getHireDate: LocalDate = hireDate
}

// Companion object as a factory
object Employee {

  def create(firstName: String, lastName: String, title: String): Employee =
  // NOTE: companion object has access to the private constructor of the class!
    new Employee(firstName, lastName, title, LocalDate.now())

  // overloaded method
  def create(firstName: String, lastName: String, title: String, hireDate: LocalDate): Employee =
    new Employee(firstName, lastName, title, hireDate)

}