package PositionList;


import java.io.Serializable;

public interface PositionList extends Serializable {
	public int size();
	public boolean isEmpty();
	public Position first() throws EmptyPositionListException;
	public Position last() throws EmptyPositionListException;
	public Position next(Position p) throws InvalidPositionException, BoundaryViolationException;
	public Position prev(Position p) throws InvalidPositionException, BoundaryViolationException;
	public void addFirst(Entry e);
	public void addLast(Entry e);
	public void addAfter(Position p, Entry e) throws InvalidPositionException;
	public void addBefore(Position p, Entry e) throws InvalidPositionException;
	public Entry remove(Position p) throws InvalidPositionException;
	public Entry set(Position p, Entry e) throws InvalidPositionException;
}
