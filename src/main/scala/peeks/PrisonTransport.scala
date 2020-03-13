package peeks

trait TreeNode {
  def value: Int
  def child: List[TreeNode]
  def isEmpty: Boolean
  def sum: Int = child.foldLeft(0)((acc, inm) => acc + inm.sum)
}
class Inmate(val value: Int) extends TreeNode {
  override def sum: Int = 1
  override def child: List[TreeNode] = Nil
  override def isEmpty: Boolean = true
}
class ForkInmate(val value: Int, val child: List[TreeNode]) extends TreeNode {
  override def isEmpty: Boolean = false
}
object PrisonTransport extends App {
  private val tree = new ForkInmate(
    1,
    List(new ForkInmate(4, List(new Inmate(2), new Inmate(3))))
  )
  println(tree.sum)
}
