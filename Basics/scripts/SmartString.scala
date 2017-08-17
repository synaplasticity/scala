  val multiLineStringOldWay = "Text first line \n" +
                              "2nd line \n" +
                              "3rd line"
                              
  println("Multi string the old way : \n" + multiLineStringOldWay)
  
  println("----------------------------------")
      
  val multiLineStringSmartStringWay = """Text first line
                                         |2nd line
                                         |3rd line""".stripMargin 
                                         // without the stripMargin, the 2nd and 3rd 
                                         // line will be printed with tabbed space as
                                         // written here in the source code.

  println("Multi string the smart string way : \n" + multiLineStringSmartStringWay)
  
  
  val message = "How about meeting at 11:30AM"
  val regex = """(\s|[0-9])?[0:9]:[0-5][0-9]\s*(AM|PM)""".r
  println(regex.findAllIn(message).toList)