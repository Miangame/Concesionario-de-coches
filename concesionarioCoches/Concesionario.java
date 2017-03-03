package concesionarioCoches;

import java.util.ArrayList;

/*
 * No pueden existir dos coches con la misma matrícula en el almacén del concesinario
 * no puede añadirse un coche al concecionario con alguno de sus atributos inválidos. Han de conocerse todas sus características 
 * Ninguno de los valores puede ser por defecto
 */
/**
 * Representa un concesionario de coches.
 * 
 * Lógicamente, no podrá añadirse un coche inválido almacén del concesinario)
 * 
 * Han de conocerse todas sus características Ninguno de los valores puede ser
 * por defecto
 * 
 * @author Miguel Ángel Gavilán Merino
 * 
 */
public class Concesionario {
	/**
	 * Lista con coches
	 */
	private ArrayList<Coche> lista = new ArrayList<Coche>();

	/**
	 * Añade un coche al concesionario
	 * 
	 * @param matricula
	 * @param modelo
	 * @param color
	 * @throws CocheYaExisteException
	 * @throws MatriculaNoValidaException
	 * @throws ModeloNoValidoException
	 * @throws ColorNoValidoException
	 */
	void annadir(String matricula, Modelo modelo, Color color)
			throws CocheYaExisteException, MatriculaNoValidaException, ModeloNoValidoException, ColorNoValidoException {
		Coche coche = new Coche(matricula, modelo, color);
		if (!lista.contains(coche)) {
			lista.add(coche);
		} else {
			throw new CocheYaExisteException("El coche ya existe");
		}
	}

	/**
	 * Elimina un coche del concesionario
	 * 
	 * @param matricula
	 * @return 
	 * @throws CocheNoExisteException
	 * @throws MatriculaNoValidaException
	 */
	boolean eliminar(String matricula) throws CocheNoExisteException, MatriculaNoValidaException {
		Coche coche = new Coche(matricula);
		if (lista.contains(coche)) {
			return lista.remove(coche);
		} else {
			throw new CocheNoExisteException("El coche no existe");
		}
	}

	/**
	 * Obtiene un coche del concesionario a través de la matrícula
	 * 
	 * @param matricula
	 * @return Coche del concesionario
	 * @throws MatriculaNoValidaException
	 * @throws CocheNoExisteException
	 */
	Coche getCoche(String matricula) throws MatriculaNoValidaException, CocheNoExisteException {
		Coche coche = new Coche(matricula);
		if (lista.contains(coche)) {
			return lista.get(lista.indexOf(coche));
		} else {
			throw new CocheNoExisteException("El coche no existe");
		}
	}

	/**
	 * Devuelve una cadena con los coches del color deseado
	 * 
	 * @param color
	 * @return candena con los coches de un color
	 */
	StringBuilder getCocheColor(Color color) {
		StringBuilder cadena = new StringBuilder();
		for (Coche coche : lista) {
			if (coche.getColor() == color) {
				cadena.append(coche);
			}
		}
		return cadena;
	}

	/**
	 * Número de coches del concesionario
	 * 
	 * @return tamaño de la lista
	 */
	int size() {
		return lista.size();
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		int i = 0;
		if (lista.isEmpty()) {
			cadena.append("\nEl concesionario está vacio\n");
		}
		for (Coche coche : lista) {
			cadena.append("\n" + (i + 1) + ". " + coche);
			i++;
		}

		return cadena.toString();
	}

}
