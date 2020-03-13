val divide = new PartialFunction[Int, Int] {
  override def isDefinedAt(x: Int) = x != 0

  override def apply(v1: Int) = 42 / v1
}

val notDivide = new PartialFunction[Int, Int] {
  override def isDefinedAt(x: Int) = x != 0

  override def apply(v1: Int) = -1
}

val mock = new PartialFunction[Int, Int] {
  override def isDefinedAt(x: Int) = true

  override def apply(v1: Int) = -100
}

val newDivide = divide
newDivide.lift(0)
val number = newDivide.lift
number(0)

val incrementor = new PartialFunction[Int, Int] {
  override def isDefinedAt(x: Int) = x >= 0

  override def apply(v1: Int) = v1 + 1
}

val shell = incrementor.lift
shell(-1)
shell(0)
shell(100)

val range = Range(-10, 10)
//range map divide
range map divide.lift
range collect divide

scala.concurrent.ExecutionContext.global