package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import layer2.Layer2;
import layer3.Layer3;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class TestHTMLDayMap {
	
	/*
	 * Read a HTML and turn it into a DayMap
	 */
	
	private String URL = "http://www.dividendenchecker.de/dividende_dax.php";
	private String BASEURI = "http://www.dividendenchecker.de/";
	private File FILE = new File("testfile.delete.me");
	public static String LOCATION = "test.stockmanager.deleteme";
	
	@Test
	public void testReadHTML() {
		// Load HTML and turn it to document
		Document aDocument = null;
		try {
			if (Layer2.isOnline()) {
				aDocument = Layer3.readDocument(URL, FILE);
			} else {
				aDocument = Layer3.readDocument(FILE, BASEURI);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Turn document into stockDayMap
		DayMap aDay = new DayMap(aDocument);
		assertNotNull(aDay);
		
		// Feed DayMap into StockManager
		StockManager aStockManager = null;
		try {
			aStockManager = StockManager.load(TestHTMLDayMap.LOCATION);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(aStockManager);
		aStockManager.update(aDay);
	}
}