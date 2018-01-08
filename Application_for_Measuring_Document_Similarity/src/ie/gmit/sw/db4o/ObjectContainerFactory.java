package ie.gmit.sw.db4o;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

import ie.gmit.sw.ServiceHandler;
/**
2      *connecting the databases of db40.
3      *<p>catch the text from databases<br>
4      *<p>like a databases controller<br>
5      *@param from ServiceHandler
6      *@return db
7      */
public class ObjectContainerFactory {
	public static ObjectContainer getInstance() {
		String db4oDataFile = ServiceHandler.WEBINF_PATH + "/db4o.yap";
		ObjectContainer db = Db4o.openFile(db4oDataFile);
		return db;
	}
}
