package JFX.mote;

import java.util.ArrayList;

public class AppThreadManager extends ArrayList<Thread>{

	private static final long serialVersionUID = 1L;
	/**
	 * Create a Thread for the specified element and appends it to the end of this list.
	 * @param e
	 * @return
	 */
	public Thread add(Runnable e) {
		Thread t = new Thread(e);
		super.add(t);
		return t;
	}
	/**
	 * Stop and removes all of the Threads from this list.
	 */
	public void clear() {
		forEach(t->{
			if(t.isAlive()) {
				t.interrupt();
			}
		});
		super.clear();
	}
}
