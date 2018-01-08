package ie.gmit.sw.jaccard;

/**
 *JaccardShingleProcessorFactory class function is mainly return an instance 
 
 *<p>set shingle size<br>
*<p>set the filePath<br>
*<p>set the title<br>
*@return return the JaccardShingleProcessor

 
 */
public class JaccardShingleProcessorFactory {
	public static JaccardShingleProcessor getInstance(int sizeOfShingle, String filePath, String title)
	{
		JaccardShingleProcessor jaccardShingleProcessor = new JaccardShingleProcessor();
		jaccardShingleProcessor.setSizeOfShingle(sizeOfShingle);
		jaccardShingleProcessor.setFilePath(filePath);
		jaccardShingleProcessor.setTitle(title);
		return jaccardShingleProcessor;
	}
}
