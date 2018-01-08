package ie.gmit.sw.jaccard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ie.gmit.sw.similarity.ResultInfo;


/**
      *manage all information of document and set function.
     
     *@return return int and string
      */
public class DocumentInfo {
	private String title;
	private int numberOfShingle;
	private int sizeOfShingle;
	private Set<Integer> set;
	private List<ResultInfo> results = new ArrayList<>();
	private String taskNumber;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumberOfShingle() {
		return numberOfShingle;
	}

	public void setNumberOfShingle(int numberOfShingle) {
		this.numberOfShingle = numberOfShingle;
	}

	public int getSizeOfShingle() {
		return sizeOfShingle;
	}

	public void setSizeOfShingle(int sizeOfShingle) {
		this.sizeOfShingle = sizeOfShingle;
	}

	public Set<Integer> getSet() {
		return set;
	}

	public void setSet(Set<Integer> set) {
		this.set = set;
	}

	public List<ResultInfo> getResults() {
		return results;
	}

	public void setResults(List<ResultInfo> results) {
		this.results = results;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

}
