package layer3;

import java.util.ArrayList;

import org.jsoup.nodes.Document;

public abstract class Source {
	
	public abstract ArrayList<Stock> extractStocks(Document inDocument);

}
