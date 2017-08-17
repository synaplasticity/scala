/**
 * If statements
 */


// Imperative style if

val no = 10
var result = ""

if (no < 10) result = "Less than 10"
else if (no > 10) result = "Greater than 10"
else result = "Exactly 10"

println(result)


// Functional style
// We want to avoid mutable values (result)

val results = if (no < 10) "Lesser than 10"
              else if (no > 10) "Greater than 10"
              else "Exactly 10"
println("Functional style : " + results)


/**
* While loop
*/

// Imperative style
var a: Int = 100
var countResult = ""

while (a > 0) {
  countResult = countResult + a;
  if (a > 1) countResult = countResult + ", "
  a = a - 1;
}
println(countResult)

// Functional style
println("Functional style :")
println( (1 to 100).reverse.mkString(", ") )


/** 
 *  For loops
 */

// Imperative style
var forResult = ""
for (a <- 1 to 100) {
  forResult = forResult + a
  if (a < 100) forResult = forResult + ", "
}
println(forResult)

// List
val xs = List(1, 2, 3, 4)
var xm = List[Int]()
for (a <-   xs) {
  xm = xm :+ (a + 1) // Add 1 to value is xs. ":+" operator overloading to append to list
}
println(xm)

// Functional style
println("Functional style")
println( for(a <- xs) yield (a.+(1)) ) // yield, execute operations and assigns results to a new collection
