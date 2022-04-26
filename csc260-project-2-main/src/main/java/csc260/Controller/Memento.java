package csc260.Controller;
import csc260.Document.Edge;
import csc260.Document.Node;
import org.jgrapht.Graph;

public class Memento {
	public Graph<Node, Edge> internal;

	public Memento(Graph<Node, Edge> saveInternal){
		internal = saveInternal;
	}

	public Graph<Node, Edge> getSavedInternal(){
		return internal;
	}

}
