package Algo.Searching;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import Common.JavaInputFile;

public class BinarySearch extends JavaInputFile {

	public static void main(String[] args) throws IOException {
		System.out.println(binarySearchIteration(1, 1000000000, 3, "left"));
	}

	static double binarySearchIteration(double left, double right, int precision, String toMove) {
		double ans = -1;
		while (left <= right) {
			double mid = Double.parseDouble(String.format("%." + precision + "f", left + (right - left) / 2));
			// System.out.printf("%f", mid);
			double unitDist = (double) 1 / (double) Math.pow(10, precision);
			if (solve(mid)) {
				ans = mid;
				if (toMove.equalsIgnoreCase("left")) {
					right = mid - unitDist;
				} else {
					left = mid + unitDist;
				}
			} else {
				if (toMove.equalsIgnoreCase("left")) {
					left = mid + unitDist;
				} else {
					right = mid - unitDist;
				}
			}
		}
		return ans;
	}

	// in case of duplicate keys ... which key is required smallest or largest is
	// determined by toMove (left = small && right = large)
	static int binary_search(ArrayList<Integer> list, int key, String toMove) {
		int low = 0;
		int high = list.size() - 1;

		int index = -1;
		while (low <= high) {
			int mid = (low + high) >>> 1;
			int midVal = list.get(mid);

			if (midVal == key) {
				index = mid;
				if (toMove.equalsIgnoreCase("left"))
					high = mid - 1;
				else
					low = mid + 1;
			} else if (midVal > key)
				high = mid - 1;
			else
				low = mid + 1;
		}
		if (index == -1)
			return Collections.binarySearch(list, key);
		return index;
	}

	static boolean solve(double x) {
		return true;
	}

}
