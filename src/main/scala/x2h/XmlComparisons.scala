package x2h

import scala.xml.{Text, Elem, NodeSeq}
import x2h.XmlPimps.TraversableNodeSeq

trait XmlComparisons {
  def normalise(xml: NodeSeq): NodeSeq = trimEmptyText(xml).to[List] match {
    case e @Elem(_, _, _, _, _) :: Nil => e
    case _ => xml
  }

  def trimEmptyText(xml: NodeSeq) = xml traverse(filterOut = {
    case Text(data) if data.trim.isEmpty => false
  })

  implicit class MatchableXml(xml: NodeSeq) {
    def =*=(that: NodeSeq) = normalise(xml) == normalise(that)
  }
}