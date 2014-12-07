package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import layer3.Layer3;
import layer3.StockAttribute;
import layer3.Stock;

/**
 * A StockManager only holds filenames to stock files, which it is suppose to manage.
 * 
 * @author lora
 *
 */
public class StockManager {
	
	public static String LOCATION = "test.stockmanager.deleteme";
	
	private ArrayList<String> strFileStocks = null;

	public StockManager() {
		this.setStrFileStocks(new ArrayList<String>());
	}
	
	public StockManager(ArrayList<String> inFileStocks) {
		this.setStrFileStocks(inFileStocks);
	}

	private ArrayList<String> setStrFileStocks (ArrayList<String> inFileStocks) {
		this.strFileStocks = inFileStocks;
		return this.getStrFileStocks();		
	}

	private ArrayList<String> getStrFileStocks() {
		return this.strFileStocks;
	}
	
	public static StockManager load() throws FileNotFoundException {
		return StockManager.load(StockManager.LOCATION);
	}

	public static StockManager load(String inStrFileStock) throws FileNotFoundException {
		StockManager loadedStockManager = new StockManager(new ArrayList<String>());
		File fileStockManager = new File(inStrFileStock);
		if (fileStockManager.exists()) {
			try {
				loadedStockManager = StockManager.createFrom(Layer3.readCSV(inStrFileStock));				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			loadedStockManager = new StockManager();
		}
		return loadedStockManager;
	}

	private static StockManager createFrom(ArrayList<String> readCSV) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(DayMap aDay) {
		ArrayList<Stock> stocks = aDay.getStocks();
		for ( Stock aStock : stocks ) {
			this.update(aStock);
		}
	}

	private boolean update(Stock inStock) {
		boolean isUpdated = false;
		String strFileStock = inStock.get(StockAttribute.strFile).getValue();
		if (this.getStrFileStocks().contains(strFileStock)) {
			Stock savedStock = this.readFromFile(strFileStock);
			isUpdated = savedStock.update(inStock);
		}
		return isUpdated;
	}

	private Stock readFromFile(String inStrFileStock) {
		Stock readStock = null;
		try {
			readStock = Stock.createFrom(Layer3.readCSV(inStrFileStock));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readStock;
	}
}