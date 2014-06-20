package x2h

import scala.xml._
import x2h.XmlPimps._

case class X2H(uglyXml: NodeSeq) {
  def xml: NodeSeq = uglyXml.traverse{ case e: Elem => e.copy(scope = TopScope, prefix = null) }
}

object XmlPimps {
  implicit class TraversableNodeSeq(ns: NodeSeq) {
    def traverse(transform: PartialFunction[Node, Node] = noChange, filterOut: PartialFunction[Node, Boolean] = all): NodeSeq = traverse(ns, transform, filterOut)

    // TODO - CAS - 18/06/2014 - how do I make identity a PartialFunction?
    private def noChange: PartialFunction[Node, Node] = {case n => n}
    private def all: PartialFunction[Node, Boolean] = { case _ => true }

//    @tailrec
    private final def traverse(ns: NodeSeq, transform: PartialFunction[Node, Node], nodeFilter: PartialFunction[Node, Boolean]): NodeSeq =
      ns filter (nodeFilter orElse all) map {(transform orElse noChange) andThen {
        case e: Elem => e.copy(child = traverse(e.child, transform, nodeFilter))
        case n => n
      }}
  }
}