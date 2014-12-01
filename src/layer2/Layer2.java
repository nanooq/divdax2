package layer2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Layer2 {
	
	private static Charset CHARSET = Charset.forName("UTF-8");
	private static String ONLINE = "http://www.dividendenchecker.de";
	
	public static boolean delete(CSVable inCSVable) {
		return inCSVable.getFile().delete();
	}
	
	public static boolean delete(File inFile) {
		return inFile.delete();
	}
	
	public static boolean exists(CSVable inCSVable) {
		return Layer2.exists(inCSVable.getFile());
	}
	
	public static boolean exists(File inFile) {
		return inFile.exists();
	}
	
	public static String read(File inFile) throws IOException {
		return IO.read(inFile.toPath(), CHARSET).trim();
	}
	
	public static Document read(String inStrURL) throws IOException {
		Document doc = Jsoup.connect(inStrURL).get();
		return doc;
	}
	
	public static String write(CSVable inCSVable) throws IOException {
		return Layer2.writeX(inCSVable.getFile(), StandardOpenOption.CREATE, inCSVable.getContent());
	}
	
	public static String write(File inFile, String inContent) throws IOException {
		return Layer2.writeX(inFile, StandardOpenOption.CREATE, inContent);
	}
	
	public static String writenew(CSVable inCSVable) throws IOException {
		return Layer2.writeX(inCSVable.getFile(), StandardOpenOption.CREATE_NEW, inCSVable.getContent());
	}

	public static String writenew(File inFile, String inContent) throws IOException {
		return Layer2.writeX(inFile, StandardOpenOption.CREATE_NEW, inContent);
	}

	private static String writeX(File inFile, StandardOpenOption inStandardOpenOption, String inContent) throws IOException {
		return IO.createX(inFile.toPath(), CHARSET, inStandardOpenOption, inContent.trim());
	}

	public static String write(File inFile, Document aDoc) throws IOException {
		String aContent = aDoc.toString();
		Layer2.write(inFile, aContent);
		return aContent;
	}

	public static Document parse(File inFile, String inBaseURI) throws IOException {
		return Jsoup.parse(inFile, Layer2.CHARSET.toString(), inBaseURI);
	}
	
	public static boolean isOnline() {
		boolean isOnline = true;
		try {
			Jsoup.connect(Layer2.ONLINE).timeout(2000).get();		
		} catch (IOException e){
			isOnline = false;
		}
		return isOnline;
	}
}