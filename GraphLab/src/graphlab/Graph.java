package graphlab;

import java.util.*;
import java.io.*;

//This class contains all of the methods that are called that search the graph
//author PH ZT TO

public class Graph {
	//fields
	private Vertex vertexList[];
	private int adjacencyMatrix[][];
	
	//constructor calls methods that read the input files
	public Graph()
	{
		readLabels("src/graphlab/labels.txt");
		readAdjacencyMatrix("src/graphlab/adj_matrix.txt");
		readAdjacencyMatrix("src/graphlab/adj_matrix.txt");
	}
	
	//reads the adjacency matrix input file and saves it into the adjacencyMatrix field
	private void readAdjacencyMatrix(String filePath)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String line;
			
			int c = 0;
			
			while((line = br.readLine()) != null) {
				Scanner lb = new Scanner(line);
				for(int i = 0; i < line.length(); i++) {
					adjacencyMatrix[c][i] = (int)(line.charAt(i) - '0');
				}
				c++;
				if(lb.hasNextLine()) {
				}
				lb.close();
			}
		}catch(Exception e) {
			System.out.println("File not Found");
			e.printStackTrace();
		}
		
	}
	
	//reads the labels input file, creates a new vertex for each label, and saves each new vertex
	//into the vertexList field
	private void readLabels(String filePath)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath));
			String label;
			int count = 0;
			String labelses = "";
			while((label = br.readLine()) != null) {
				labelses += label;
			}
			
			count = labelses.length();
			
			vertexList = new Vertex[count];
			adjacencyMatrix = new int[count][count];
			
			for(int i = 0; i < count; i++) {
				vertexList[i] = new Vertex(labelses.charAt(i));
			}
		}catch(Exception e) {
			System.out.println("File not Found");
			e.printStackTrace();
		}
		
		
	}
	
	//implements the depth first search algorithm calling
	//getAdjacentUnvisitedVertex() and displayVertex() as needed
	public void depthFirstSearch()
	{
		//implement a stack
		Stack<Vertex> stack = new Stack();
		
		//add first vertex to the stack
		stack.push(vertexList[0]);
		vertexList[0].wasVisited = true;		//mark as visited
		System.out.print(vertexList[0].label);
		
		int current = 0;
		
		while(!stack.isEmpty()) {
			
			//if a vertex is neighboring start point:
			int i = this.getAdjacentUnvisitedVertex(current);
			if(i != -1) {
				//repeat process
				stack.push(vertexList[i]);
				vertexList[i].wasVisited = true;
				System.out.print(vertexList[i].label);
				current = i;
			}
			else {
				stack.pop();
				//finish when stack is empty
				if(!stack.isEmpty()) {
					for(int j = 0; j < vertexList.length; j++) {
						if(vertexList[j].label == stack.peek().label) {
							current = j;
						}
					}
				}
			}
		}
		//sets all wasVisited values back to false
		clearVertices();
	}
	
	//implements the depth first search algorithm calling
	//getAdjacentUnvisitedVertex() and displayVertex() as needed
	public void breadthFirstSearch()
	{
		//marking the current vertex
		//vertexList[0] = A
		int current = 0;
		
		//checks to see if there are any remaining unvisited vertices
		//in the case that queue is ever empty but vertices remain
		boolean go = true;
		
		//uses Queue
		Queue<Vertex> queue = new LinkedList();
		//DON'T add the first vertex to the queue
		vertexList[0].wasVisited = true;		//mark as visited
		System.out.print(vertexList[0].label);
		
		//do while loop runs at least once: will run when queue is originally empty
		do {
			//if a vertex is neighboring start point:
			int i = this.getAdjacentUnvisitedVertex(current);
			if(i != -1) {
				//repeat process
				queue.add(vertexList[i]);
				vertexList[i].wasVisited = true;
				System.out.print(vertexList[i].label);
			}
			else {
				if(!queue.isEmpty()) {
					Vertex removed = queue.remove();
					for (int j = 0; j < vertexList.length; j++) {
						if(vertexList[j] == removed) {
							current = j;
						}
					}
					if(queue.isEmpty() && getAdjacentUnvisitedVertex(current) != -1) {
						go = true;
					}
					else if(queue.isEmpty()) {
						go = false;
					}
				}
			}	
		}while(go); 

		//clear visited property of nodes
		clearVertices();
	}
	
	//implements the minimum spanning tree algorithm calling 
	//getAdjacentUnvisitedVertex() and displayVertex() as needed
	public void minimum_spanning_tree()
	{
		//implement a stack
		Stack<Vertex> stack = new Stack();
		
		//add first vertex to the stack
		stack.push(vertexList[0]);
		vertexList[0].wasVisited = true;		//mark as visited
		System.out.print(vertexList[0].label);
		
		int current = 0;
		
		while(!stack.isEmpty()) {
			
			//if a vertex is neighboring start point:
			int i = this.getAdjacentUnvisitedVertex(current);
			if(i != -1) {
				//repeat process
				stack.push(vertexList[i]);
				vertexList[i].wasVisited = true;
				System.out.print(vertexList[i].label);
				
				//this is an addition. Prints first vertex of edge
				if(this.getAdjacentUnvisitedVertex(i) != -1) {
					System.out.print(" " + vertexList[i].label);
				}
				current = i;
			}
			else {
				stack.pop();
				//finish when stack is empty
				if(!stack.isEmpty()) {
					for(int j = 0; j < vertexList.length; j++) {
						if(vertexList[j].label == stack.peek().label) {
							
							//Prints second vertex of edge
							if(this.getAdjacentUnvisitedVertex(j) != -1) {
								System.out.print(" " + vertexList[j].label);
							}
							current = j;
						}
					}
				}
			}
		}
		//sets all wasVisited values back to false
		clearVertices();
	}
	
	//returns an unvisited vertex adjacent to the vertex indicated by vertexIndex
	//using the vertexList and adjacencyMatrix fields
	public int getAdjacentUnvisitedVertex(int vertexIndex)
	{
		for(int i = 0; i < adjacencyMatrix[vertexIndex].length; i++) {
			if((adjacencyMatrix[vertexIndex][i] == 1) && (vertexList[i].wasVisited == false)) {
				return i;
			}
		}
		return -1;
	}
	
	//sets all "wasVisited" values of vertices back to false so they can be 
	//researched accurately
	public void clearVertices()
	{
		for(int i = 0; i < vertexList.length; i++) {
			vertexList[i].wasVisited = false;
		}
	}
}
