package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import layer2.Layer2;
import layer3.DayMap;
import layer3.Layer3;
import layer3.SourceDocument;
import layer3.Source_DivChe;
import layer3.StockManager;

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
		SourceDocument aSourceDocument = null;
		try {
			if (false /*Layer2.isOnline()*/) {
				aSourceDocument = Layer3.readDocument(URL, FILE, new Source_DivChe());
			} else {
				aSourceDocument = Layer3.readDocument(FILE, BASEURI, new Source_DivChe());
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Turn document into stockDayMap
		DayMap aDay = new DayMap(aSourceDocument);
		assertNotNull(aDay);
		
		// Feed DayMap into StockManager
		StockManager aStockManager = null;
		try {
			aStockManager = StockManager.load(TestHTMLDayMap.LOCATION);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(aStockManager);
		
		boolean isUpdated = false;
		assertFalse(isUpdated);
		isUpdated = aStockManager.update(aDay);
		assertTrue(isUpdated);
	}
}