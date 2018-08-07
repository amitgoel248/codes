package DS.Trie;

import java.util.ArrayList;

import Common.JavaInputFile;

public class BasicTrie extends JavaInputFile {

	public static TrieNode initializeTrie(int size) {
		TrieNode root = new TrieNode(size);
		return root;
	}

	public static void insertTrie(TrieNode root, String s, int size) {
		for (int i = 0; i < s.length(); i++) {
			if (root.next.get(getIndex(s.charAt(i))) == null) {
				root.next.set(getIndex(s.charAt(i)), new TrieNode(size));
			}
			root = root.next.get(getIndex(s.charAt(i)));
			root.frequency++;
			if (i == s.length() - 1)
				root.isEnd++;
		}
	}

	public static void insertTrie(TrieNode root, ArrayList<Integer> list, int size) {
		for (int i = 0; i < list.size(); i++) {
			if (root.next.get(list.get(i)) == null) {
				root.next.set(list.get(i), new TrieNode(size));
			}
			root = root.next.get(list.get(i));
			root.frequency++;
			if (i == list.size() - 1)
				root.isEnd++;
		}
	}

	public static void deleteTrie(TrieNode root, String s, int size) {
		if (searchTrie(root, s, size) > 0)
			remove(root, s, size);
	}

	public static void deleteTrie(TrieNode root, ArrayList<Integer> list, int size) {
		if (searchTrie(root, list, size) > 0)
			remove(root, list, size);
	}

	public static int searchTrie(TrieNode root, ArrayList<Integer> list, int size) {

		for (int i = 0; i < list.size(); i++) {
			if (root.next.get(list.get(i)) == null) {
				return 0;
			}
			root = root.next.get(list.get(i));
			if (i == list.size() - 1 && root.isEnd != 0) {
				return root.isEnd;
			}
		}
		return 0;
	}

	// returns number of string same as 's' in the trie
	public static int searchTrie(TrieNode root, String s, int size) {
		for (int i = 0; i < s.length(); i++) {
			if (root.next.get(getIndex(s.charAt(i))) == null) {
				return 0;
			}
			root = root.next.get(getIndex(s.charAt(i)));
			if (i == s.length() - 1 && root.isEnd != 0) {
				return root.isEnd;
			}
		}
		return 0;
	}

	// returns number of string whose prefix is same as 's' in the trie
	public static int partialSearchTrie(TrieNode root, String s, int size) {
		for (int i = 0; i < s.length(); i++) {
			if (root.next.get(getIndex(s.charAt(i))) == null) {
				return 0;
			}
			root = root.next.get(getIndex(s.charAt(i)));
			if (i == s.length() - 1) {
				return root.frequency;
			}
		}
		return 0;
	}

	private static void remove(TrieNode root, String s, int size) {
		for (int i = 0; i < s.length(); i++) {
			if (root.next.get(getIndex(s.charAt(i))).frequency == 1) {
				root.next.set(getIndex(s.charAt(i)), null);
			}
			root.next.get(getIndex(s.charAt(i))).frequency--;
		}
	}

	private static void remove(TrieNode root, ArrayList<Integer> list, int size) {
		for (int i = 0; i < list.size(); i++) {
			if (root.next.get(list.size()).frequency == 1) {
				root.next.set(list.size(), null);
			}
			root.next.get(list.get(i)).frequency--;
		}
	}

	private static int getIndex(char ch) {
		if (ch >= 'a' && ch <= 'z')
			return ch - 'a';
		if (ch >= 'A' && ch <= 'Z')
			return 26 + ch - 'A';
		if (ch >= '0' && ch <= '9')
			return 52 + ch - '0';
		return 0;
	}

	public static class TrieNode {
		private int frequency;
		private int isEnd;
		private ArrayList<TrieNode> next;

		public TrieNode(int size) {
			this.frequency = 0;
			this.isEnd = 0;
			this.next = new ArrayList<>();
			for (int j = 0; j < size; j++) {
				this.next.add(null);
			}
		}

		public int getFrequency() {
			return frequency;
		}

		public void setFrequency(int frequency) {
			this.frequency = frequency;
		}

		public int isEnd() {
			return isEnd;
		}

		public void setEnd(int isEnd) {
			this.isEnd = isEnd;
		}

		public ArrayList<TrieNode> getNext() {
			return next;
		}

		public void setNext(ArrayList<TrieNode> next) {
			this.next = next;
		}

	}

}
