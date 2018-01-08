package ie.gmit.sw.similarity;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import ie.gmit.sw.db4o.ObjectContainerFactory;
import ie.gmit.sw.jaccard.DocumentInfo;

public class SimilarityDBResult {
	public static List<ResultInfo> fetchResult(String taskNumber) {
		ObjectContainer db = ObjectContainerFactory.getInstance();
		Query query = db.query();
		query.constrain(DocumentInfo.class);
		query.descend("taskNumber").constrain(taskNumber);
		List<DocumentInfo> list = query.execute();
		List<ResultInfo> results = null;
		if (list != null && list.size() > 0) {
			DocumentInfo documentInfo = list.get(0);
			results = documentInfo.getResults();
		} else {
		}
		db.close();
		return results;
	}
}
