package ie.gmit.sw.similarity;

import java.util.Set;
import java.util.TreeSet;

import ie.gmit.sw.jaccard.DocumentInfo;



/**
 *comparing process and calculate the result between the document A and B .
 *<p>By the TreeSet Function <br>
 *<p> retainAll() method<br>

 */

public class SimilarityProcessor implements SimilarityInterface {
	private DocumentInfo documentA;
	private DocumentInfo documentB;

	private Set<Integer> setA;
	private Set<Integer> setB;

	@Override
	public double calculateSimilarity() {
		setA = documentA.getSet();
		setB = documentB.getSet();
		int numberOfShingleA = documentA.getSet().size();
		int numberOfShingleB = documentB.getSet().size();

		if (setA != null && setB != null) {
			Set<Integer> setC = new TreeSet<Integer>(setB);
			setC.retainAll(setA);
			int retain = setC.size();
			if (numberOfShingleA + numberOfShingleB - retain == 0) {
				return 1;
			} else {
				double d = 1.0d * retain / (numberOfShingleA + numberOfShingleB - retain);
				return d;
			}
		} else {
			return 0;
		}
	}

	public DocumentInfo getDocumentA() {
		return documentA;
	}

	public void setDocumentA(DocumentInfo documentA) {
		this.documentA = documentA;
	}

	public DocumentInfo getDocumentB() {
		return documentB;
	}

	public void setDocumentB(DocumentInfo documentB) {
		this.documentB = documentB;
	}

}
