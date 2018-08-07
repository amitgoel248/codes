package Common;

import java.util.ArrayList;
import java.util.Collections;

public class PrimeFactorsNumbersDivisors {

	// sqrt(n)
	public static ArrayList<Integer> allDivisors(int n) {
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				list1.add(i);
				list2.add(n / i);
			}
		}
		if (isPerfectSquare(n))
			list1.remove(list1.size() - 1);
		Collections.reverse(list2);
		list1.addAll(list2);
		return list1;
	}

	static boolean isPerfectSquare(double x) {
		double sr = Math.sqrt(x);
		return ((sr - Math.floor(sr)) == 0);
	}

	// sqrt(n)
	public static ArrayList<Integer> primeFactors(int n) {
		ArrayList<Integer> list = new ArrayList<>();
		while (n % 2 == 0) {
			list.add(2);
			n = n / 2;
		}
		for (int i = 3; i <= Math.sqrt(n); i = i + 2) {
			while (n % i == 0) {
				list.add(i);
				n = n / i;
			}
		}
		if (n > 2)
			list.add(n);
		return list;
	}

	// sqrt(n)*loglog(n)
	public static ArrayList<Integer> primeNumbers(int n) {
		ArrayList<Integer> list = new ArrayList<>();
		boolean[] prime = new boolean[n + 1];

		for (int i = 4; i <= n; i += 2)
			prime[i] = true;

		for (int p = 3; p * p <= n; p += 2) {
			if (!prime[p]) {
				for (int i = p * p; i <= n; i += p)
					prime[i] = true;
			}
		}

		// Print all prime numbers
		for (int p = 2; p <= n; p++)
			if (!prime[p])
				list.add(p);

		return list;
	}

	public static int[] getSieveArrayForPrimeFactors(int n) {
		int[] sieveArray = new int[n + 1];

		for (int i = 1; i <= n; i++)
			sieveArray[i] = i;

		for (int i = 4; i <= n; i += 2)
			sieveArray[i] = 2;

		for (int i = 3; i * i <= n; i += 2) {
			if (sieveArray[i] == i) {
				for (int j = i * i; j <= n; j += i)
					if (sieveArray[j] == j)
						sieveArray[j] = i;
			}
		}

		return sieveArray;

	}

	public static ArrayList<Integer> primeFactorsUsingSieve(int n, int[] sieveArray) {
		ArrayList<Integer> list = new ArrayList<>();
		while (n > 1) {
			list.add(sieveArray[n]);
			n = n / sieveArray[n];
		}
		return list;
	}

}
