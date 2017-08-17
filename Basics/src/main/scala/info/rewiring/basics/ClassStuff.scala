package info.rewiring.basics

import scala.beans.BeanProperty

// NOTE: val needs to be added. Could be var too, but scala programmers do not like that.
// @BeanProperty create java style get/setters (accessor/mutator). Helpful for interoperability
class Employee(@BeanProperty firstName: String, val lastName: String, val title: String) {

  // ancillary constructors :
  // In scala the primary constructor is wide as we want to reduce object copy in
  // an immutable language
  // Unlike java and other OO languages the ancillary constructors are the once that
  // are more *narrow*
  def this(firstName: String, lastName: String) = this(firstName, lastName, "Ms")

  //NOTE:
  // firstName or lastName is accessed by emp.firstName or emp.LastName
  // This is not direct access to the field, but the method with parameters
  // which does not need to be explicitly declared.
  // NEEDS parameters on primary constrictor to be declared as VAL
}
