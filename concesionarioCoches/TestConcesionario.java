package concesionarioCoches;

import utiles.Menu;
import utiles.Teclado;

/**
 * Queremos modelar un concesionario de coches en Java. Nos limitaremos a las
 * siguientes opciones:
 * <ol>
 * <li>Alta de un coche (se pedirá matricula, color y modelo),</li>
 * <li>Baja de un coche (por matrícula)</li>
 * <li>Mostrar un coche (por matrícula)</li>
 * <li>Mostrar concesionario (todos los coches del concesionario)</li>
 * <li>Contar el número de coches en el concesionario</li>
 * <li>Mostrar coches de un color</li>
 * </ol>
 * <p>
 * Lógicamente, no podrá añadirse un coche inválido o ya contenido (No pueden
 * existir dos coches con la misma matrícula en el concesionario) Por cada p que
 * se dé de alta, han de conocerse todas sus características. Ninguna de las
 * características del coche puede ser por defecto.
 * 
 * @author Miguel Ángel Gavilán Merino
 * 
 */
public class TestConcesionario {

	static String[] opciones = { "Alta de un coche", "Baja de un coche (por matrícula)",
			"Mostrar un coche (por matrícula)", "Mostrar todos los coches",
			"Contar el número de coches del concesionario", "Mostrar coches de un color", "Salir" };
	static Menu menu = new Menu("\n---Test Concesionario---", opciones);

	static Menu menuModelos = new Menu("\n---Escoja modelo---", Modelo.CORDOBA.generarOpcionesMenu());
	static Menu menuColores = new Menu("\n---Escoja color---", Color.ROJO.generarOpcionesMenu());

	static Concesionario lista = new Concesionario();

	public static void main(String[] args) {
		int opcion;

		do {
			opcion = menu.gestionar();

			switch (opcion) {
			case 1:
				altaCoche();
				break;

			case 2:
				bajaCoche();
				break;

			case 3:
				mostrarCoche();
				break;

			case 4:
				mostrarConcesionario();
				break;

			case 5:
				contarCoches();
				break;

			case 6:
				mostrarCochesColor();
				break;
			default:
				System.out.println("\nSALIENDO...");
				break;
			}
		} while (opcion != 7);

	}

	/**
	 * Muestra todos los coches del color pedido
	 */
	private static void mostrarCochesColor() {
		System.out.println(lista.getCocheColor(pedirColor()));

	}

	/**
	 * Muestra el número de coches del concesionario
	 */
	private static void contarCoches() {
		System.out.println("\nHay " + lista.size() + " coches en el concesionario");
	}

	/**
	 * Muestra todos los coches del concesionario
	 */
	private static void mostrarConcesionario() {
		System.out.println(lista.toString());
	}

	/**
	 * Busca el coche a través de la matrícula pedida por teclado
	 */
	private static void mostrarCoche() {
		String matricula = Teclado.leerCadena("Introduzca una matrícula para buscar el coche: ");
		try {
			System.out.println(lista.getCoche(matricula));
		} catch (MatriculaNoValidaException e) {
			System.err.println(e.getMessage());
		} catch (CocheNoExisteException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Elimina un coche del concesionario
	 */
	private static void bajaCoche() {
		String matricula = Teclado.leerCadena("Introduzca una matrícula: ");
		try {

			System.out.println("\nCoche eliminado correctamente: " + lista.getCoche(matricula) );
		} catch (CocheNoExisteException e) {
			System.err.println(e.getMessage());
		} catch (MatriculaNoValidaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Da de alta un coche en el concesionario
	 */
	private static void altaCoche() {
		String matricula = Teclado.leerCadena("Introduzca una matrícula: ");
		try {
			lista.annadir(matricula, pedirModelo(), pedirColor());
			System.out.println("\nCoche añadido correctamente");
		} catch (MatriculaNoValidaException e) {
			System.err.println(e.getMessage());
		} catch (ModeloNoValidoException e) {
			System.err.println(e.getMessage());
		} catch (ColorNoValidoException e) {
			System.err.println(e.getMessage());
		} catch (CocheYaExisteException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Pide un color
	 * 
	 * @return Color deseado
	 */
	private static Color pedirColor() {
		return Color.values()[menuColores.gestionar() - 1];
	}

	/**
	 * Pide un modelo
	 * 
	 * @return Modelo deseado
	 */
	private static Modelo pedirModelo() {
		return Modelo.values()[menuModelos.gestionar() - 1];
	}

}
