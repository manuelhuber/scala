package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c > r) 0 else if (c == 0 || c == r) 1 else pascal(c, r - 1) + pascal(c - 1, r - 1)
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = {
    def step(chars: List[Char], counter: Int): Boolean = {
      def getCounter(): Int = if (chars.head == ')') counter - 1 else if (chars.head == '(') counter + 1 else counter

      if (counter < 0) false else if (chars.isEmpty) counter == 0 else step(chars.tail, getCounter())
    }

    step(chars, 0)
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {

    def checkAmount(money: Int, coins: List[Int], total: Int): Int = {
      if (money < 0) total else if (coins.isEmpty) if (money != 0) total else total + 1

      else checkAmount(money, coins.tail, total) + checkAmount(money - coins.head, coins, total)

    }

    if (money == 0 || coins.isEmpty) 0 else checkAmount(money, coins, 0)
  }
}
