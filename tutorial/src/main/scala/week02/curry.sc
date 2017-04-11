def cube(a: Int): Int = a * a * a

def sum(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc + f(a))
  }

  loop(a, 0)
}

def prod(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc * f(a))
  }

  loop(a, 1)
}


def factorial(x: Double): Double =
  if (x == 1) 1 else (x * factorial(x - 1))

def chain(f: (Int, Int) => Int)(g: (Int) => Int)(a: Int, b: Int): Int = {
  if (a < b) f(chain(f)(g)(a + 1, b), g(a)) else g(a)
}

def chainTail(f: (Int, Int) => Int)(start: Int)(g: (Int) => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, f(acc, g(a)))
  }

  loop(a, start)
}


sum(cube)(1, 5)
chain((a: Int, b: Int) => a + b)(cube)(1, 5)
chainTail((a: Int, b: Int) => a + b)(0)(cube)(1, 5)

prod(cube)(1, 4)
chain((a: Int, b: Int) => a * b)(cube)(1, 4)
chainTail((a: Int, b: Int) => a * b)(1)(cube)(1, 4)
