package com.yatra.products.corporate.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		System.out.println(binarySearch(1, 1000000000, 3, "left"));
	}

	static double binarySearch(double left, double right, int precision, String toMove) {
		double ans = -1;
		while (left <= right) {
			double mid = Double.parseDouble(String.format("%." + precision + "f", left + (right - left) / 2));
			//System.out.printf("%f", mid);
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
