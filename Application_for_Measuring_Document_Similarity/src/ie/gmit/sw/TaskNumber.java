package ie.gmit.sw;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import ie.gmit.sw.db4o.ObjectContainerFactory;

public class TaskNumber {
	private static TaskNumber obj;

	private long jobNumber;

	public static long fetchJobNumber() {
		ObjectContainer db = ObjectContainerFactory.getInstance();
		Query query = db.query();
		query.constrain(TaskNumber.class);
		List<TaskNumber> list = query.execute();
		long jobNumber = 0;
		if (list != null && list.size() > 0) {
			obj = list.get(0);
			jobNumber = obj.getJobNumber();
		}
		db.close();
		return jobNumber;
	}

	public static void saveJobNumber(long jobNumber) {
		ObjectContainer db = ObjectContainerFactory.getInstance();
		if (obj != null) {
			obj.setJobNumber(jobNumber);
			db.store(obj);
		}
	}

	public long getJobNumber() {
		return jobNumber;
	}

	private void setJobNumber(long jobNumber) {
		this.jobNumber = jobNumber;
	}

}
