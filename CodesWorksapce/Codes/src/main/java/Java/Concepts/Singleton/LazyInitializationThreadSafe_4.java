package Java.Concepts.Singleton;

public class LazyInitializationThreadSafe_4 {

	private static LazyInitializationThreadSafe_4 singleton;

	private LazyInitializationThreadSafe_4() {
	}

	// Now this function becomes thread safe hence only one object will be created
	// But the problem with this is that we are adding an overHead blocking all the
	// threads even when the object is created earlier
	public static LazyInitializationThreadSafe_4 getInstance_2() {
		if (singleton == null)
			return singleton = new LazyInitializationThreadSafe_4();
		return singleton;
	}

	// better Approach
	// Here we are not blocking the threads after one instance is created
	public static synchronized LazyInitializationThreadSafe_4 getInstance() {
		if (singleton == null) {
			synchronized (LazyInitializationThreadSafe_4.class) {
				if (singleton == null)
					return singleton = new LazyInitializationThreadSafe_4();
			}
		}
		return singleton;
	}

}
