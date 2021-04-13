package PositionList;
import java.io.Serializable;
@SuppressWarnings("serial")
public class BoundaryViolationException extends Exception{
	public BoundaryViolationException(String msg){
		super(msg);
	}

}
