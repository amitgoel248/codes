package DS.DisjointSet;

import Common.JavaInputFile;

public class DisjointSet extends JavaInputFile {

	public static int numberOfCC;
	public static boolean isCyclic;

	public static void init(Parent[] parent, int n) {
		for (int i = 0; i <= n; i++)
			parent[i] = new Parent(i, 1);
	}

	public static Parent findParent(int a, Parent[] parent) {
		if (parent[a].getValue() == a)
			return parent[a];
		return findParent(parent[a].getValue(), parent);
	}

	public static void join(int a, int b, Parent[] parent) {
		Parent parentA = findParent(a, parent);
		Parent parentB = findParent(b, parent);
		if (parentA.getValue() != parentB.getValue()) {
			if (parentA.getSize() <= parentB.getSize()) {
				parentA.setValue(parentB.getValue());
				parentB.setSize(parentA.getSize() + parentB.getSize());
			} else {
				parentB.setValue(parentA.getValue());
				parentA.setSize(parentA.getSize() + parentB.getSize());
			}
			numberOfCC--;
			return;
		} else
			isCyclic = true;
	}

	public static class Parent {

		private int value;
		private int size;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public Parent(int value, int size) {
			super();
			this.size = size;
			this.value = value;
		}

	}
}
