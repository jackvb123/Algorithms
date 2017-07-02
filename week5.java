import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

class Node {
	int id;
	boolean visited = false;
	int checked = 0;
	int time = -1;
	int leader = -1;
	LinkedList<Integer> edges = new LinkedList<Integer>();
	Node(int id) {
		this.id = id;
	}

	public void addEdge(Integer nodeId) {
		edges.add(nodeId);
	}

	public void removeEdge(Integer nodeId) {
		edges.remove(nodeId);
	}

}

class DirectedGraph {
	int time;
	int leader;
	int NUM;
	Node[] vertices;

	DirectedGraph(int n) {
		NUM = n;
		vertices = new Node[NUM];
	}

	public void addNode(int id, Node newnode) {
		if (id >= 0 && id < NUM)
			vertices[id - 1] = newnode;

	}

	public void removeNode(int id) {
		vertices[id - 1] = null;
	}

	public void printGraph() {
		System.out.println("graph:");
		for (int i = 1; i <= NUM; i++) {
			System.out.println("node " + i + ":" + vertices[i - 1]);
		}
	}
}

public class SCC {
	public static int NUM = 875714;
	public static int[] array = new int[NUM + 1];

	public static DirectedGraph readGraph(DirectedGraph graph)
			throws IOException {
		String str;
		Node newNode;
		int newId, newEdge;

		BufferedReader buffer = new BufferedReader(new FileReader("in.txt"));

		while ((str = buffer.readLine()) != null) {
			Scanner sca = new Scanner(str);
			newId = sca.nextInt();
			newEdge = sca.nextInt();

			if (graph.vertices[newId - 1] != null) {
				graph.vertices[newId - 1].addEdge(newEdge);
			} else {
				newNode = new Node(newId);
				newNode.addEdge(newEdge);
				graph.addNode(newId, newNode);
			}
		}

		return graph;

	}

	public static DirectedGraph readInvGraph(DirectedGraph graph)
			throws IOException {
		String str;
		Node newNode;
		int newId, newEdge;

		BufferedReader buffer = new BufferedReader(new FileReader("in.txt"));

		while ((str = buffer.readLine()) != null) {
			Scanner sca = new Scanner(str);
			newEdge = sca.nextInt();
			newId = sca.nextInt();

			if (graph.vertices[newId - 1] == null) {
				newNode = new Node(newId);
				newNode.addEdge(newEdge);
				graph.addNode(newId, newNode);
			} else {
				graph.vertices[newId - 1].addEdge(newEdge);

			}
		}

		return graph;

	}

	public static void DFSLoop(DirectedGraph graph) {
		Node node;
		graph.time = 0;
		graph.leader = 0;

		for (int i = NUM; i > 0; i--) {
			node = graph.vertices[array[i] - 1];
			if (node != null) {
				if (!node.visited) {
					graph.leader = node.id;
					DFS(graph, node);
				}

			}
		}
	}

	public static void DFS(DirectedGraph graph, Node node) {
		Stack<Node> stack = new Stack<Node>();
		stack.push(node);

		node.visited = true;

		while (!stack.isEmpty()) {
			node = stack.peek();

			while (node.checked < node.edges.size()) {
				int ids = node.edges.get(node.checked);
				node.checked++;
				Node edgeNode = graph.vertices[ids - 1];
				if (!edgeNode.visited) {
					edgeNode.visited = true;
					stack.push(edgeNode);
					node = edgeNode;
				}
			}
			node = stack.pop();
			graph.time++;
			node.time = graph.time;
			node.leader = graph.leader;
		}
	}

	public static void sortGraph(DirectedGraph graph) {
		for (int i = 1; i <= NUM; i++) {
			Node node = graph.vertices[i - 1];
			// System.out.println(node.time);
			array[node.time] = node.id;
		}
	}

	public static void largestFive(DirectedGraph graph) {
		Node node;
		int[] count = new int[NUM + 1];
		for (int i = 1; i <= NUM; i++) {
			node = graph.vertices[i - 1];
			count[node.leader]++;
		}

		Arrays.sort(count);
		System.out.println("Output:");
		for (int i = 0; i <= 4; i++) {
			System.out.println(count[NUM - i]);
		}
	}

	public static void main(String[] args) throws IOException {
		DirectedGraph graph = new DirectedGraph(NUM);
		DirectedGraph invGraph = new DirectedGraph(NUM);

		for (int i = 0; i < NUM; i++) {
			array[i + 1] = i + 1;
			graph.vertices[i] = new Node(i + 1);
			invGraph.vertices[i] = new Node(i + 1);
		}
		System.out.println("started");
		readGraph(graph);
		System.out.println("reading done");
		readInvGraph(invGraph);
		System.out.println("inverse reading done");
		DFSLoop(invGraph);
		sortGraph(invGraph);
		DFSLoop(graph);

		largestFive(graph);
	}
}