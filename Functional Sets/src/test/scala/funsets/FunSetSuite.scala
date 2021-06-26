package funsets
package math

import org.junit._

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite {

  import FunSets._

  @Test def `contains is implemented`: Unit = {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val all = (x => true) : FunSet
    val even = (x => if(x % 2 == 0) true else false) : FunSet
    val odd = (x => !even(x)) : FunSet
    val div_by_3 = (x => if(x % 3 == 0) true else false) : FunSet
    val div_by_5 = (x => if(x % 5 == 0) true else false) : FunSet
  }

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * @Ignore annotation.
   */
   ("not ready yet"); @Test def `singleton set one contains one`: Unit = {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  @Test def `union contains all elements of each set`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  @Test def `intersect contains common elements between sets`: Unit = {
    new TestSets {
      val s = intersect(div_by_5, even)
      assert(contains(s, 40), "Intersect 40")
      assert(contains(s, 50), "Intersect 50")
      assert(!contains(s, 8), "Intersect 8")
      assert(!contains(s, 15), "Intersect 15")
    }
  }

  @Test def `diff works properly`: Unit = {
    new TestSets {
      val s = diff(all, even)
      assert(contains(s, 3), "Diff 3")
      assert(!contains(s, 8), "Diff 8")
    }
  }

  @Test def `filter works properly`: Unit = {
    new TestSets {
      val s = filter(all, x => x<50)
      assert(contains(s, 29), "Filter 29")
      assert(!contains(s,98), "Filter 98")
    }
  }

  @Test def `forall works properly`: Unit = {
    new TestSets {
      assert(!forall(even, x => if(x % 2 == 0) false else true), "forall 1")
      assert(forall(even, x => if(x % 2 == 0) true else false), "forall 2")
    }
  }

  @Test def `exists works properly`: Unit = {
    new TestSets {
      assert(exists(even, x => if(x % 5 == 0) false else true), "exists 1")
      assert(!exists(odd, x => if(x % 2 == 0) false else true), "exists 2")
    }
  }



  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}
