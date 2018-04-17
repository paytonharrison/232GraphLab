package graphlab;
public class GraphApp {
	public static void main(String[] args)
	{
		Graph theGraph = new Graph();
		System.out.println("Depth First Search Visits: ");
		theGraph.depthFirstSearch();
		System.out.println("");
		System.out.println("Breadth First Search Visits: ");
		theGraph.breadthFirstSearch();
		System.out.println("");
		System.out.println("Minimum Spanning Tree: ");
		theGraph.minimum_spanning_tree();
		System.out.println("");
	}
}
