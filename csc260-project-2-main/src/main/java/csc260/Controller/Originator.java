package csc260.Controller;
import csc260.Document.Edge;
import csc260.Document.Node;
import org.jgrapht.Graph;


public class Originator {


	private Graph<Node, Edge> internal;

	public void set(Graph<Node, Edge> newInternal){

		internal = newInternal;
	}

	public Memento storeInMemento(){

		return new Memento(internal);
	}

	public Graph<Node, Edge> restoreFromMemento(Memento memento){
		internal = memento.getSavedInternal();
		return internal;
	}

}
