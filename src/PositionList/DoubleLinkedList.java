package PositionList;

import java.io.Serializable;
import java.util.Iterator;

public class DoubleLinkedList implements PositionList,Serializable{
	private Node header;
 	private Node trailer;
 	private int size;
 	
 	public DoubleLinkedList(){
 		size = 0;
		header = trailer = new Node();
		header.setNext(trailer);
		trailer.setPrev(header);
 	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	public Position first() throws EmptyPositionListException {
		if(isEmpty())
			throw new EmptyPositionListException("Lista Vacia");
		return header.getNext();
	}
	public Position last() throws EmptyPositionListException {
		if(isEmpty())
			throw new EmptyPositionListException("Lista Vacia");
		return trailer.getPrev();
	}
	public Position prev(Position p) throws InvalidPositionException, BoundaryViolationException {
		if (p == null)
			throw new InvalidPositionException("Posicion nula");
		if (p == header.getNext())
			throw new BoundaryViolationException("No tiene anterior");
		Node n= (Node)p;
		return n.getPrev();
	}
	public Position next(Position p) throws InvalidPositionException, BoundaryViolationException {
		if (p == null)
			throw new InvalidPositionException("Posicion nula");
		if (p == trailer.getPrev())
			throw new BoundaryViolationException("No tiene siguiente");
		Node n= (Node)p;
		return n.getNext();
	}
	public void addFirst(Entry e) {
		Node n = new Node();
		n.setEntry(e);
		n.setPrev(header);
		n.setNext(header.getNext());
		header.getNext().setPrev(n);
		header.setNext(n);
		size++;
	}
	
	public void addLast(Entry e) {
		Node n = new Node();
		n.setEntry(e);
		n.setNext(trailer);
		trailer.getPrev().setNext(n);
		n.setPrev(trailer.getPrev());
		trailer.setPrev(n);
		size++;
	}
	public void addBefore(Position p, Entry e) throws InvalidPositionException {
		if(p==null)
			throw new InvalidPositionException("Posicion nula");
		Node n=(Node)p;
		Node nuevo= new Node();
		nuevo.setEntry(e);
		Node prev=n.getPrev();
		nuevo.setPrev(prev);
		prev.setNext(nuevo);
		n.setPrev(nuevo);
		size++;
	}
	public void addAfter(Position p, Entry e) throws InvalidPositionException {
		if(p==null)
			throw new InvalidPositionException("Posicion nula");
		Node n=(Node)p;
		Node nuevo= new Node();
		nuevo.setEntry(e);
		Node next=n.getNext();
		nuevo.setNext(next);
		next.setPrev(nuevo);
		n.setNext(nuevo);
		size++;
	}
	public Entry remove(Position p) throws InvalidPositionException {
		if(p==null)
			throw new InvalidPositionException("Posicion nula");
		Node n= (Node) p;
		Entry aux=n.entry();
		n.setEntry(null);
		if(size>1){
			Node next=n.getNext();
			Node prev=n.getPrev();
			next.setPrev(prev);
			prev.setNext(next);	
		}
		size--;
		
		n.setNext(null);
		n.setPrev(null);
		
		return aux;
	}
	public Entry set(Position p, Entry e) throws InvalidPositionException {
		if(p==null)
			throw new InvalidPositionException("Posicion nula");
		Node n= (Node) p;
		Entry aux=n.entry();
		n.setEntry(e);
		return aux;
	}
	
 	
}
