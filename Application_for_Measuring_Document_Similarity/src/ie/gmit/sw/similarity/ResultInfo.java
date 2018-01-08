package ie.gmit.sw.similarity;

/**
      *output the results to servicePollHandler.
      *<p>title info<br>
      *<p>similarity<br>
      
      *@return string and double value
     
      */


public class ResultInfo implements Comparable<ResultInfo> {
	private String title;
	private Double similarity;

	public ResultInfo(String title, double similarity) {
		this.title = title;
		this.similarity = similarity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(Double similarity) {
		this.similarity = similarity;
	}

	@Override
	public int compareTo(ResultInfo o) {
		return this.getSimilarity().compareTo(o.getSimilarity());
	}
}
