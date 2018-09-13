package Algo.Graph.Dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;

import Common.JavaInputFile;

public class Dijkstra extends JavaInputFile {

	public static void main(String[] args) {

		int[][] distance = new int[100][100];
		int source = readInt();
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		boolean[] visited = new boolean[100];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dijkstra(graph, source, shortest, distance, visited);

	}

	public static void dijkstra(ArrayList<ArrayList<Integer>> graph, int source, int[] shortest, int[][] distance,
			boolean[] visited) {

		visited[source] = true;
		for (int i = 0; i < graph.get(source).size(); i++) {
			int neighbour = graph.get(source).get(i);
			if (!visited[neighbour])
				shortest[neighbour] = Integer.min(shortest[neighbour], shortest[source] + distance[source][neighbour]);
		}

		dijkstra(graph, getShortest(), shortest, distance, visited);
	}

	static class Node {
		int value;
		int dist;
	}
}
