/**
 * 
 */
package Ejercicio_09;

import java.util.*;

/**
 * @author alu
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList <String> ficheros = new ArrayList<String>();
		
		ficheros.add("fichero1.txt");
		ficheros.add("fichero2.txt");
		ficheros.add("fichero3.txt");
		ficheros.add("fichero4.txt");
		
		int vuelta = 1;
		Hilo par = null;
		Hilo impar = null;
		int totalLineas = 0;
		for (String fichero : ficheros) {
			
			if(vuelta%2 == 0) {
				par = new Hilo(fichero);
				par.start();
				totalLineas = par.contadorLineas;
			}
			else {
				impar = new Hilo(fichero);
				impar.start();
				totalLineas = impar.contadorLineas;
			}
			vuelta++;
		}
		do{
			
		}while(par.isAlive()||impar.isAlive());
		System.out.println("El total de lineas entre los 4 ficheros es: " + totalLineas);
	}

}
