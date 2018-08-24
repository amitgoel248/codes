package Java.Concepts.Singleton;

/*This is again early initialization of the of the singleton object that is,
 *we are creating the object even when it might not be required at all
 */

/* Here we have handle the case of exception using the static block
*/

public class BlockStaticSingleton_2 {

	private static BlockStaticSingleton_2 singleton;

	// used to initialize static data members
	static {
		try {
			singleton = new BlockStaticSingleton_2();
		} catch (Exception e) {
			System.out.println("Exceptin Occured while creating the singleton object");
			e.printStackTrace();
		}
	}

	private BlockStaticSingleton_2() {
	}

	public static BlockStaticSingleton_2 getInstance() {
		return singleton;
	}
}
