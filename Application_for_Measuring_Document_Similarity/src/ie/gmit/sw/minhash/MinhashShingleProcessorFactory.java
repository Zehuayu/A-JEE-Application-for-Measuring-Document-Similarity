package ie.gmit.sw.minhash;
/**
 *MinHashShingleProcessorFactory class function is mainly get an instance
 
 *<p>set value to sizeofShingle <br>
 *<p>set the filePath<br>
 *<p>set the title<br>
 *@return return minhasShingleProcessor

    */
public class MinhashShingleProcessorFactory {
	public static MinhashShingleProcessor getInstance(int sizeOfShingle, String filePath, String title) {
		MinhashShingleProcessor minhashShingleProcessor = new MinhashShingleProcessor();
		minhashShingleProcessor.setSizeOfShingle(sizeOfShingle);
		minhashShingleProcessor.setFilePath(filePath);
		minhashShingleProcessor.setTitle(title);
		return minhashShingleProcessor;
	}
}
