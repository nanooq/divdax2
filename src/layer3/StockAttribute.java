package layer3;

public enum StockAttribute {
	isin("isin"),
	name("name"), 
	price("price"),
	yield("yield"),
	dividend13("dividend"),
	yield13("yield13"), 
	dividend14("dividend14"), 
	yield14("yield14"), 
	trend("trend"),
	dividendExpected("dividendExpected"),
	yieldExpected("yieldExpected"),
	asmExpected("asmExpected"),
	information("information"), 
	strFile("strFile");

	private String aString = null;
	
	private StockAttribute(String inString) {
		this.setString(inString);
	}
	
	private String getString() {
		return aString;
	}
	
	private String setString(String inString) {
		this.aString = inString;
		return getString();
	}
}