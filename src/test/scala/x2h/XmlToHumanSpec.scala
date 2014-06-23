package x2h

import org.scalatest.WordSpec
import x2h.X2H._

class XmlToHumanSpec extends WordSpec with XmlComparisons {
  val yuckyXml =
    <xml xmlns:bob="some/irrelevant/uri">
      <bob:name>Some name element</bob:name>
    </xml>

  "We can strip namespaces out of XML" in {
    assert(yuckyXml.xml =*= <xml><name>Some name element</name></xml>)
  }

  val fpml =
    <BlockAllocation>
      <partyTradeIdentifier>
        <fpml:partyReference href="DTCC00001111">UNUSED</fpml:partyReference>
        <fpml:tradeId tradeIdScheme="TradeRefNbr">TRADEREF0005</fpml:tradeId>
      </partyTradeIdentifier>
      <AllocationCollateral></AllocationCollateral>
      <AllocationNotional>
        <fpml:currency>EUR</fpml:currency>
        <fpml:amount>2500000.00</fpml:amount>
      </AllocationNotional>
      <AllocationFee>
        <fpml:currency>EUR</fpml:currency>
        <fpml:amount>2000.00</fpml:amount>
      </AllocationFee>
    </BlockAllocation>

  val humanisedFpml =
    """
      |BlockAllocation
      |  partyTradeIdentifier
      |    partyReference[DTCC00001111]: UNUSED
      |    tradeId: TRADEREF0005
      |  AllocationCollateral
      |  AllocationNotional
      |    currency: EUR
      |    amount: 2500000.00
      |  AllocationFee
      |    currency: EUR
      |    amount: 2000.00""".stripMargin

  "We can display XML in a human-readable form" in {
    assert(fpml.humanString == humanisedFpml)
  }
}