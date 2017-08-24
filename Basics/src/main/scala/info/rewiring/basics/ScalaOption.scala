package info.rewiring.basics

class Associate(val firstName: String, val middleName: Option[String], val lastName: String) {

  def this(firstName: String, middleName: String, lastName: String) =
      this(firstName, Some(middleName), lastName)


  def this(firstName: String, lastName: String) =
      this(firstName, None, lastName)

  /**
    *
    * Just for learning purpose. Pattern matching
    */
  def peelMiddleName(str : Option[String]) : String = {

    str match {
      case Some(name) => name
      case None => "No middle name"
      // Could have been case _ (default)
    }
  }
}
