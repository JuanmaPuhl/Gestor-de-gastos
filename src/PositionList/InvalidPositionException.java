package PositionList;
import java.io.Serializable;
@SuppressWarnings("serial")
public class InvalidPositionException extends Exception {
	public InvalidPositionException(String msg){
		super(msg);
	}

}
