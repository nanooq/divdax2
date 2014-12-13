package layer3;

public class Data {
	
	private DT type = null;
	private String value = null;

	public Data(String inValue) {
		this(DT.STR, inValue);
	}
	
	public Data(DT inDT, String inValue) {
		this.setDataType(inDT);
		this.setValue(this.formatValue(inDT, inValue));
	}
	
	public String formatValue(DT inDT, String inValue) {
		if (inValue == null
				|| inValue.isEmpty()) {
			inValue = null;
		} else {
			switch (inDT) {
			// Alphanumeric
			case AN : inValue = inValue.replaceAll("[^a-zA-Z0-9]", "");
			// Alphanumeric and ; . ,
			case STRX : inValue = inValue.replaceAll("[^a-zA-Z0-9:.,]", "");
				break;
			case DATE : inValue = Layer3.formatDate(inValue, Layer3.ddMMyyyy, Layer3.yyyyMMdd);
				break;
			default:
				break;
			}
		}
		return inValue;
	}

	private DT setDataType(DT inDataType) {
		this.type = inDataType;
		return this.getDataType();
	}

	public DT getDataType() {
		return this.type;
	}

	private String setValue(String inString) {
		this.value  = inString;
		return this.getValue();
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

	public boolean isString() {
		boolean isString = false;
		if ( 
				this.getDataType().equals(DT.AN) ||
				this.getDataType().equals(DT.STR) ||
				this.getDataType().equals(DT.STRX)) 
		{
			isString = true;
		}
		return isString;
	}
}