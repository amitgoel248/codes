package Algo.Tree.HLD;

import java.util.ArrayList;

import Common.JavaInputFile;

public class HLD_basic extends JavaInputFile {

	static int segIndex = -1;
	static int branchIndex = 0;

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
		int n = readInt();
		node treeNode[] = new node[n];
		int[] branchSequenceHeads = new int[n];
		int segArray[] = new int[n];
		int segTree[] = new int[n * 4];
		for (int i = 0; i < n; i++) {
			tree.add(new ArrayList<>());
			treeNode[i] = new node();
		}

		for (int i = 0; i < n - 1; i++) {
			int a = readInt();
			int b = readInt();
			// int w = readInt();
			tree.get(a).add(b);
			tree.get(b).add(a);
		}

		compute_size(0, -1, 0, tree, treeNode);

		branchSequenceHeads[0] = 0; // root
		create_HLD(treeNode, tree, segArray, 0, -1, branchSequenceHeads);
		createSegmentTree(0, n - 1, segArray, segTree, 0);

		printEverything(n, treeNode, branchSequenceHeads, segArray, segTree, tree);

		int q = readInt();

		for (int i = 0; i < q; i++) {
			int type = readInt();
			int ele1 = readInt();
			int ele2 = readInt();

			if (type == 1) {
				int ans = query(ele1, ele2, treeNode, branchSequenceHeads, n, segTree);
				System.out.println(ans);
			} else {
				update(segTree, ele1, ele2, treeNode, n);
			}
		}
	}

	private static void printEverything(int n, node[] treeNode, int[] branchSequenceHeads, int[] segArray,
			int[] segTree, ArrayList<ArrayList<Integer>> tree) {
		for (int i = 0; i < n; i++) {
			System.out.println(treeNode[i].toString());
		}

		for (int i = 0; i < n; i++)
			System.out.print(segArray[i] + " ");
		System.out.println();

		for (int i = 0; i < 4 * n; i++)
			System.out.print(segTree[i] + " ");
		System.out.println();

		for (int i = 0; i < tree.size(); i++) {
			for (int j = 0; j < tree.get(i).size(); j++)
				System.out.print(tree.get(i).get(j) + " ");
			System.out.println();
		}

		for (int i = 0; i <= branchIndex; i++) {
			System.out.print(branchSequenceHeads[i] + " ");
		}
		System.out.println();
	}

	private static int createSegmentTree(int i, int j, int[] segArray, int[] segTree, int index) {
		if (i == j) {
			return segTree[index] = segArray[i];
		}

		int mid = (i + j) / 2;
		return segTree[index] = Integer.max(createSegmentTree(i, mid, segArray, segTree, (2 * index) + 1),
				createSegmentTree(mid + 1, j, segArray, segTree, (2 * index) + 2));
	}

	private static void update(int[] segTree, int nodeNo, int weight, node[] treeNode, int n) {
		updateSegmentTree(segTree, treeNode[nodeNo].indexInSegArray, weight, 0, n - 1, 0);
	}

	private static int updateSegmentTree(int[] segTree, int indexInSegArray, int weight, int i, int j, int index) {
		if (indexInSegArray < i || indexInSegArray > j)
			return segTree[index];
		if (i == j && i == indexInSegArray) {
			return segTree[index] = weight;
		}
		int mid = (i + j) / 2;
		return segTree[index] = Integer.max(
				updateSegmentTree(segTree, indexInSegArray, weight, i, mid, (2 * index) + 1),
				updateSegmentTree(segTree, indexInSegArray, weight, mid + 1, j, (2 * index) + 2));
	}

	private static int query(int ele1, int ele2, node[] treeNode, int[] branchSequenceHeads, int n, int[] segTree) {
		if (treeNode[ele1].branchNo == treeNode[ele2].branchNo) {
			return querySeg(Integer.min(treeNode[ele1].indexInSegArray, treeNode[ele2].indexInSegArray),
					Integer.max(treeNode[ele1].indexInSegArray, treeNode[ele2].indexInSegArray), 0, n - 1, segTree, 0);
		}
		if (treeNode[branchSequenceHeads[treeNode[ele1].branchNo]].depth > treeNode[branchSequenceHeads[treeNode[ele2].branchNo]].depth) {
			return Integer.max(
					querySeg(
							Integer.min(treeNode[ele1].indexInSegArray,
									treeNode[branchSequenceHeads[treeNode[ele1].branchNo]].indexInSegArray),
							Integer.max(treeNode[ele1].indexInSegArray,
									treeNode[branchSequenceHeads[treeNode[ele1].branchNo]].indexInSegArray),
							0, n - 1, segTree, 0),
					query(treeNode[branchSequenceHeads[treeNode[ele1].branchNo]].parent, ele2, treeNode,
							branchSequenceHeads, n, segTree));
		} else {
			return Integer.max(
					querySeg(
							Integer.min(treeNode[ele2].indexInSegArray,
									treeNode[branchSequenceHeads[treeNode[ele2].branchNo]].indexInSegArray),
							Integer.max(treeNode[ele2].indexInSegArray,
									treeNode[branchSequenceHeads[treeNode[ele2].branchNo]].indexInSegArray),
							0, n - 1, segTree, 0),
					query(ele1, treeNode[branchSequenceHeads[treeNode[ele2].branchNo]].parent, treeNode,
							branchSequenceHeads, n, segTree));
		}
	}

	private static int querySeg(int l, int r, int i, int j, int[] segTree, int index) {
		if (l > j || r < i)
			return -1;
		if (l <= i && r >= j)
			return segTree[index];
		int mid = (i + j) / 2;
		return Integer.max(querySeg(l, r, i, mid, segTree, (2 * index) + 1),
				querySeg(l, r, mid + 1, j, segTree, (2 * index) + 2));
	}

	private static void create_HLD(node[] treeNode, ArrayList<ArrayList<Integer>> tree, int[] segArray, int root,
			int parent, int[] branchSequenceHeads) {
		segArray[++segIndex] = root;
		treeNode[root].indexInSegArray = segIndex;
		treeNode[root].branchNo = branchIndex;
		int maxChild = treeNode[root].maxNextElement;
		if (maxChild == -1)
			return;
		create_HLD(treeNode, tree, segArray, maxChild, root, branchSequenceHeads);
		for (int i = 0; i < tree.get(root).size(); i++) {
			if (tree.get(root).get(i) != parent && tree.get(root).get(i) != maxChild) {
				branchSequenceHeads[++branchIndex] = tree.get(root).get(i);
				create_HLD(treeNode, tree, segArray, tree.get(root).get(i), root, branchSequenceHeads);
			}
		}

	}

	private static int compute_size(int root, int parent, int depth, ArrayList<ArrayList<Integer>> tree,
			node[] treeNode) {
		treeNode[root].depth = depth;
		treeNode[root].size = 1;
		treeNode[root].maxNextElement = -1;
		treeNode[root].parent = parent;
		for (int i = 0; i < tree.get(root).size(); i++) {
			if (tree.get(root).get(i) != parent) {
				int childSize = compute_size(tree.get(root).get(i), root, depth + 1, tree, treeNode);
				treeNode[root].size += childSize;
				if (treeNode[root].maxNextElement == -1)
					treeNode[root].maxNextElement = tree.get(root).get(i);
				else if (childSize > treeNode[treeNode[root].maxNextElement].size)
					treeNode[root].maxNextElement = tree.get(root).get(i);
			}
		}
		return treeNode[root].size;
	}

	private static class node {
		int size;
		int maxNextElement;
		int branchNo;
		int indexInSegArray;
		int parent;
		int depth;

		@Override
		public String toString() {
			return "node [size=" + size + ", maxNextElement=" + maxNextElement + ", branchNo=" + branchNo
					+ ", indexInSegArray=" + indexInSegArray + ", parent=" + parent + ", depth=" + depth + "]";
		}
	}

}
