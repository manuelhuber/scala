def cube(a: Int): Int = a * a * a

// Sums the results of f(x) for x values from a up to (and including) b
def sum(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc + f(a))
  }

  loop(a, 0)
}

// multiplies the results of f(x) for x values from a up to (and including) b
def prod(f: Int => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, acc * f(a))
  }

  loop(a, 1)
}

// Reduces the values of f(x) for a<=x<=b with the given function
def mapReduce(reduce: (Int, Int) => Int)(map: (Int) => Int)(a: Int, b: Int): Int = {
  if (a < b) reduce(map(a), mapReduce(reduce)(map)(a + 1, b)) else map(a)
}
// mapReduce(add)(x => x)(1,4)
// add(1,mapReduce(add)(x => x)(2,4))
// add(1,add(2,mapReduce(add)(x => x)(4,4)))
// add(1,add(2,add(3,mapReduce(add)(x => x)(4,4))))
// add(1,add(2,add(3,4)))


// Reduces the values of f(x) for a<=x<=b with the given function tail recursively. Also needs a starting value
def mapReduceTailRecursion(reduce: (Int, Int) => Int)(start: Int)(map: (Int) => Int)(a: Int, b: Int): Int = {
  def loop(a: Int, acc: Int): Int = {
    if (a > b) acc
    else loop(a + 1, reduce(acc, map(a)))
  }

  loop(a, start)
}
// mapReduceTailRecursion(add)(0)(x => x)(1,4)
// loop(1,0)
// loop(1+1,add(0,1))
// loop(2+1,add(1,2))
// loop(3+1,add(3,3))
// loop(4+1,add(6,4))
// 10

sum(cube)(1, 5)
mapReduce((a: Int, b: Int) => a + b)(cube)(1, 5)
mapReduceTailRecursion((a: Int, b: Int) => a + b)(0)(cube)(1, 5)

prod(cube)(1, 4)
mapReduce((a: Int, b: Int) => a * b)(cube)(1, 4)
mapReduceTailRecursion((a: Int, b: Int) => a * b)(1)(cube)(1, 4)
