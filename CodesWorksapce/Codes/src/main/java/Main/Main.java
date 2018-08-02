package Main;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;

import Common.JavaInputFile;

public class Main extends JavaInputFile {

	public static void main(String[] args) {
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(INPUT.getBytes());
		out = new PrintWriter(System.out);
		solve();
		out.flush();
	}

	private static void solve() {
		// TODO Auto-generated method stub
		
	}

}
