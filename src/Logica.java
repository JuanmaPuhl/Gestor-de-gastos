import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Scanner;

import PositionList.BoundaryViolationException;
import PositionList.DoubleLinkedList;
import PositionList.EmptyPositionListException;
import PositionList.Entry;
import PositionList.InvalidPositionException;
import PositionList.Position;
import PositionList.PositionList;

public class Logica {
	private static Scanner sc = new Scanner(System.in); // Declaro e inicializo
	// el scanner, para leer
	// datos ingresados por
	// el usuario.

	private static Calendar fecha = Calendar.getInstance(); // Variable hecha para que tome la fecha seleccionada y que
	// no vuelva a la actual
	private static File data = new File("Files/data.txt");
	private static File config = new File("Files/config.txt");
	private static PositionList lista;

	public Logica() {
		lista = new DoubleLinkedList();
	}

	public void checkFiles() {
		// Corroboro que los archivos existan...
		if (data.exists() && config.exists()) {
			//System.out.println("Bienvenido de nuevo al Gestor de Ganancias Alfa.");
		} else {
			try {
				//System.out.println("Bienvenido al gestor de ganancias. Se estan creando los archivos necesarios...");

				if (!data.exists()) {
					data.createNewFile();
					//System.out.println("Se ha creado el archivo 'data'.");
					saveState(lista);
				}
				if (!config.exists()) {
					config.createNewFile();
					//System.out.println("Se ha creado el archivo 'config'.");
				}
			} catch (IOException ioe) {
				System.out.println(ioe.getMessage());
			}
		}
	}
	
	public void guardarEsquema(String aux) {
		try {
			FileWriter fichero= new FileWriter(config);
			PrintWriter pw= new PrintWriter(fichero);
			pw.print(aux);
			fichero.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String leerEsquema() {
		String aux="";
		try {
			FileReader fr=new FileReader(config);
			BufferedReader br=new BufferedReader(fr);
			if((aux=br.readLine())==null)
				aux="";
			if(null!=fr)
				fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aux;
	}
	
	public static String obtenerMes() {
		int nombreDia = fecha.get(Calendar.MONTH);
		String nombre = "";
		switch (nombreDia) {
			case (0): {
				nombre = "Enero";
				break;
			}
			case (1): {
				nombre = "Febrero";
				break;
			}
			case (2): {
				nombre = "Marzo";
				break;
			}
			case (3): {
				nombre = "Abril";
				break;
			}
			case (4): {
				nombre = "Mayo";
				break;
			}
			case (5): {
				nombre = "Junio";
				break;
			}
			case (6): {
				nombre = "Julio";
				break;
			}
			case (7): {
				nombre = "Agosto";
				break;
			}
			case (8): {
				nombre = "Septiembre";
				break;
			}
			case (9): {
				nombre = "Octubre";
				break;
			}
			case (10): {
				nombre = "Noviembre";
				break;
			}
			case (11): {
				nombre = "Diciembre";
				break;
			}
		}

		return nombre;
	}

	public static String obtenerDiayNombre() {
		return ""+fecha.get(Calendar.DATE);
	}

	public static String obtenerFecha(int n) {
		// Calendar fecha=Calendar.getInstance();
		if (n == 1 || n == -1)
			fecha.add(Calendar.DATE, n);
		if (n == 10 || n == -10)
			fecha.add(Calendar.MONTH, n / 10);
		String s = fecha.get(Calendar.DATE) + "/" + (fecha.get(Calendar.MONTH) + 1) + "/" + fecha.get(Calendar.YEAR);
		return s;
	}
	public static String obtenerAnio() {
		String s = ""+fecha.get(Calendar.YEAR);
		return s;
	}

	public void insertar(double monto,String desc,int tipo,String f) {
		//String f= obtenerFecha(0);
		Entry entrada;
		if(tipo==2) {
			 entrada=new Entry(-1*monto,desc,f,tipo);
		}
		else
			entrada=new Entry(monto,desc,f,tipo);
		lista.addLast(entrada);
		saveState(lista);
	}
	
	private static void ingresar() {
		System.out.println("Ingrese el valor de la transaccion: ");
		int v = sc.nextInt();
		sc.nextLine();
		System.out.println("Ingrese una descripcion de la transaccion: ");
		String d = sc.nextLine();
		String f = obtenerFecha(0);
		int t = pedirTipo();
		Entry entrada = new Entry(v, d, f, t);
		lista.addLast(entrada);
		saveState(lista);
	}

	public  void saveAux() {
		saveState(lista);
	}
	private static void saveState(PositionList l) { // serializar
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Files/data.txt"));
			output.writeObject(l);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void recuperarAux() {
		recuperar();
	}
	private static void recuperar() {
		PositionList l = null;

		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream("Files/data.txt"));
			l = (PositionList) input.readObject();
			input.close();
			if (l != null && !l.isEmpty()) {
				Position p = l.first();
				Position u = l.last();
				while (p != null) {
					// System.out.println(p.entry().getFecha() + " " +p.entry().getValue()+ " " +
					// p.entry().getDesc());
					lista.addLast(p.entry());
					if (p == u) {
						// System.out.println("]");
						p = null;
					} else {
						p = lista.next(p);
					}
				}
			}
			if (l != null && !l.isEmpty()) {
				Position p = l.first();
				Position u = l.last();
				while (p != null) {
					l.remove(p);
					if (p == u)
						p = null;
					else
						p = lista.next(p);
				}
			}
			saveState(l);

		} catch (IOException | ClassNotFoundException | EmptyPositionListException | BoundaryViolationException
				| InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static int pedirTipo() {
		System.out.println("Elija una opcion: ");
		System.out.println("1: Ingreso.");
		System.out.println("2: Gasto.");
		System.out.println("3: Plata prestada / Plata devuelta.");
		int v = sc.nextInt();
		return v;
	}

	public static Object[][] cargarUltimasDiez(){
		return null;
	}
	public Object[][] mostrarHistorial(Object [][] arr, int size,int max){
		return historial3(arr,size,max);
	}
	private static Object[][] historial3(Object [][] arr, int size,int max) {
		if(!lista.isEmpty()){
			try {
				Position p = lista.first();
				while (p != null) {
					Entry e=p.entry();
					if(size>=arr.length) {//Debo agrandar 
						int aux=2*max;
						Object [][] arr2= new Object[aux][4];
						for(int i=0; i<arr.length; i++) {
							for(int j=0; j<4; j++) {
								arr2[i][j]=arr[i][j];
							}
						}
						arr=arr2;
						max*=2;
					}
					arr[size][0]=e.getFecha();
					arr[size][1]=obtenerTipo(e.getType());
					if(e.getType()==2) 
						arr[size][2]="-$"+(-1)*e.getValue();
					else
						if(e.getType()==3 && e.getValue()<0) {
							arr[size][2]="-$"+(-1)*e.getValue();
						}
						else
							arr[size][2]="$"+e.getValue();
					arr[size][3]=e.getDesc();
					size++;
					if (p == lista.last())
						p = null;
					else
						p = lista.next(p);
				}
			} catch (EmptyPositionListException | InvalidPositionException | BoundaryViolationException err) {
				System.out.println(err.getMessage());
			}
		}
		return arr;
	}
	private static String obtenerTipo(int n) {
		if(n==1)
			return "Ingreso";
		if(n==2)
			return "Gasto";
		if(n==3)
			return "Préstamo";
		return "";
	}

	public static void historial() {
		if (lista.isEmpty())
			System.out.println("Historial Vacio");
		else {
			try {

				Position p = lista.first();
				while (p != null) {
					System.out.println(p.entry().getFecha() + " Tipo" + p.entry().getType() + "  $"
							+ p.entry().getValue() + " Descripcion:" + p.entry().getDesc() + ".");
					if (p == lista.last())
						p = null;
					else
						p = lista.next(p);
				}
			} catch (EmptyPositionListException | InvalidPositionException | BoundaryViolationException err) {
				System.out.println(err.getMessage());
			}
		}
	}
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	public double saldoAux() {
		return saldo();
	}
	private static double saldo() {
		double saldo = 0.00;
		try {
			if (lista.isEmpty()) {
				return saldo;
			}
			Position p = lista.first();
			while (p != null) {
				saldo += p.entry().getValue();
				if (p != lista.last())
					p = lista.next(p);
				else
					p = null;
			}
			saldo=round(saldo,2);
		} catch (EmptyPositionListException | InvalidPositionException | BoundaryViolationException err) {
			System.out.println(err.getMessage());
		}
		return saldo;
	}

	public void limpiarHistorialAux() {
		clearHistory();
	}
	private static void clearHistory() {
		if (!lista.isEmpty()) {
			try {
				Position p = lista.first();
				while (p != null) {
					Position next = null;
					if (p != lista.last()) {
						next = lista.next(p);
					} else
						next = null;
					lista.remove(p);
					p = next;
				}
			} catch (EmptyPositionListException | BoundaryViolationException | InvalidPositionException err) {
				System.out.println(err.getMessage());
			}
		}
	}
	private static int fechaMayor(String s1,String s2) {
		String dia1="",dia2="",mes1="",mes2="",anio1="",anio2="";
		/**Separo fecha1*/
		char c='\0';
		int i=0;
		while(c!='-' && c!='/'){
			c=s1.charAt(i);
			dia1=dia1+c;
			i++;
			System.out.println(c+" size= "+s1.length());
			c=s1.charAt(i);
			
		}
		c='\0';
		i++;
		while(c!='-' && c!='/'){
			c=s1.charAt(i);
			i++;
			mes1=mes1+c;
			c=s1.charAt(i);
		}

		c='\0';
		i++;
		while(i<s1.length()){	
			c=s1.charAt(i);
			i++;
			anio1=anio1+c;
		}

		/**Separo fecha2*/
		i=0;
		
		while(c!='-' && c!='/'){
			c=s2.charAt(i);
			i++;
			dia2=dia2+""+c;
			c=s2.charAt(i);
		}
		c='\0';
		i++;
		while(c!='-' && c!='/'){
			c=s2.charAt(i);
			i++;
			mes2=mes2+c;
			c=s2.charAt(i);
		}
		i++;
		while(i<s2.length()){
			c=s2.charAt(i);
			i++;
			anio2=anio2+c;	
		}
		int anio1Int=Integer.parseInt(anio1);
		int anio2Int=Integer.parseInt(anio2);
		//System.out.println("Año1:"+anio1Int+" Anio2:"+anio2Int);
		if(anio1Int<anio2Int) 
			return 2;
		if(anio1Int>anio2Int)
			return 1;
		else {
			int mes1Int=Integer.parseInt(mes1);
			int mes2Int=Integer.parseInt(mes2);
			//System.out.println("Mes1:"+mes1Int+" Mes2:"+mes2Int);
			if(mes1Int<mes2Int)
				return 2;
			if(mes1Int>mes2Int)
				return 1;
			else {
				int dia1Int=Integer.parseInt(dia1);
				int dia2Int=Integer.parseInt(dia2);
				//System.out.println("Dia1:"+dia1Int+" Dia2:"+dia2Int);
				if(dia1Int<dia2Int)
					return 2;
				if(dia1Int>dia2Int)
					return 1;
				else
					return 0;
			}
		}
	}
	public Object[][] filtrar(Object [][] arr, int size,int max,int t1,int t2,int t3,int minimo,int maximo,String fecha){
		return showType2(arr,size,max,t1,t2,t3,minimo,maximo,fecha);
	}
	
	private static Object[][] showType2(Object [][] arr, int size,int max,int t1,int t2,int t3,int minimo,int maximo,String fecha) {
		if(!lista.isEmpty()){
			try {
				Position p = lista.first();
				while (p != null) {
					Entry e=p.entry();
					if(size>=arr.length) {//Debo agrandar 
						int aux=2*max;
						Object [][] arr2= new Object[aux][4];
						for(int i=0; i<arr.length; i++) {
							for(int j=0; j<4; j++) {
								arr2[i][j]=arr[i][j];
							}
						}
						arr=arr2;
						max*=2;
					}
					int auxiliar=fechaMayor(e.getFecha(),fecha);
					//System.out.println("El valor auxiliar es: "+auxiliar);
					if((e.getType()==t1 || e.getType()==t2 || e.getType()==t3) && e.getValue()>=minimo && e.getValue()<=maximo && auxiliar==1 || auxiliar==0) {
					//System.out.println("Entre");
					arr[size][0]=e.getFecha();
					arr[size][1]=obtenerTipo(e.getType());
					arr[size][2]="$"+e.getValue();
					arr[size][3]=e.getDesc();
					size++;
					}
					if (p == lista.last())
						p = null;
					else
						p = lista.next(p);
				}
			} catch (EmptyPositionListException | InvalidPositionException | BoundaryViolationException err) {
				System.out.println(err.getMessage());
			}
		}
		return arr;
	}
	
	private static void showType(int t,int t2,int t3) {
		int cant = 0;
		if (lista.isEmpty())
			System.out.println("Historial vacio");
		else {
			try {
				Position p = lista.first();
				while (p != null) {
					Entry e = p.entry();
					if (e.getType() == t || e.getType()==t2 || e.getType()==t3) {
						System.out.println(e.getFecha() + " Tipo: " + e.getType() + " $" + e.getValue()
								+ " Descripcion: " + e.getDesc());
						cant++;
					}
					if (p == lista.last())
						p = null;
					else
						p = lista.next(p);
				}

			} catch (EmptyPositionListException | BoundaryViolationException | InvalidPositionException err) {
				System.out.println(err.getMessage());
			}
		}
		if (cant == 0)
			System.out.println("No se encontraron entradas con ese tipo");
	}
	public float calcularSaldo() {
		float saldo=0;
		try {
			Position p=lista.first();
			while(p!=null) {
				if(p.entry().getType()==3) {
					saldo+=p.entry().getValue();
				}
				if(p!=lista.last())
					p=lista.next(p);
				else
					p=null;
			}
			
			
		} catch (EmptyPositionListException|BoundaryViolationException|InvalidPositionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return saldo;
	}
	
	private static void seleccionar() {
		saveState(lista);
		int n;
		n = sc.nextInt();

		switch (n) {
		case 1: {
			ingresar();
			showMenu(1);
			break;
		}
		case 2: {
			historial();
			showMenu(1);
			break;
		}
		case 3: {
			saldo();
			showMenu(1);
			break;
		}
		case 4: {
			clearHistory();
			showMenu(1);
			break;
		}
		case 5: {
			int t = pedirTipo();
			showType(t,0,0);
			showMenu(1);
			break;
		}
		case 6: {
			pressEnterToContinue();
			saveState(lista);
			break;
		}
		case 9: {
			System.out.println(obtenerFecha(0));
			System.out.println(obtenerFecha(1));
			System.out.println(obtenerFecha(0));
			System.out.println(obtenerFecha(-1));
			System.out.println(obtenerFecha(0));
			System.out.println(obtenerFecha(-10));
			System.out.println(obtenerFecha(0));
		}
		default: {
			System.out.println("Opcion incorrecta");
			showMenu(1);
			break;
		}
		}
	}

	private static void pressEnterToContinue() {
		System.out.println("Presione ENTER para continuar...");
		try {
			System.in.read();
		} catch (Exception e) {
			System.out.println("Ocurrio un problema");
		}
	}

	/**
	 * Limpia la pantalla
	 */
	private static void clearScreen() {
		try {
			final String os = System.getProperty("os.name");
			if (os.contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}

	/**
	 * Limpia la pantalla, muestra el menu y deja seleccionar una opcion.
	 */
	public static void showMenu(int i) {
		if (i == 1) {
			pressEnterToContinue();
			clearScreen();
		}
		if (i == 0) {
			recuperar();
		}

		System.out.println("Menu:");
		System.out.println("1: Ingresar datos.");
		System.out.println("2: Historial de transacciones.");
		System.out.println("3: Consultar Saldo.");
		System.out.println("4: Limpiar historial.");
		System.out.println("5: Sort by.");
		System.out.println("6: Salir.");
		System.out.println("Ingrese el numero de accion deseada");
		seleccionar();
	}

}
