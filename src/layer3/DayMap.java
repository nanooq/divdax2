package layer3;

import java.util.ArrayList;

public class DayMap {

	private ArrayList<Stock> stocks = null;

	public DayMap(SourceDocument inSourceDocument) {
		this.setStocks(SourceDocument.extractStocks(inSourceDocument));
	}

	private ArrayList<Stock> setStocks(ArrayList<Stock> inStockMap) {
		this.stocks  = inStockMap;
		return this.getStocks();
	}

	public ArrayList<Stock> getStocks() {
		return this.stocks;
	}
}
