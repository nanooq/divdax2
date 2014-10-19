package layer2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class IO {
	
	private static String nl = System.lineSeparator();
	
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

	public static String read(Path inPath, Charset inCharset) throws IOException {
		StringBuilder aSB = new StringBuilder();
		try (BufferedReader reader = Files.newBufferedReader(inPath, inCharset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        aSB.append(line + nl);
		    }
		} catch (IOException e) {
			throw e;
		}
		return aSB.toString();
	}
}
