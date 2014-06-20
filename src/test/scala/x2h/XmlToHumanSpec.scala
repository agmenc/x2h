package x2h

import org.scalatest.WordSpec
import scala.xml.Elem

class XmlToHumanSpec extends WordSpec with XmlComparisons {
  val yuckyXml =
    <xml xmlns:bob="some/irrelevant/uri">
      <bob:name>Some name element</bob:name>
    </xml>

  "We can strip namespaces out of XML" in {
    assert(X2H(yuckyXml).xml =*= <xml><name>Some name element</name></xml>)
  }

}