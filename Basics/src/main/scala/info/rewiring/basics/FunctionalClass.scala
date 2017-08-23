package info.rewiring.basics

// Will fail, as this cannot be a class, but object.
// However an object can have both functions and methods.

class FunctionalClass {
	val addOne = (x:Int) => x + 1 // cannot have functions in a class

	def main(args: Array[String]): Unit = {
	  val f = new FunctionalClass();
	  println(addOne(2))
	}  

}

//object FunctionalObject {
//  val addOne = (x: Int) => x + 1
//  
////  def mains(args: Array[String]) : Unit = {
//    println(addOne(4));
////  }
//}