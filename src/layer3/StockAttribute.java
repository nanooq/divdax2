package layer3;

public enum StockAttribute {
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
	information("information");

	private StockAttribute(String inString) {
		this.setString(inString);
	}
	
	private String aString = null;
	
	private String setString(String inString) {
		this.aString = inString;
		return getString();
	}
	
	private String getString() {
		return aString;
	}
}
