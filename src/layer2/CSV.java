package layer2;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import layer3.Data;
import layer3.MyMap;
import layer3.Stock;

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
	
	public CSV(File inFile, ArrayList<MyMap> inMap) {
		this.setFile(inFile);
		this.setHeader(inMap);
		this.setLines(new ArrayList<String[]>(), inMap);
	}

	private ArrayList<String[]> setLines(ArrayList<String[]> inLines, ArrayList<MyMap> inMap) {
		this.setLines(new ArrayList<String[]>());
		if (inLines.isEmpty()){
			for (MyMap aRow : inMap) {
				this.add(aRow);
			}			
		} else {
			throw new RuntimeException("CSV.setLines - unexpected");
		}
		return this.getLines();
	}

	private String[] setHeader(ArrayList<MyMap> inMap) {
		String[] header = null;
		if (inMap.isEmpty()) {
			throw new RuntimeException("wat?");
		} else {
			Set<Stock> keys = inMap.get(0).keySet();
			ArrayList<String> strings = new ArrayList<String>();
			for (Stock aStock : keys) {
				strings.add(aStock.toString());
			}
			String[] dummy = new String[0];
			header = strings.toArray(dummy); 
		}
		return this.setHeader(header);
	}

	public static CSV from(File inFile, ArrayList<MyMap> inRows) {
		Set<Stock> stocks = inRows.get(0).keySet();
		for(MyMap aRow : inRows) {
			Set<Stock> otherStocks = aRow.keySet();
			for ( Stock anotherStock : otherStocks) {
				if (stocks.contains(otherStocks)){
					stocks.add(anotherStock);
				}				
			}
		}
		String[] strings = (String[]) stocks.toArray();
		CSV newCSV = new CSV(inFile, strings);
		for (MyMap aRow : inRows) {
			newCSV.add(aRow);			
		}
		return newCSV;
	}

	private boolean add(MyMap aRow) {
		ArrayList<String> strDatas = new ArrayList<String>();
		Data aData = null;
		for (String aColumn : this.getHeader()) {
			aData = aRow.get(Stock.valueOf(aColumn));
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