package x2h

import scala.xml._
import x2h.XmlPimps._

case class X2H(uglyXml: NodeSeq) {
  def xml: NodeSeq = uglyXml traverse { case e: Elem => e.copy(scope = TopScope, prefix = null) }

  override def toString = xml.toHumanString

  def toHtml: NodeSeq = ??? // xml traverse ( filterOut = emptyText )
}

object XmlPimps {
  implicit class TraversableFilterableNodeSeq(ns: NodeSeq) {
    def traverse(transform: PartialFunction[Node, Node] = noChange, filterOut: PartialFunction[Node, Boolean] = all): NodeSeq = traverse(ns, transform, filterOut)
    def toHumanString: String = toHumanString(ns, 0)

    private def noChange: PartialFunction[Node, Node] = {case n => n}
    private def all: PartialFunction[Node, Boolean] = { case _ => true }

    private final def traverse(ns: NodeSeq, transform: PartialFunction[Node, Node], filterOut: PartialFunction[Node, Boolean]): NodeSeq =
      ns filter (filterOut orElse all) map {(transform orElse noChange) andThen {
        case e: Elem => e.copy(child = traverse(e.child, transform, filterOut))
        case n => n
      }}

    private final def toHumanString(ns: NodeSeq, depth: Int): String =
      ns map {
        case e: Elem => s"\n${indent(depth)}${e.label}${printAttributes(e.attributes)}${toHumanString(e.child, depth + 1)}"
        case Text(t) => if (t.trim.isEmpty) "" else s": $t"
        case x => s"Unrecognised element type: $x"
      } filter { s => s.trim.nonEmpty } mkString ""

    private def indent(depth: Int) = ((0 to depth).toList.tail map (i => "  ")).mkString

    private def printAttributes(attributes: MetaData) = attributes map {
      case UnprefixedAttribute(key, Text(data), _) if key contains "Scheme" => ""
      case UnprefixedAttribute(key, Text(data), _) if data.nonEmpty => s"[$data]"
      case _ => ""
    } mkString ""
  }

  def emptyText: PartialFunction[NodeSeq, Boolean] = {
    case Text(data) if data.trim.isEmpty => false
  }
}