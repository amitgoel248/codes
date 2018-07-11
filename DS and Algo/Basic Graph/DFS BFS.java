package com.yatra.products.corporate.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws java.lang.Exception {

		StringTokenizer nAndm = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(nAndm.nextToken()); // number of nodes
		int m = Integer.parseInt(nAndm.nextToken()); // number of edges

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			ArrayList<Integer> row = new ArrayList<>();
			graph.add(row);
		}

		for (int i = 0; i < m; i++) {
			StringTokenizer aAndb = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(aAndb.nextToken());
			int b = Integer.parseInt(aAndb.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		DFS_graph(graph, n);
		BFS_graph(graph, n);
	}

	private static void BFS_graph(ArrayList<ArrayList<Integer>> graph, int n) {
		boolean visited[] = new boolean[n];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		visited[0] = true;
		BFS(graph, n, visited, queue);
	}

	private static void DFS_graph(ArrayList<ArrayList<Integer>> graph, int n) {
		boolean visited[] = new boolean[n];
		DFS(graph, n, visited, 0);
	}

	private static void DFS(ArrayList<ArrayList<Integer>> graph, int n, boolean[] visited, int index) {
		System.out.println(index);
		visited[index] = true;
		for (int i = 0; i < graph.get(index).size(); i++) {
			if (!visited[graph.get(index).get(i)])
				DFS(graph, n, visited, graph.get(index).get(i));
		}
	}

	private static void BFS(ArrayList<ArrayList<Integer>> graph, int n, boolean[] visited, Queue<Integer> queue) {
		while (!queue.isEmpty()) {
			int elem = queue.poll();
			System.out.println(elem);
			for (int i = 0; i < graph.get(elem).size(); i++) {
				if (!visited[graph.get(elem).get(i)]) {
					visited[graph.get(elem).get(i)] = true;
					queue.add(graph.get(elem).get(i));
				}
			}
		}
	}

}
