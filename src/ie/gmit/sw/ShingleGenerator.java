package ie.gmit.sw;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
public class ShingleGenerator {

	Set<Integer> hashes = new TreeSet<Integer>();
	Set<Integer> bookHashes = new TreeSet<Integer>();	
	
	ArrayList<String> words = new ArrayList<String>();
	ArrayList<String> shingleStrSet = new ArrayList<String>();
	
	List<List<String>> shingleParts = new ArrayList<List<String>>();

	Book book = new Book();	
	String title;

	public ShingleGenerator() {
		super();
	}

	public void bookName(String s) {
		title = s;
	}

	public void generateShingle(String line) {

		String[] wordSplit = line.split(" ");
		final int N = words.size();
		final int shingleSize = 3;


		for (int i = 0; i < wordSplit.length; i++) {

			words.add(wordSplit[i]);
		}

		for (int i = 0; i < N; i += shingleSize) {
			shingleParts.add(new ArrayList<>(words.subList(i, Math.min(N, i + shingleSize))));

		}

		for (List<String> part : shingleParts) {
			shingleStrSet.add(String.join(" ", part));
			String shingle = String.join(" ", part);
			hashes.add(shingle.hashCode());
		}
	}

	
	
	
	public void getBooks() {

		

		ObjectContainer db = Db4oEmbedded.openFile("database.db4o");

		try {
			
			List<Book> library = db.queryByExample(Book.class);
			book = library.get(0);
			System.out.println(book.getName());
			//db.store(book);
			bookHashes = book.getHashes();
			db.commit();
		}

		catch (Exception e) {
			db.rollback();
		}

		finally {
			db.close();
		}

	}
}
