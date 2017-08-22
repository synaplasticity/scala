package info.rewiring.basics

import scala.beans.BeanProperty

// NOTE: val needs to be added. Could be var too, but scala programmers do not like that.
// @BeanProperty create java style get/setters (accessor/mutator). Helpful for interoperability
class Employee(@BeanProperty val firstName: String, val lastName: String, val title: String) {
  // require will be executed as part of the default constructor
  // Don't need to check for null unless this class wil interact with Java classes.
  require(firstName.nonEmpty, "First name cannot be empty")
  require(lastName.nonEmpty, "Last name cannot be empty")

  // One could also do an explicit check on args
  if(title.contains("Senior") || title.contains("Junior"))
    throw new IllegalArgumentException("Title cannot contain Senior or Junior in it")

  // ancillary constructors :
  // In scala the primary constructor is wide as we want to reduce object copy in
  // an immutable language
  // Unlike java and other OO languages the ancillary constructors are the once that
  // are more *narrow*
  // this() call has to be the *first* call, else you will get a compilation error
  def this(firstName: String, lastName: String) = this(firstName, lastName, "Ms")

  //NOTE:
  // firstName or lastName is accessed by emp.firstName or emp.LastName
  // This is not direct access to the field, but the method with parameters
  // which does not need to be explicitly declared.
  // NEEDS parameters on primary constrictor to be declared as VAL

  // String interpolation. Like python "".format()
  def fullName(): String = s"$firstName $lastName"

  // Always go immutable. So in this case create a copy rather than change state
  def changeLastName(ln: String) = new Employee(firstName, ln)

  // default params
  def copy(firstName: String = this.firstName,
           lastName: String = this.lastName,
           title: String = this.title): Employee = {

    new Employee(firstName, lastName, title)
  }
}

// You could also use default arguments. E.g.: For Title
class Empl(@BeanProperty firstName: String, val lastName: String, val title: String = "Ms") {

}

class Department(val name: String)

class Manager(firstName: String, lastName: String, title: String, val department: Department)
    extends Employee(firstName, lastName, title) {

  override def fullName(): String = super.fullName() + s", ${department.name} Manager"
}

