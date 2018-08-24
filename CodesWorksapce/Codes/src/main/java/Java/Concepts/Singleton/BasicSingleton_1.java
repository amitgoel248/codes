package Java.Concepts.Singleton;

/*This is Eager initialization of the of the singleton object that is,
 *we are creating the object even when it might not be required at all
 */

/* Also we are not handling any kind of exception 
 * that might occur while creating the object
*/ 

public class BasicSingleton_1 {

	private static BasicSingleton_1 basicSingleton = new BasicSingleton_1();

	private BasicSingleton_1() {
	}

	public static BasicSingleton_1 getInstance() {
		return basicSingleton;
	}
}
