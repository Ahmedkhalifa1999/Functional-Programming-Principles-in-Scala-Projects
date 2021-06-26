package recfun

import scala.annotation.tailrec

object RecFun extends RecFunInterface {

  def main(args: Array[String]): Unit = {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(s"${pascal(col, row)} ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c > r) throw new IllegalArgumentException("column value can't be larger than row value") else {
      if (c == 0 || c == r) 1 else pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }


  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def intBalance(count: Int, chars: List[Char]): Boolean = {
      if (chars.isEmpty) {
        if (count == 0) true else false
      }
      else{
        if (count == -1) false else {
          if (chars.head == '(') intBalance(count + 1, chars.tail)
          else if (chars.head == ')') intBalance(count - 1, chars.tail)
          else intBalance(count, chars.tail)
        }
      }
    }
    intBalance(count = 0, chars = chars)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty) 0 else {
      if (coins.head > money) countChange(money, coins.tail) else {
        countChange(money, coins.tail) + countChange(money-coins.head, coins) + (if (money == coins.head) 1 else 0)
      }
    }
  }
}
