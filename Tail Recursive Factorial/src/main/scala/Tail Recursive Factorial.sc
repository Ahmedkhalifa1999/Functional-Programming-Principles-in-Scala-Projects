import scala.annotation.tailrec


def factorial(n : Int) : Int = {
  @tailrec
  def loop(acc : Int, n : Int) : Int = {
    if (n == 0) acc else loop(acc*n, n-1)
  }
  loop(acc = 1 , n)
}

factorial(12)