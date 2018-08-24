package Java.Concepts.Singleton;

import java.lang.reflect.Constructor;

// The implementations discussed till now can all be destroyed using the
// reflection class in java by getting the reference if constructor
// and making it accessible as
public class SingletonDetstroyReflection {

	public static void main(String[] args) {
		BasicSingleton_1 instanceOne = BasicSingleton_1.getInstance();
		BasicSingleton_1 instanceTwo = null;
		try {
			Constructor[] constructors = BasicSingleton_1.class.getDeclaredConstructors();
			for (Constructor constructor : constructors) {
				// Below code will destroy the singleton pattern
				constructor.setAccessible(true);
				instanceTwo = (BasicSingleton_1) constructor.newInstance();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(instanceOne.hashCode());
		System.out.println(instanceTwo.hashCode());
	}

}
