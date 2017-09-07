package info.rewiring.basics

import org.scalatest.FunSpec

class CollectionWithFunctionTests extends FunSpec {

  describe("Collection with function test suite") {

    describe("Basic map tests") {
      it("should be able to map a function on a collection like list") {
        val list = (1 to 5).toList

        val results = list.map((x: Int) => x + 1) // Add one to each element list
        assert(results === (2 to 6).toList)
      }

      it("should be able to map a function to a collection using different syntatic sugar and type inference") {
        val list = (1 to 5).toList

        assert( list.map((x: Int) => x + 1) === (2 to 6).toList ) // Add one to each element list

        assert( list.map(x => x + 1) === (2 to 6).toList ) // We already know the List is of Int

        assert( list.map(_ + 1) === (2 to 6).toList ) // We know we can replace single function var with "_"

        assert( list.map(1 + _) === (2 to 6).toList ) // addition is commutative so we can switch the operators

        import scala.language.postfixOps // required for the following version syntatic sugar
        assert( list.map(1+) === (2 to 6).toList ) // A trailing "_" is not required

      }

    }

    describe("Basic set tests") {
      it("should be able to use map on set's too") {
        val set: Set[String] = Set("One", "Two", "Three")

        assert( set.map(x => x.length) === Set(3, 5)) // As the set hold only unique numbers (One and Two are 3
      }

      it("should be able to return a tuple if required (lists can do it too)") {
        val set: Set[String] = Set("One", "Two", "Three")

        assert( set.map(x => (x, x.length)) === Set(("One", 3), ("Two", 3), ("Three", 5)) )
      }
    }

    describe("Basic map tests") {
      it("should be able to append to Symbols which are map keys while using a map fn") {
        val map: Map[Symbol, Int] = Map('A -> 4, 'B -> 5, 'C ->2)

        val tmap: Map[Symbol, Int] = map.map(t => (Symbol("Team " + t._1.name), t._2) )

        assert( tmap.head === (Symbol("Team A"), 4) ) // On println, shows as ('Team A, 4)
      }

    }

    describe("Filter test suite") {
      it("should be able to find even number using Filter on a collection") {
        val range = 1 to 10
        val evens = range.filter(x => x %2 == 0) // even numbers
        val odds = range.filterNot(_ %2 == 0) // odd numbers

        assert(evens !== odds)
      }

      it("should be able to find letter with vowels > 1") {
        val colors = Set("Red", "Blue", "Green", "Purple", "Orange")
        val wordsWithMoreThanOneVowel = colors.filter(w => CollectionWithFunctionTests.filterVowels(w).length > 1)

        assert(wordsWithMoreThanOneVowel === Set("Blue", "Green", "Purple", "Orange"))
      }

    }

    describe("For comprehension (preferred over for loop - **Also most of the Scala collections are " +
      "monadic as they support map, flatMap, filter, foreach**) test suite") {

      it("should return the same using for/yield and map") {
        val forYield = for(i <- 1 to 10) yield ( i + 1)
        val forMap = (1 to 10)
                      .map(i => i + 1)

        assert (forYield == forMap)
      }

      it("should be same value if we use for/yield and flatMap for 2 dimensional loops") {
        val forYieldList = for( i <- 1 to 4; j <- 5 to 8) yield (i, j)
        // following will return a list of list.
        // e.g.: ( List((1, 2), (1, 3), (1, 4), (1, 5)), List((2, 1), (2, 2) etc.
        val forMapListofList = List(1, 2, 3, 4)
                                .map(i => List(5, 6, 7, 8)
                                  .map(j => (i, j)))
        // so lets flatten it
        val forMapList = List(1, 2, 3, 4)
                          .flatMap(i => List(5, 6, 7, 8)
                            .map(j => (i, j)))

        assert(forYieldList === forMapList)

      }

      it("should be equivalent if we use for/yield and forMap with filters") {
        val forYield = for(i <- 1 to 4
                           if (i % 2 == 0);
                           j <- 5 to 8) yield (i, j)
        // Filter is inefficient
        val forMapFilter = List(1,2,3,4)
                            .filter(i => i % 2 == 0)
                            .flatMap(i => List(5,6,7,8)
                              .map(j => (i,j)))
        // So we will use *withFiter* which is lazy, therefore more efficient
        val forMapWithFilter = List(1,2,3,4)
                                .withFilter(i => i % 2 == 0)
                                .flatMap(i => List(5,6,7,8)
                                  .map(j => (i,j)))

        assert(forYield === forMapWithFilter)
      }

    }

    describe("Fold and reduce test suite") {

      it("should return same value using foldLeft with a seed and reduce function") {

        // fold/reduceLeft
        // total = 0; next = 1
        // total = 1; next = 2
        // total = 2; next = 3
        val foldLeft = (1 to 10)
                        .foldLeft(0)( (total: Int, next: Int) => total + next)
                      //.foldLeft(0)( _+_) - shortcut "_" replaces vars as we have seen before
        val reduce = (1 to 10)
                          .reduceLeft( (total: Int, next: Int) => total + next )

        assert(foldLeft === reduce)

      }

      it("should return same value using foldRight with a seed and reduce function") {

        // fold/reduceRight
        // total = 0; next = 10 --- starts from the right most value of the collection
        // total = 10; next = 9
        // total = 19; next = 8
        val foldRight = (1 to 10)
          .foldRight(0)( (next: Int, total: Int) => total + next) // next comes first in foldRight
        val reduce = (1 to 10)
          .reduceLeft( (next: Int, total: Int) => total + next )

        assert(foldRight === reduce)

      }

    }

  }


}

object CollectionWithFunctionTests {

  def filterVowels(string: String): String = {
    string.toLowerCase.filter(x => Set('a', 'e', 'i', 'o', 'u').contains(x))
  }
}

