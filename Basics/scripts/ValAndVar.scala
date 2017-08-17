//package info.rewiring.basics

//object ValAndVar {
  
  
  val `can use vals with space` = "some value" // could be useful with DSL and tests readibility
  println(`can use vals with space`)
 
      
  // OPChar
  {
    val whatIsItsValue_? = 100  
    val currentValueIs_! = whatIsItsValue_? + 1
    println("OPChar example : " + currentValueIs_!)
  }

  // Can use reserved words using ``
  {
    val `import` = 10
    println(`import`)
  }
  
  // If not a keyword, you can directly use the var/val without ``
  {
    val `magic` = "foo"
    println(magic)
  }
  
  // What does the following return
  {
    val `void` = 100
    val `false` = true
    val `return` = 90
    if (`false`) `void` else `return`
  }
//}