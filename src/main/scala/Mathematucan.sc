def factorial(x: Int): Int = {
  @scala.annotation.tailrec
  def fact(x: Int, accum: Int): Int = {
    if (x <= 1) accum
    else fact(x - 1, x * accum)
  }
  fact(x, 1)
}

factorial(3)
factorial(4)
factorial(5)
factorial(6)

val numbers = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
val res = numbers.foldRight(0)((m, n) => m - n)