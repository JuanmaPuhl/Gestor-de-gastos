public class principal {
	
	private static Logica l;

	public static void main(String[] args) {
		/**
		 * Gestor de Ganancias y deudas. Idea general: Almacenar datos en
		 * distintos archivos, uno para cada seccion. Lista de tareas: Crear
		 * GUI. Manejar archivos. Hacer secciones
		 */
		/*
		 * Por ahora me centro en poder ingresar deudas y guardarlas
		 */
		l=new Logica();
		l.checkFiles();
		l.recuperarAux();
		//l.showMenu(0);
		//l.guardarEsquema("Prueba");
		System.out.println(l.leerEsquema());
	}	
}
