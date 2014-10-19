package layer2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class IO {
	
	private static final String USERAGENT = "Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0";
	private static final String REFERRER = "http://www.google.de";
	private static final int TIMEOUT = 3000;
	
	private static String NL = System.lineSeparator();
	
	public static Document read(File inHTMLFile, String inCharset, String inBaseURI) throws IOException  {
		return Jsoup.parse(inHTMLFile, inCharset, inBaseURI);
	}
	
	public static String createX(Path inPath, Charset inCharset, StandardOpenOption inStandardOpenOption, String inContent) throws IOException {
		BufferedWriter aWriter = Files.newBufferedWriter(
				inPath, 
				inCharset, 
				inStandardOpenOption);
		aWriter.write(inContent);
		aWriter.flush();
		aWriter.close();
		return inContent;
	}

	public static Document read(String inStrURL) throws IOException {
		Document doc = Jsoup.connect(inStrURL)
				.userAgent(IO.USERAGENT)
				.referrer(IO.REFERRER)
				.timeout(IO.TIMEOUT)
				.get();
		return doc;
	}
	
	public static String read(Path inPath, Charset inCharset) throws IOException {
		StringBuilder aSB = new StringBuilder();
		try (BufferedReader reader = Files.newBufferedReader(inPath, inCharset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        aSB.append(line + NL);
		    }
		} catch (IOException e) {
			throw e;
		}
		return aSB.toString();
	}
}
