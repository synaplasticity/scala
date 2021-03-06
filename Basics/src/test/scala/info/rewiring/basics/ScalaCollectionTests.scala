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

    describe("Range tests") {
      it("should return a string of numbers bound by the upper number - inclusive") {
        val r1 = (1 to 4)
        assert( r1.toList === List(1,2,3,4) )
      }

      it("should return a string of numbers bound by the upper number - exclusive") {
        val r1 = (1 until 4)
        assert( r1.toList === List(1,2,3) )
      }

      it("should support methods that other collection like list do") {
        val r1 = (1 to 4)

        assert(r1.head === 1)
        assert(r1.tail === List(2, 3, 4))
        assert(r1.init === List(1, 2, 3))
        assert(r1.min === 1)
      }

      it("should be able to step the range by a number") {
        val r2 = (1 to 10 by 3)

        assert(r2.toList === List(1, 4, 7, 10))
      }

    }

  }


}
