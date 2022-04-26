package csc260.Controller;
import java.util.ArrayList;

public class CareTaker {
	ArrayList<Memento> savedInternals = new ArrayList<Memento>();

	public void addMemento(Memento m){
		savedInternals.add(m);

	}

	public Memento getMemento(int index){
		return savedInternals.get(index);
	}

	public void clearFromIndex(int index){ savedInternals.subList(index, savedInternals.size()).clear(); }

	public void print(){
		System.out.println(savedInternals);
	}

	public int size(){
		return savedInternals.size();
	}

}
