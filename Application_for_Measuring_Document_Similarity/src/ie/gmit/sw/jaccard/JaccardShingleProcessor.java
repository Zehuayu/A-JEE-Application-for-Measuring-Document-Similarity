package ie.gmit.sw.jaccard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
/**
      *JaccardShingleProcessor class contain TreeSet function. saving the data in the queue 
      *and read the data from document.
      *<p>make Shingle size<br>
     *<p>get title submited<br>
     *<p>get text submited<br>

      
      */
public class JaccardShingleProcessor implements ShingleInterface {
	private int sizeOfShingle;
	private String filePath;
	private String title;
	private DocumentInfo documentInfo = new DocumentInfo();
	
	protected JaccardShingleProcessor() {
	}
	
	public void process() {
		File file = new File(filePath);
		FileReader fr = null;
		Set<Integer> set = new TreeSet<Integer>();
		int numberOfShingle = 0;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			char[] cbuf = new char[sizeOfShingle];
			int n = 0;
			while ((n = br.read(cbuf)) != -1) {
				String s = new String(cbuf, 0, n);
				set.add(s.hashCode());
				numberOfShingle++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			file.delete();
		}

		DocumentInfo documentInfo = new DocumentInfo();
		documentInfo.setTitle(title);
		documentInfo.setNumberOfShingle(numberOfShingle);
		documentInfo.setSizeOfShingle(sizeOfShingle);
		documentInfo.setSet(set);
	}

	public int getSizeOfShingle() {
		return sizeOfShingle;
	}

	public void setSizeOfShingle(int sizeOfShingle) {
		this.sizeOfShingle = sizeOfShingle;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DocumentInfo getDocumentInfo() {
		return documentInfo;
	}

	public void setDocumentInfo(DocumentInfo documentInfo) {
		this.documentInfo = documentInfo;
	}
}
