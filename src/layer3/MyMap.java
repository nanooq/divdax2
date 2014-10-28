package layer3;

import java.util.HashMap;
import java.util.Set;

import layer3.Data.DT;

public class MyMap {
	
	private HashMap<Stock, Data> aMap = null;
	
	public MyMap() {
		this.setMap(new HashMap<Stock, Data>());
	}

	private HashMap<Stock, Data> getMap() {
		return this.aMap;
	}

	public Data put(DT inDT, Stock inStock, String inValue) {
		Data newData = null;
		try {
			newData = new Data(inDT, inValue);
			this.put(inStock, new Data(inDT, inValue));
		} catch (NullPointerException e) {
			;
		}
		return newData;
	}
	
	private Data put(Stock inStock, Data inData){
		return this.getMap().put(inStock, inData);
	}
	
	private HashMap<Stock, Data> setMap(HashMap<Stock, Data> inMap) {
		this.aMap = inMap;
		return this.getMap();
	}

	@Override
	public String toString() {
		StringBuilder aSB = new StringBuilder();
		aSB.append("{");
		String prefix = "";
		Set<Stock> keys = this.getMap().keySet();
		for ( Stock aStock : keys ) {
			aSB.append(prefix);
			prefix = ", ";
			aSB.append(aStock + " = " + this.getMap().get(aStock));
		}		
		aSB.append("}");
		return aSB.toString();
	}

	public Data put(Stock inStock, String inValue) {
		return this.put(DT.STR, inStock, inValue);
	}

	public boolean isEmpty() {
		return this.getMap().isEmpty();
	}

	public Set<Stock> keySet() {
		return this.aMap.keySet();
	}

	public Data get(Stock inStock) {
		return this.getMap().get(inStock);
	}

	public Data get(String aKey) {
		Stock aStock = Stock.valueOf(aKey);
		return this.get(aStock);
	}
}