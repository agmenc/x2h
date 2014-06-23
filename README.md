XML to Human
============

* Copy the X2H pimp into your codebase somewhere (!) and import it:
```scala
import x2h.X2H._
```
* Strip namespace bumf from messy XML with `X2H.xml`:
```scala
val yuckyXml = <xml xmlns:bob="some/irrelevant/uri"><bob:name>Some name element</bob:name></xml>

assert(yuckyXml.xml == <xml><name>Some name element</name></xml>)
```
* Print human-readable documents with `X2H.humanString`:
```scala
val yuckyXml =
<BlockAllocation>
  <partyTradeIdentifier>
    <fpml:partyReference href="DTCC00001111">UNUSED</fpml:partyReference>
    <fpml:tradeId tradeIdScheme="TradeRefNbr">TRADEREF0005</fpml:tradeId>
  </partyTradeIdentifier>
</BlockAllocation>

assert(yuckyXml.humanString ==
 """
       |BlockAllocation
       |  partyTradeIdentifier
       |    partyReference[DTCC00001111]: UNUSED
       |    tradeId: TRADEREF0005""".stripMargin
)
```