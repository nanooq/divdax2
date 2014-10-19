package layer2;

import java.io.File;
import java.util.ArrayList;

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
}