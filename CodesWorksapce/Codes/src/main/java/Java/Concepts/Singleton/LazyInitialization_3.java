package Java.Concepts.Singleton;

/*Here we are not initializing the object, we are creating
 *  it when we required it for the first time
 */

/* Also here we could handle the exception thing inside the getInstance function
*/

/* The Problem with this method is when we are in a multiThreaded environment
 * Read comments below
*/

public class LazyInitialization_3 {

	private static LazyInitialization_3 singleton;

	private LazyInitialization_3() {
	}

	// When multiple thread simultaneously try to access this function
	public static LazyInitialization_3 getInstance() {
		if (singleton == null) // multiple threads could pass this statement simultaneously
			return singleton = new LazyInitialization_3(); // hence multiple objects will be created
		return singleton;
	}
}
