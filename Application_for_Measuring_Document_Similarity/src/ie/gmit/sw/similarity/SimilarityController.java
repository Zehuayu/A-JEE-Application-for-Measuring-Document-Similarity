package ie.gmit.sw.similarity;

import java.util.List;

import javax.servlet.AsyncContext;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import ie.gmit.sw.db4o.ObjectContainerFactory;
import ie.gmit.sw.jaccard.DocumentInfo;
import ie.gmit.sw.jaccard.ShingleInterface;
import ie.gmit.sw.minhash.MinhashShingleProcessorFactory;


/**
 *Comparing process controller class, run functions in this class
 *<p>thread pool<br>
  *<p>manage all document upload from ServiceHandler  <br>  

  */
public class SimilarityController implements Runnable {
	private String taskNumber;
	private int sizeOfShingle;
	private String uploadFile;
	private String title;
	private AsyncContext asyncContext;
	
	public SimilarityController(AsyncContext asyncCtx) {
		this.asyncContext = asyncCtx;
	}

	public void execute() {
//		ShingleInterface shingleProcessor = JaccardShingleProcessorFactory.getInstance(sizeOfShingle, uploadFile, title);
		ShingleInterface shingleProcessor = MinhashShingleProcessorFactory.getInstance(sizeOfShingle, uploadFile, title);
		shingleProcessor.process();
		DocumentInfo currentDoc = shingleProcessor.getDocumentInfo();

		currentDoc.setTaskNumber(taskNumber);
	
		ObjectContainer db = ObjectContainerFactory.getInstance();
		Query query = db.query();
		query.constrain(DocumentInfo.class);
		List<DocumentInfo> list = query.execute();
		
		SimilarityResult result = new SimilarityResult();
		for (DocumentInfo documentInfo : list) {
			SimilarityProcessor similarityProcessor = new SimilarityProcessor();
			similarityProcessor.setDocumentA(currentDoc);
			similarityProcessor.setDocumentB(documentInfo);
			double similarity = similarityProcessor.calculateSimilarity();
			result.addResult(documentInfo.getTitle(), similarity);
//			System.out.println("Title: " + documentInfo.getTitle() + "     index: " + similarity);
		}
		
		currentDoc.setResults(result.getResults());
		db.store(currentDoc);
		db.commit();
		db.close();
		
//		List<ResultInfo> a = result.getResults();
//		System.out.println("=======result============");
//		for (ResultInfo re : a) {
//			System.out.println("Title: " + re.getTitle() + "     index: " + re.getSimilarity());
//		}
		asyncContext.complete();
	}

	@Override
	public void run() {
		System.out.println("【SimilarityController.run()】 " + "");
		this.execute();
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public int getSizeOfShingle() {
		return sizeOfShingle;
	}

	public void setSizeOfShingle(int sizeOfShingle) {
		this.sizeOfShingle = sizeOfShingle;
	}

	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
