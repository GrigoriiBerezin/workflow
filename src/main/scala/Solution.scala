object Solution {
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
      math.sqrt(math.pow(p1._1 - p2._1, 2) + math.pow(p1._2 - p2._2, 2))

    val lines = peeks zip (peeks.tail ++ List(peeks.head))
    println(lines)
    lines.map(line => dist(line._1, line._2)).sum
  }

  def main(args: Array[String]): Unit = {
//    val n = readInt()
    val points = (1 to 2).map(_ => "2 4".split(" ").map(d => d.toInt).toList).toList
    println(points)
//    val peeks = mcs(points)
//    println(peeks)
//    val per = perimeter(peeks)
//    println(per)
  }
}