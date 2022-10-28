/**
 * 
 */
package Ejercicio_09;

import java.io.File;
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

		
		ArrayList <String> fpar = new ArrayList<String>();
		ArrayList <String> fimpar = new ArrayList<String>();

		
		File f = new File("D:\\Ángel Pavón\\WS - Programacón de servicios y procesos\\ProgramacionServiciosProcesos\\Hilos\\Ficheros");
		int vuelta = 0;
		for (String s : f.list()) {
			if(vuelta%2 == 0 || vuelta == 0) {
				fpar.add(s);
			}
			else if(vuelta%2 != 0){
				fimpar.add(s);
			}
		}
		for (String string : fimpar) {
			System.out.println(string);
		}
		Hilo par = new Hilo(fpar);
		par.start();
			
		Hilo impar = new Hilo(fimpar);
		impar.start();
		
		do{
			
		}while(par.isAlive()||impar.isAlive());
		System.out.println("El total de lineas entre los 4 ficheros es: " /*+  totalLineas*/);
	}

}
