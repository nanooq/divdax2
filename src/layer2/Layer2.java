package layer2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;

public class Layer2 {
	
	private static Charset CHARSET = Charset.forName("UTF-8");
	
	public static boolean delete(CSVable inCSVable) {
		return inCSVable.getFile().delete();
	}
	
	public static boolean delete(File inFile) {
		return inFile.delete();
	}
	
	public static boolean exists(File inFile) {
		return inFile.exists();
	}
	
	public static String writenew(CSVable inCSVable) throws IOException {
		return Layer2.writeX(inCSVable.getFile(), StandardOpenOption.CREATE_NEW, inCSVable.getContent());
	}
	
	public static String writenew(File inFile, String inContent) throws IOException {
		return Layer2.writeX(inFile, StandardOpenOption.CREATE_NEW, inContent);
	}
	
	public static String write(CSVable inCSVable) throws IOException {
		return Layer2.writeX(inCSVable.getFile(), StandardOpenOption.CREATE, inCSVable.getContent());
	}
	
	public static String write(File inFile, String inContent) throws IOException {
		return Layer2.writeX(inFile, StandardOpenOption.CREATE, inContent);
	}
	
	private static String writeX(File inFile, StandardOpenOption inStandardOpenOption, String inContent) throws IOException {
		return IO.createX(inFile.toPath(), CHARSET, inStandardOpenOption, inContent.trim());
	}

	public static String read(File inFile) throws IOException {
		return IO.read(inFile.toPath(), CHARSET).trim();
	}

	public static boolean exists(CSVable inCSVable) {
		return Layer2.exists(inCSVable.getFile());
	}
}