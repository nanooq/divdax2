package test;

import java.util.ArrayList;

import layer3.Stock;
import layer3.Sources_DivChe;

import org.jsoup.nodes.Document;

public class DayMap {

	private ArrayList<Stock> stocks = null;

	public DayMap(Document inDocument) {
		this.setStocks(Sources_DivChe.extractStocks(inDocument));
	}

	private ArrayList<Stock> setStocks(ArrayList<Stock> inStockMap) {
		this.stocks  = inStockMap;
		return this.getStocks();
	}

	public ArrayList<Stock> getStocks() {
		return this.stocks;
	}
}
