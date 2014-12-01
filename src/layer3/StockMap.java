package layer3;

import java.util.HashMap;
import java.util.Set;

import layer3.Data.DT;

public class StockMap {
	
	private HashMap<StockAttribute, Data> aMap = null;
	
	public StockMap() {
		this.setMap(new HashMap<StockAttribute, Data>());
	}

	private HashMap<StockAttribute, Data> getMap() {
		return this.aMap;
	}

	public Data put(DT inDT, StockAttribute inStock, String inValue) {
		Data newData = null;
		try {
			newData = new Data(inDT, inValue);
			this.put(inStock, new Data(inDT, inValue));
		} catch (NullPointerException e) {
			;
		}
		return newData;
	}
	
	private Data put(StockAttribute inStock, Data inData){
		return this.getMap().put(inStock, inData);
	}
	
	private HashMap<StockAttribute, Data> setMap(HashMap<StockAttribute, Data> inMap) {
		this.aMap = inMap;
		return this.getMap();
	}

	@Override
	public String toString() {
		StringBuilder aSB = new StringBuilder();
		aSB.append("{");
		String prefix = "";
		Set<StockAttribute> keys = this.getMap().keySet();
		for ( StockAttribute aStock : keys ) {
			aSB.append(prefix);
			prefix = ", ";
			aSB.append(aStock + " = " + this.getMap().get(aStock));
		}		
		aSB.append("}");
		return aSB.toString();
	}

	public Data put(StockAttribute inStock, String inValue) {
		return this.put(DT.STR, inStock, inValue);
	}

	public boolean isEmpty() {
		return this.getMap().isEmpty();
	}

	public Set<StockAttribute> keySet() {
		return this.aMap.keySet();
	}

	public Data get(StockAttribute inStock) {
		return this.getMap().get(inStock);
	}

	public Data get(String aKey) {
		StockAttribute aStock = StockAttribute.valueOf(aKey);
		return this.get(aStock);
	}
}