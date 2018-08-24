package Main;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.TreeMap;

public class Main {

	public static InputStream is;
	public static PrintWriter out;
	public static String INPUT = "";
	private static byte[] inbuf = new byte[1024];
	public static int lenbuf = 0, ptrbuf = 0;

	private static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	public static double readDouble() {
		return Double.parseDouble(readString());
	}

	public static char readChar() {
		return (char) skip();
	}

	public static String readString() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	public static char[] readCharArray(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	public static int[] readArray(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = readInt();
		return a;
	}

	public static ArrayList<Integer> readArrayListInt(int n) {
		ArrayList<Integer> a = new ArrayList<>();
		for (int i = 0; i < n; i++)
			a.add(readInt());
		return a;
	}

	public static ArrayList<String> readArrayListString(int n) {
		ArrayList<String> a = new ArrayList<>();
		for (int i = 0; i < n; i++)
			a.add(readString());
		return a;
	}

	public static int readInt() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	public static long readLong() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	public static void printList(ArrayList<Integer> list) {
		for (int i = 0; i < list.size(); i++)
			System.out.println(list.get(i));

	}

	public static void main(String[] args) {
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		solve();
		out.flush();
	}

	private static void solve() {
		int n = readInt();
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		int head = 0;
		for (int i = 0; i < n; i++) {
			int type = readInt();
			if (type == 1) {
				int x = readInt();
				treeMap.put(x, 1);
			} else {
				Integer floorKey = treeMap.floorKey(head);
				Integer ceilKey = treeMap.ceilingKey(head);
				if (floorKey == null) {
					System.out.println(ceilKey);
					treeMap.remove(ceilKey);
					head = ceilKey;
				} else if (ceilKey == null) {
					System.out.println(floorKey);
					treeMap.remove(floorKey);
					head = floorKey;
				} else if (head - floorKey < ceilKey - head) {
					System.out.println(floorKey);
					treeMap.remove(floorKey);
					head = floorKey;
				} else {
					System.out.println(ceilKey);
					treeMap.remove(ceilKey);
					head = ceilKey;
				}
			}
		}
	}

}
