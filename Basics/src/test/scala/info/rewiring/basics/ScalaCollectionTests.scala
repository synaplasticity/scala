package info.rewiring.basics

import java.util.NoSuchElementException

import org.scalatest.FunSpec

class ScalaCollectionTests extends FunSpec {

  describe("Scala collection tests") {

    describe("Basic map tests") {
      it("should return a tuple while using lisp like arrow between key and value") {

        assert( (3 -> "Three").isInstanceOf[(Int, String)] )
      }

      it("should return Some(\"One\") for key 1 from the map as it's exists on the map") {
        val map = Map(1 -> "One", 2 -> "Two", 3 -> "Three")

        assert(map.get(1) === Some("One"))
      }

      it("should return the value \"One]\", while using the apply method. WARN: If it doesn't exists, this will end up in an error") {
        val map = Map(1 -> "One", 2 -> "Two", 3 -> "Three")

        assert(map(1) === "One")
      }

      it("should return None for key 4 as it does not exist on the map") {
        val map = Map(1 -> "One", 2 -> "Two", 3 -> "Three")

        assert(map.get(4) === None)
      }

      it("should throw an NoSuchElementException for key 4 as it does not exist while using apply") {
        val map = Map(1 -> "One", 2 -> "Two", 3 -> "Three")

        assertThrows[NoSuchElementException] {
          assert(map(4) === None)
        }
      }

      it("should return a list of tuples on toList method") {
        val map = Map(1 -> "One", 2 -> "Two", 3 -> "Three")

        assert( map.toList === List((1, "One"), (2, "Two"), (3, "Three")) )
      }

      it("should use Symbols instead of strings as *same* Symbols will always point ot the same object (interned)") {
        val sym1 = Symbol("key1") // All symbols are interned
        val sym2 = 'key1 // another way to define symbols

        assert(sym1 == sym2)
        assert(sym1 eq sym2)

        // whereas, this is not true for String objects and interned strings
        // so BAD for map keys
        val str1 = new String("key1")
        val str2 = "key1" // interned string

        assert(str1 == str2)
        assert( (str1 eq str2) == false)
      }

    }

  }


}
