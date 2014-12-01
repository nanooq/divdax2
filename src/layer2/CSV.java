package layer2;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import layer3.Data;
import layer3.StockMap;
import layer3.StockAttribute;

public class CSV implements CSVable {
	
	public static final String NL = System.lineSeparator();
	public static final String REGEX = ",";
	private File aFile = null;
	private String[] aHeader = null;
	private ArrayList<String[]> lines = null;
	
	public CSV(File inFile, String[] inHeader){
		this.setFile(inFile);
		this.setHeader(inHeader);
		this.setLines(new ArrayList<String[]>());
	}
	
	public CSV(File inFile, ArrayList<StockMap> inMap) {
		this.setFile(inFile);
		this.setHeader(inMap);
		this.setLines(new ArrayList<String[]>(), inMap);
	}

	private ArrayList<String[]> setLines(ArrayList<String[]> inLines, ArrayList<StockMap> inMap) {
		this.setLines(new ArrayList<String[]>());
		if (inLines.isEmpty()){
			for (StockMap aRow : inMap) {
				this.add(aRow);
			}			
		} else {
			throw new RuntimeException("CSV.setLines - unexpected");
		}
		return this.getLines();
	}

	private String[] setHeader(ArrayList<StockMap> inMap) {
		String[] header = null;
		if (inMap.isEmpty()) {
			throw new RuntimeException("wat?");
		} else {
			Set<StockAttribute> keys = inMap.get(0).keySet();
			ArrayList<String> strings = new ArrayList<String>();
			for (StockAttribute aStock : keys) {
				strings.add(aStock.toString());
			}
			String[] dummy = new String[0];
			header = strings.toArray(dummy); 
		}
		return this.setHeader(header);
	}

	public static CSV from(File inFile, ArrayList<StockMap> inRows) {
		Set<StockAttribute> stocks = inRows.get(0).keySet();
		for(StockMap aRow : inRows) {
			Set<StockAttribute> otherStocks = aRow.keySet();
			for ( StockAttribute anotherStock : otherStocks) {
				if (stocks.contains(otherStocks)){
					stocks.add(anotherStock);
				}				
			}
		}
		String[] strings = (String[]) stocks.toArray();
		CSV newCSV = new CSV(inFile, strings);
		for (StockMap aRow : inRows) {
			newCSV.add(aRow);			
		}
		return newCSV;
	}

	private boolean add(StockMap aRow) {
		ArrayList<String> strDatas = new ArrayList<String>();
		Data aData = null;
		for (String aColumn : this.getHeader()) {
			aData = aRow.get(StockAttribute.valueOf(aColumn));
			strDatas.add(aData.toString());
		} 
		String[] arrString = new String[this.getHeader().length];
		return this.getLines().add(strDatas.toArray(arrString));
	}

	public boolean add(String aLine, String inRegex) {
		return this.getLines().add(aLine.split(inRegex));		
	}

	@Override
	public String getBody() {
		ArrayList<String[]> lines = this.getLines();
		StringBuilder aSB = new StringBuilder();
		for ( String[] aLine : lines ) {
			aSB.append(this.toRow(aLine));
		}
		return aSB.toString();
	}
	
	@Override
	public String getContent() {
		StringBuilder aSB = new StringBuilder();
		aSB.append(this.toRow(this.getHeader()));
		aSB.append(this.getBody());
		return aSB.toString();
	}
	
	@Override
	public File getFile() {
		return this.aFile;
	}

	@Override
	public String[] getHeader(){
		return this.aHeader;
	}

	private ArrayList<String[]> getLines() {
		return this.lines;
	}

	private File setFile(File inFile) {
		this.aFile = inFile;
		return this.getFile();
	}

	private String[] setHeader(String[] inHeader) {
		this.aHeader  = inHeader;
		return this.getHeader();
	}

	private ArrayList<String[]> setLines(ArrayList<String[]> inLines) {
		this.lines = inLines;
		return this.getLines();
	}

	private String toRow(String[] inLine) {
		StringBuilder aSB = new StringBuilder();
		for ( int i = 0 ; i < inLine.length ; i++) {
			aSB.append(inLine[i] + CSV.REGEX);
		}
		aSB.append(CSV.NL);
		return aSB.toString();
	}
	
	@Override
	public String toString() {
		return this.getContent().trim();
	}
}