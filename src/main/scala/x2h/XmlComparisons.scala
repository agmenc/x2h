package x2h

import scala.xml.{Elem, NodeSeq}
import x2h.XmlPimps._

trait XmlComparisons {
  def normalise(xml: NodeSeq): NodeSeq = xml.traverse(filterOut = emptyText).to[List] match {
    case e @Elem(_, _, _, _, _) :: Nil => e
    case _ => xml
  }

  implicit class MatchableXml(xml: NodeSeq) {
    def =*=(that: NodeSeq) = normalise(xml) == normalise(that)
  }
}