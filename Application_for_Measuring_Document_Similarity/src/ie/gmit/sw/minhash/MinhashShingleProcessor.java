package ie.gmit.sw.minhash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import ie.gmit.sw.jaccard.DocumentInfo;
import ie.gmit.sw.jaccard.ShingleInterface;

/**
 *MinHash function is quickly estimating how similar two sets are 
 *<p>resource from http://blog.csdn.net/Remoa_Dengqinyi/article/details/72891383<br>
*<p>The Jaccard similarity coefficient is a commonly used indicator of the similarity between two sets. For sets A and B it is defined to be the ratio of the number of elements of their 
*intersection and the number of elements of their union<br>


 
 */
public class MinhashShingleProcessor implements ShingleInterface {
	private static double errorRate = 0.1;
	private static double jIndex = 0.30;
	// expect to be 0.01 with error rate of 10%
	// set k function 
	private static double k = 1 / (jIndex * errorRate * errorRate);
	
	private int sizeOfShingle;
	private String filePath;
	private String title;
	private DocumentInfo documentInfo = new DocumentInfo();

	protected MinhashShingleProcessor() {
	}
	
	public void process() {
		// Create the set of hash integers as random numbers
		Set<Integer> hashes = new TreeSet<Integer>();
		Random r = new Random(1);
		for (int i = 0; i < k; i++) {
			// Create k random integers
			hashes.add(r.nextInt());
		}

		File file = new File(filePath);
		Set<Integer> shingles = new TreeSet<Integer>();

		// XOR the integer word values with the hashes
		for (Integer hash : hashes) {
			FileReader fr = null;
			try {
				int min = Integer.MAX_VALUE;

				fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				char[] cbuf = new char[sizeOfShingle];
				int n = 0;
				while ((n = br.read(cbuf)) != -1) {
					String s = new String(cbuf, 0, n);

					// Bitwise XOR the string hashCode with the hash
					int minHash = s.hashCode() ^ hash;
					if (minHash < min) {
						min = minHash;
					}
				}
				shingles.add(min);
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
			}
		}
		file.delete();
		// set info to the Jaccard. 
		documentInfo.setTitle(title);
		documentInfo.setSizeOfShingle(sizeOfShingle);
		documentInfo.setSet(shingles);
	}

	public static double getErrorRate() {
		return errorRate;
	}

	public static void setErrorRate(double errorRate) {
		MinhashShingleProcessor.errorRate = errorRate;
	}

	public static double getjIndex() {
		return jIndex;
	}

	public static void setjIndex(double jIndex) {
		MinhashShingleProcessor.jIndex = jIndex;
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
