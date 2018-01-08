package ie.gmit.sw.similarity;

import java.util.ArrayList;
import java.util.Collections;




public class SimilarityResult {
	
	
	
	private ArrayList<ResultInfo> results = new ArrayList<>();
	
	public void addResult(String title, double similiarity) {
		if (results.size() < 10) {
			results.add(new ResultInfo(title, similiarity));
		} else {
			Collections.sort(results);
			Collections.reverse(results);
			double sim = results.get(9).getSimilarity();
			if (similiarity > sim) {
				results.remove(9);
				results.add(new ResultInfo(title, similiarity));
			}
		}
	}

	public ArrayList<ResultInfo> getResults() {
		Collections.sort(results);
		Collections.reverse(results);
		return results;
	}
}
