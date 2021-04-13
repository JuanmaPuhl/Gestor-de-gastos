package PositionList;
import java.io.Serializable;
public class Entry implements Serializable {
	private double value;
	private String desc;
	private String fecha;
	private int tipo;
	//private String hora;
	
	public Entry(double v,String d,String f,int t){
		value=v;
		desc=d;
		fecha=f;
		tipo=t;
	}
	public double getValue(){
		return value;
	}
	public String getDesc(){
		return desc;
	}
	public String getFecha(){
		return fecha;
	}
	public int getType(){
		return tipo;
	}
/*	public String getHora(){
		return hora;
	}*/
	public void setType(int t){
		tipo=t;
	}
	public void setValue(double v){
		value=v;
	}
	public void setDesc(String d){
		desc=d;
	}
	public void setFecha(String f){
		fecha=f;
	}
	/*public void setHora(String h){
		hora=h;
	}*/
}
