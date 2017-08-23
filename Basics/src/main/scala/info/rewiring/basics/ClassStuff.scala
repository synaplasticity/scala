package info.rewiring.basics

import scala.beans.BeanProperty


abstract class Person {
  def firstName: String
  def lastName: String
}

// NOTE: val needs to be added. Could be var too, but scala programmers do not like that.
// @BeanProperty create java style get/setters (accessor/mutator). Helpful for interoperability
class Employee(@BeanProperty val firstName: String, val lastName: String, val title: String)
    extends Person { // This works as a val is in front of firstName and lastName
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

  override def equals(obj: scala.Any): Boolean = {
    if (!obj.isInstanceOf[Employee]) false
    else {
      val x = obj.asInstanceOf[Employee]
      x.firstName.equals(this.firstName) &&
        x.lastName.equals(this.lastName) &&
        x.title == this.title // == is same as .equals() in scala
    }
  }

  /**
    * Usiing Josh Blouch's implementation
    * @return
    */
  override def hashCode(): Int = {
    var result = 42
    // use a prime like 31
    result = 31 * result + this.firstName.hashCode
    result = 31 * result + this.lastName.hashCode
    result = 31 * result + this.title.hashCode
    result
  }

}

// You could also use default arguments. E.g.: For Title
class Empl(@BeanProperty firstName: String, val lastName: String, val title: String = "Ms") {

}

/**
  * case classes provide by default things like hashcode, equals, toString and copy etc. features
  *
  * Also, you do not need to declare constructor args with val to get accessor/mutator
  *
  *
  * NOTE: A case subclass cannot extend a case superclass. All other classes can extend.
  *
  * @param name - department name
  */
case class Department(name: String) {

  /**
    * You can also, override the default hashcode, equals, toString implementations
    *
    * @return
    */
  override def toString: String = s"Department: $name"

}

class Manager(firstName: String, lastName: String, title: String, val department: Department)
    extends Employee(firstName, lastName, title) {

  override def fullName(): String = super.fullName() + s", ${department.name} Manager"

  // NOTE: We do not add the department as that will then *overload* the method
  // We are oding only override now
  override def copy(firstName: String = this.firstName,
           lastName: String = this.lastName,
           title: String = this.title): Manager = {

    new Manager(firstName, lastName, title, Department("Toys"))
  }

  // Overloaded methods
  // WILL NOT compile as the superclass as *default parameters*
  // This is a corner case. n all other cases overload should work
  /*
  def copy(firstName: String = this.firstName,
                    lastName: String = this.lastName,
                    title: String = this.title,
                    department: Department = this.department): Manager = {

    new Manager(firstName, lastName, title, department)
  }
  */
}

