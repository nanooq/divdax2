package layer3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import layer2.CSV;
import layer2.Layer2;

import org.jsoup.nodes.Document;

public class Layer3 {
	
	public static final String ddMMyyyy = "dd.MM.yyyy";
	public static final String yyyyMMdd = "yyyy-MM-dd";
	
	public static String download(String inStrURL, File inFile) throws IOException {
		Document aDoc = Layer2.read(inStrURL);
		return Layer2.write(inFile, aDoc);
	}
	
	public static String formatDate(Date inDate) {
		return Layer3.formatDate(inDate, Layer3.ddMMyyyy);
	}
	
	public static String formatDate(Date inDate, String inPattern) {
		return new SimpleDateFormat(inPattern).format(inDate);
	}

	public static String formatDate(String inValue, String inPatternIn, 
			String inPatternOut) {
		String formattedString = "";
		try{
			formattedString = new SimpleDateFormat(inPatternOut).format(
					new SimpleDateFormat(inPatternIn).parse(inValue))
					.toString();			
		} catch (ParseException e) {
			formattedString  = inValue;
		}
		return formattedString;
	}
	
	/**
	 * Read file and return Jsoup.Document
	 * @param inFile
	 * @param inStrBaseURI
	 * @return
	 * @throws IOException
	 */
	public static Document readDocument(File inFile, String inStrBaseURI) throws IOException {
		Document readDocument = null;
		if (Layer2.exists(inFile)) {
			readDocument = Layer2.parse(inFile, inStrBaseURI);
		} else {
			throw new FileNotFoundException();
		}
		return readDocument;
	}

	/**
	 * Read URL from inStrURL and write to file
	 * @param inStrURL
	 * @param inFile
	 * @return
	 * @throws IOException
	 */
	public static Document readDocument(String inStrURL, File inFile) throws IOException {
		Document readDocument = null;
		readDocument = Layer2.read(inStrURL);
		Layer2.write(inFile, readDocument);
		return readDocument;
	}

	public static String stampToday() {
		return Layer3.formatDate(new Date(), Layer3.yyyyMMdd);
	}
	
	public static Date toDate(String inString) {	
		 Date d1 = null;
		try {
			SimpleDateFormat formatter=new SimpleDateFormat(Layer3.ddMMyyyy);
	        d1 = formatter.parse(inString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d1;
	}

	public static Date toDateyyyyMMdd(String inString) {	
		 Date d1 = null;
		try {
			SimpleDateFormat formatter=new SimpleDateFormat(Layer3.yyyyMMdd);
	        d1 = formatter.parse(inString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d1;
	}

	public static String write(CSV inCSV) throws IOException {
		return Layer2.write(inCSV);		
	}

	public static String write(File inFile, ArrayList<MyMap> inRows) throws IOException {
		return Layer3.write(CSV.from(inFile, inRows));
	}
}