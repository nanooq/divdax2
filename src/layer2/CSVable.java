package layer2;

import java.io.File;

public interface CSVable {
	
	public File getFile();
	public String[] getHeader();
	public String getBody();
	public String getContent();

}
