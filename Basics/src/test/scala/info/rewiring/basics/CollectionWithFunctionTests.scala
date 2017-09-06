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

  }


}

object CollectionWithFunctionTests {

  def filterVowels(string: String): String = {
    string.toLowerCase.filter(x => Set('a', 'e', 'i', 'o', 'u').contains(x))
  }
}

