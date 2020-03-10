def listReplication(num: Int, arr: List[Int]): List[Int] =
  arr.flatMap(List.fill(num)(_))

def f(arr: List[Int]): List[Int] =
  arr.drop(1).sliding(1, 2).flatten.toList

def arrayOfNElements(n: Int): List[Int] =
  List.range(0, n)

def reverseAList(arr: List[Int]): List[Int] =
  arr.reverse

def sumOfOddNumbers(arr: List[Int]): Int =
  arr.filter(_ % 2 != 0).sum

def listLength(arr: List[Int]): Int =
  arr.foldLeft(0)((x, _) => x + 1)

def updateList(arr: List[Int]): List[Int] =
  arr.map(math.abs)

def evaluatingE(x: Double, count: Int = 9): Double = {
  @scala.annotation.tailrec
  def fact(digit: Int, acc: Int = 1): Int =
    if (digit <= 1) acc
    else fact(digit - 1, acc * digit)

  if (count == 0) 1
  else math.pow(x, count) / fact(count) + evaluatingE(x, count - 1)
}

def areaUnderCurves(coefficients:List[Int],powers:List[Int],x:Double):Double =
  coefficients.zip(powers)
  .foldLeft(0.0){case (acc, (c, p)) => acc + c * math.pow(x, p)}

def area(coefficients:List[Int],powers:List[Int],x:Double):Double =
  math.Pi + math.pow(areaUnderCurves(coefficients, powers, x), 2)

def summation(func:(List[Int],List[Int],Double)=>Double,
              upperLimit:Int,
              lowerLimit:Int,
              coefficients:List[Int],
              powers:List[Int]):Double =
{
  val step = 0.001
  val interval = BigDecimal(lowerLimit) to BigDecimal(upperLimit) by BigDecimal(step)
  interval.foldLeft(0.0){case (acc, x) => acc + func(coefficients, powers, x.toDouble)*step}
}

