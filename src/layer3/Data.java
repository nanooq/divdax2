package layer3;

public class Data {
	
	public enum DT {
		STR, STRX, FLT, DATE
	}

	
	private DT type = null;
	private String value = null;

	public Data(String inValue) {
		this(DT.STR, inValue);
	}
	
	public Data(DT inDT, String inValue) {
		this.setDataType(inDT);
		this.setValue(this.checkValue(inDT, inValue));
	}
	
	private String checkValue(DT inDT, String inValue) {
		if (inValue == null
				|| inValue.isEmpty()) {
			inValue = "";
			throw new NullPointerException();
		} else {
			switch (inDT) {
			case STRX : inValue = inValue.replaceAll("[^a-zA-Z0-9 :.,]", "");
				break;
			case DATE : inValue = Layer3.formatDate(Layer3.toDate(inValue));
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

	private DT getDataType() {
		return this.type;
	}

	private String setValue(String inString) {
		this.value  = inString;
		return this.getValue();
	}
	
	private String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
}