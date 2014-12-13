package layer3;

import java.util.ArrayList;

import org.jsoup.nodes.Document;

public class SourceDocument {

	private Document aDocument;
	private Source source;

	public SourceDocument(Document inDocument, Source inSource) {
		this.setDocument(inDocument);
		this.setSource(inSource);
	}

	private Source setSource(Source inSource) {
		this.source=inSource;
		return this.getSource();
	}

	private Source getSource() {
		return this.source;
	}

	private Document setDocument(Document inDocument) {
		this.aDocument = inDocument; 
		return this.getDocument();
	}

	private Document getDocument() {
		return this.aDocument;
	}

	public static ArrayList<Stock> extractStocks(SourceDocument inSourceDocument) {		
		ArrayList<Stock> stocks = null;
		stocks = inSourceDocument.getSource().extractStocks(inSourceDocument.getDocument());
		return stocks;
	}

}
