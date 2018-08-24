package Java.Concepts.Singleton;

// Bill Pugh came with a complete different solution to create the singleton object
// used inner static class for creating instance 
// This is one of the best methods as it does not require synchronization
public class SingletonBillPugh_5 {

	private SingletonBillPugh_5() {
	}

	// the inner class is loaded only when the getInstance is called for the first
	// time
	private static class SingletonHelper {
		private static final SingletonBillPugh_5 INSTANCE = new SingletonBillPugh_5();
	}

	public static SingletonBillPugh_5 getInstance() {
		return SingletonHelper.INSTANCE;
	}

}


