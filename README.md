XML to Human
============

### To use
Copy the X2H pimp into your codebase somewhere (!) and `import x2h.X2H._`.

### `X2H.xml`
Strips namespace bumf from messy XML
##### before:
```<xml xmlns:bob="some/irrelevant/uri"><bob:name>Some name element</bob:name></xml>```
##### after:
```<xml><name>Some name element</name></xml>```
##### example:
```scala
import x2h.X2H._
val yuckyXml =
    <xml xmlns:bob="some/irrelevant/uri">
      <bob:name>Some name element</bob:name>
    </xml>

assert(yuckyXml.xml =*= <xml><name>Some name element</name></xml>)
```


### `X2H.humanString`
Creates human-readable documents:
##### before:
```
<BlockAllocation>
  <partyTradeIdentifier>
    <fpml:partyReference href="DTCC00001111">UNUSED</fpml:partyReference>
    <fpml:tradeId tradeIdScheme="TradeRefNbr">TRADEREF0005</fpml:tradeId>
  </partyTradeIdentifier>
</BlockAllocation>
```
##### after:
```
BlockAllocation
  partyTradeIdentifier
    partyReference[DTCC00001111]: UNUSED
    tradeId: TRADEREF0005
```
##### example:
```scala
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

  assert(fpml.humanString == humanisedFpml)
```
