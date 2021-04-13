package PositionList;
import java.io.Serializable;
@SuppressWarnings("serial")
public class EmptyPositionListException extends Exception{
	public EmptyPositionListException(String msg){
		super(msg);
	}
}
