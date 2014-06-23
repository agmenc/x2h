XML to Human
============

Copy the X2H pimp into your codebase somewhere (!) and `import x2h.X2H._`.

### `X2H.xml`
Strip namespace bumf from messy XML:
* before: ```<xml xmlns:bob="some/irrelevant/uri"><bob:name>Some name element</bob:name></xml>```
* after: ```<xml><name>Some name element</name></xml>```

### `X2H.humanString`
Print human-readable documents:
* before:
```
<BlockAllocation>
  <partyTradeIdentifier>
    <fpml:partyReference href="DTCC00001111">UNUSED</fpml:partyReference>
    <fpml:tradeId tradeIdScheme="TradeRefNbr">TRADEREF0005</fpml:tradeId>
  </partyTradeIdentifier>
</BlockAllocation>
```
* after:
```
BlockAllocation
  partyTradeIdentifier
    partyReference[DTCC00001111]: UNUSED
    tradeId: TRADEREF0005
```

