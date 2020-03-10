def super_digit(n: String, k: Int) = {
  def reducer(digit: String) =
    digit.split("").map(_.toLong).sum

  @scala.annotation.tailrec
  def simplify(digit: Long): Long =
    if (digit < 10) digit
    else simplify(reducer(digit.toString))

  simplify(k * reducer(n))
}

def kmpSearch2(text: String, pattern: String): Boolean = {
  @scala.annotation.tailrec
  def prefix(subText: List[Char], headText: List[Char] = Nil, acc: Int = 0, pi: List[Int] = Nil): List[Int] =
    subText match {
      case t :: ts =>
        headText match {
          case _ :: _ =>
            if (t == headText(acc)) prefix(ts, headText ++ List(t), acc + 1, pi ++ List(acc + 1))
            else if (acc == 0) prefix(ts, headText ++ List(t), acc, pi ++ List(acc))
            else prefix(subText, headText, pi.head, pi)
          case Nil => prefix(ts, headText ++ List(t), 0, pi ++ List(0))
        }
      case Nil => pi
    }

  prefix(s"$pattern@$text".toList).contains(pattern.length)
}

def kmp_search3(text: String, pattern: String): Boolean = {
  val checkerString = pattern + "@" + text
  val length = checkerString.length
  val array: Array[Int] = new Array(length)

  @scala.annotation.tailrec
  def innerFindPrefix(start: Int, end: Int, acc: Int = 0): Array[Int] = {
    if (end == length) array
    else if (checkerString(start) == checkerString(end)) {
      array(end) = acc + 1
      innerFindPrefix(start + 1, end + 1, acc + 1)
    }
    else if (start == 0) {
      array(end) = 0
      innerFindPrefix(start - acc, end + 1)
    }
    else {
      innerFindPrefix(array(start - 1), end, array(start - 1))
    }
  }
  pattern.length <= text.length &&
    innerFindPrefix(0, 1).contains(pattern.length)
}

def mcs(points: List[(Int, Int)]): List[(Int, Int)] = {
  def isBluntAngle(startPoint: (Int, Int), endPoint: (Int, Int), point: (Int, Int)): Boolean =
    rotate(startPoint, endPoint, point) <= 0

  def rotate(startPoint: (Int, Int), endPoint: (Int, Int), point: (Int, Int)): Int = {
    def vectorCoord(startPoint: (Int, Int), endPoint: (Int, Int)): (Int, Int) =
      (endPoint._1 - startPoint._1, endPoint._2 - startPoint._2)

    val (vectorX, vectorY) = vectorCoord(startPoint, endPoint)
    val (pointX, pointY) = vectorCoord(startPoint, point)

    vectorX * pointY - vectorY * pointX
  }

  @scala.annotation.tailrec
  def findPeeks(points: List[(Int, Int)], peeks: List[(Int, Int)]): List[(Int, Int)] = {
    points match {
      case x :: xs =>
        if (isBluntAngle(peeks.tail.head, peeks.head, x)) findPeeks(points, peeks.tail)
        else findPeeks(xs, x :: peeks)
      case Nil => peeks
    }
  }

  points match {
    case _ :: _ :: _ =>
      val sortedPoints = points.sorted((p1: (Int, Int), p2: (Int, Int)) => p1._1 - p2._1)
      val startPoint = sortedPoints.head
      val lastPoints = sortedPoints.tail.sorted((p1, p2) => rotate(startPoint, p1, p2))

      findPeeks(lastPoints.tail, List(startPoint, lastPoints.head))
    case _ => List()
  }
}

def perimeter(peeks: List[(Int, Int)]): Double = {
  def dist(p1: (Int, Int), p2: (Int, Int)): Double =
    math.sqrt((p1._1 - p2._1) ^ 2 + (p1._2 + p2._2) ^ 2)

  val lines = peeks zip (peeks.tail ++ List(peeks.head))
  println(lines)
  lines.map(line => dist(line._1, line._2)).sum
}

val points = mcs(
  List(
    (1, 1),
    (2, 5),
    (3, 3),
    (5, 3),
    (3, 2),
    (2, 2)
  )
)

perimeter(points)