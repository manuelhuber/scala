object factorial {

  def factorial(x: Double): Double =
    if (x == 1) 1 else (x * factorial(x - 1))

  def tailFactorial(x: Double): Double = {
    def foo(x: Double, total: Double): Double =
      if (x == 0) total else foo(x - 1, total * x)

    foo(x, 1)
  }

  factorial(5)
  tailFactorial(0)
  tailFactorial(1)
  tailFactorial(2)
  tailFactorial(3)
  tailFactorial(4)
  tailFactorial(5)

}
