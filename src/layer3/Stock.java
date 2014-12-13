package layer3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import layer2.CSV;

/**
 * This class represents a stock.
 * 
 * @author lora
 *
 */
public class Stock {
	
	private HashMap<StockAttribute, Data> aMap = null;
	
	public Stock() {
		this.setMap(new HashMap<StockAttribute, Data>());
	}

	private HashMap<StockAttribute, Data> getMap() {
		return this.aMap;
	}

	public Data put(DT inDT, StockAttribute inStockAttribute, String inValue) {
		Data newData = null;
		newData = new Data(inDT, inValue);
		this.put(inStockAttribute, new Data(inDT, inValue));
		if (inStockAttribute == StockAttribute.isin) {
			this.put(StockAttribute.strFile, this.createFilename(inValue));
		}
		return newData;
	}
	
	private Data createFilename(String inValue) {
		return new Data(DT.STR, inValue + "." + CSV.FILETYPE);
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

	public static Stock createFrom(ArrayList<String> inCSVStrings) {
		//TODO
		System.out.println("PANIK");
		return null;
	}

	public boolean update(Stock inStock) {
		System.out.println("HELLO" + inStock);
		return false;
	}
}