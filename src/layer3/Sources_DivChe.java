package layer3;

import java.util.ArrayList;

import layer3.Data.DT;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Sources_DivChe {
	
	public static final ArrayList<String> SOURCES = Sources_DivChe.initSources();
	
	private static ArrayList<String> initSources() {
		ArrayList<String> sources = new ArrayList<String>();
		sources.add("http://www.dividendenchecker.de/dividende_dax.php");
		sources.add("http://www.dividendenchecker.de/dividende_mdax.php");
		sources.add("http://www.dividendenchecker.de/dividende_sdax.php");
		sources.add("http://www.dividendenchecker.de/dividende_tecdax.php");
		sources.add("http://www.dividendenchecker.de/dividende_okodax.php");
		return sources;
	}
	
	public static ArrayList<MyMap> extractData(Document inDocument) {
		ArrayList<MyMap> rows = new ArrayList<MyMap>();
		Element aTable = inDocument.getElementById("table-dax");
		Elements tableRows = aTable.getElementsByTag("tr");
		for (Element aRow : tableRows) {
			MyMap myMap = new MyMap();
			Elements rowDatas = aRow.getElementsByTag("td");
			Elements data = null;
			if (!rowDatas.isEmpty()) {
				data = rowDatas.get(0).getElementsByTag("a");
				if (! data.isEmpty()) {
					String Title = data.text();
					myMap.put(DT.STR, Stock.name, Title);
				}
				myMap.put(DT.FLT, Stock.price, rowDatas.get(1).text());
				myMap.put(DT.FLT, Stock.yield,  rowDatas.get(2).text().replace("%", ""));
				myMap.put(DT.FLT, Stock.dividend13, rowDatas.get(3).text());
				myMap.put(DT.FLT, Stock.yield13, rowDatas.get(4).text().replace("%", ""));
				myMap.put(DT.FLT, Stock.dividend14, rowDatas.get(5).text());
				myMap.put(DT.FLT, Stock.yield14, rowDatas.get(6).text().replace("%", ""));
				data = rowDatas.get(7).getElementsByTag("img");
				if (! data.isEmpty()) {
					myMap.put(DT.STR, Stock.trend, data.get(0).attr("alt"));				
				}
				myMap.put(DT.FLT, Stock.dividendExpected, rowDatas.get(8).text());
				myMap.put(DT.FLT, Stock.yieldExpected, rowDatas.get(9).text().replace("%", ""));
				myMap.put(DT.DATE, Stock.asmExpected, rowDatas.get(10).text());
				data = rowDatas.get(11).getElementsByTag("img");
				if (! data.isEmpty()) {
					myMap.put(DT.STRX, Stock.information, data.get(0)
							.attr("title")
							.replace("<br>", "")
							.replace("</br>", "")
							.trim());
				}
			}
			if (!myMap.isEmpty()) {
				rows.add(myMap);				
			}
		}
		return rows;
	}
}