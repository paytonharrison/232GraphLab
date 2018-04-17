package graphlab;


public class Vertex {
	
	//instance variables
	public char label;	//label (i.e. 'A')
	public boolean wasVisited;
	
	//constructor
	public Vertex(char lab)
	{
		label = lab;
		wasVisited = false;
	}

}
