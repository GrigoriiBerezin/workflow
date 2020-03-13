//package peeks
//
//import scala.io.StdIn.{readInt, readLine}
//
//case class Point(x: Int, y: Int)
//case class Line(start: Point, end: Point) {
//  val i: Int = end.x - start.x
//  val j: Int = end.y - start.y
//}
//
//object Solution {
//  type PointList = List[Point]
//
//  def mcs(points: PointList): PointList = {
//    def rotate(startPoint: Point, endPoint: Point, pointCheck: Point) = {
//      val v1 = Line(startPoint, endPoint)
//      val v2 = Line(startPoint, pointCheck)
//
//      v1.i * v2.j - v1.j * v2.i
//    }
//
//    @scala.annotation.tailrec
//    def findPeeks(points: PointList, peeks: PointList): PointList = {
//      points match {
//        case x :: xs =>
//          peeks match {
//            case p :: ps =>
//              if (rotate(p, ps.head, x) < 0) findPeeks(points, ps)
//              else findPeeks(xs, x :: peeks)
//            case _ => findPeeks(xs, x :: peeks)
//          }
//        case Nil => peeks
//      }
//    }
//
//    val sortedPoints =
//      points.sorted(Ordering.by[Point, Int](_.x).thenComparing(_.y))
//    sortedPoints match {
//      case p :: ps =>
//        val lastPoints = ps.sorted((p1, p2) => rotate(p, p1, p2))
//        lastPoints match {
//          case l :: ls => findPeeks(ls, List(l, p))
//        }
//      case _ => List()
//    }
//  }
//
//  def perimeter(peeks: PointList): Double = {
//    def dist(p1: Point, p2: Point): Double =
//      math.sqrt(math.pow(p1.x - p2.x, 2) + math.pow(p1.y - p2.y, 2))
//
//    val lines = peeks zip (peeks.tail ++ List(peeks.head))
//    lines.map(line => dist(line._1, line._2)).sum
//  }
//
//  def read(x: Int): PointList =
//    (1 to x)
//      .map(_ => {
//        val line = readLine().split("\\s").toList
//        Point(line.head.toInt, line.last.toInt)
//      })
//      .toList
//
//  def main(args: Array[String]): Unit = {
//    val inmatesNum = 1 to readInt()
//    val pairNum = readInt()
//    val list = read(pairNum)
//
//    val inmates: List[Int] = list.flatMap(inmate => inmate.x :: List(inmate.y))
//  }
//}
