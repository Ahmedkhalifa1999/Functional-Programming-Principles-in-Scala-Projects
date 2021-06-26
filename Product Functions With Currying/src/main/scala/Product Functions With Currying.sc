def product(f: Int => Int)(a : Int, b: Int) : Int = {
  if (a > b) 1 else f(a) * product(f)(a+1, b)
}

def factorial(a : Int) : Int = product(x => x)(1,a)

def mapReduce(f : Int => Int, combine: (Int,Int) => Int, zero : Int)(a : Int, b : Int) : Int = {
  if (a > b) zero else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))
}


