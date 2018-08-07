package Algo.Searching;

import java.io.IOException;

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

	static boolean solve(double x) {
		return true;
	}

}
