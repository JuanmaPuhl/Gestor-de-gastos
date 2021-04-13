package PositionList;
import java.io.Serializable;
public class Node implements Position,Serializable {
	private Node next;
	private Node prev;
	private Entry entrada;

	public Node(){
		next=null;
		prev=null;
		entrada=null;
	}
	public void setPrev(Node n){
		prev=n;
	}
	public void setNext(Node n){
		next=n;
	}
	public void setEntry(Entry e){
		entrada=e;
	}
	public Node getPrev(){
		return prev;
	}
	public Node getNext(){
		return next;
	}
	public Entry entry(){
		return entrada;
	}	
}
