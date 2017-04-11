package funsets

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
  * This class is a test suite for the methods in object FunSets. To run
  * the test suite, you can either:
  *  - run the "test" command in the SBT console
  *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
  */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
    * Link to the scaladoc - very clear and detailed tutorial of FunSuite
    *
    * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
    *
    * Operators
    *  - test
    *  - ignore
    *  - pending
    */

  /**
    * Tests are written using the "test" operator and the "assert" method.
    */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
    * For ScalaTest tests, there exists a special equality operator "===" that
    * can be used inside "assert". If the assertion fails, the two values will
    * be printed in the error message. Otherwise, when using "==", the test
    * error message will only say "assertion failed", without showing the values.
    *
    * Try it out! Change the values so that the assertion fails, and look at the
    * error message.
    */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
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
    * val s1 = singletonSet(1)
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
    val positive: Set = (x: Int) => x > 0
    val negative: Set = (x: Int) => x < 0
    val aroundTen: Set = (x: Int) => (x <= 10) && (x >= -10)
  }

  /**
    * This test is currently disabled (by using "ignore") because the method
    * "singletonSet" is not yet implemented and the test would fail.
    *
    * Once you finish your implementation of "singletonSet", exchange the
    * function "ignore" by "test".
    */
  test("singletonSet(1) contains 1") {

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
      assert(contains(s2, 2), "Singleton")
      assert(contains(s3, 3), "Singleton")
      assert(!contains(s3, 4), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")

      val tenAndPositive = union(aroundTen, positive)
      assert(!contains(tenAndPositive, -11))
      assert(contains(tenAndPositive, 10))
      assert(contains(tenAndPositive, 999))
    }
  }

  test("intersect contains only elements that are in both sets") {
    new TestSets {
      val i1 = intersect(s1, s2)
      val i2 = intersect(s1, s1)
      assert(!contains(i1, 1), "Union 1")
      assert(!contains(i1, 2), "Union 1")
      assert(contains(i2, 1), "Union 2")
      assert(!contains(i2, 2), "Union 2")

      val tenPositive = intersect(positive, aroundTen)
      assert(!contains(tenPositive, 11), "Union 3")
      assert(!contains(tenPositive, 0), "Union 3")
      assert(contains(tenPositive, 1), "Union 3")
      assert(contains(tenPositive, 10), "Union 3")
    }
  }
  test("diff contains all elements that are in the first set but not the 2nd set") {
    new TestSets {
      val d1 = diff(s1, s2)
      val d2 = diff(s1, s1)
      assert(contains(d1, 1), "Union 1")
      assert(!contains(d1, 2), "Union 1")
      assert(!contains(d2, 1), "Union 2")
      assert(!contains(d2, 2), "Union 2")

      val tenPositive = diff(aroundTen, negative)
      assert(!contains(tenPositive, 11), "Union 3")
      assert(!contains(tenPositive, -5), "Union 3")
      assert(contains(tenPositive, 0), "Union 3")
      assert(contains(tenPositive, 1), "Union 3")
      assert(contains(tenPositive, 10), "Union 3")
      assert(contains(tenPositive, 5), "Union 3")
    }
  }

  test("for all works") {
    new TestSets {
      assert(forall(positive, x => x > 0))
      assert(forall(positive, x => x + 1 > x))

      assert(forall(aroundTen, x => math.abs(x) <= 10))
    }
  }
  test("exists works") {
    new TestSets {
      assert(exists(positive, x => x == 10))
      assert(!exists(positive, x => x == -10))
      assert(!exists(positive, x => x + 1 < 0))
      assert(exists(positive, x => x + 1 > 5))
    }
  }

  test("map works") {
    new TestSets {
      assert(!contains(map(positive, x => x + 1), 1))
      assert(contains(map(positive, x => x + 1), 2))
      assert(contains(map(positive, x => x + 1), 1000))

      assert(contains(map(aroundTen, x => x * 2), -20))
      assert(contains(map(aroundTen, x => x * 2), 20))
    }
  }

}
