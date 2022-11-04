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
		String r = "D:\\Descargas\\";
		
		File f = new File(r);
		
		String [] lista = f.list();
		int vuelta = 0;
		for (String s : lista) {
			if(vuelta%2 == 0 || vuelta == 0) {
				fpar.add(r + s);
			}
			else if(vuelta%2 != 0){
				fimpar.add(r + s);
			}
			vuelta++;
		}
		for (String string : fimpar) {
			System.out.println(string);
		}
		Hilo par = new Hilo(fpar);
		par.start();
			
		Hilo impar = new Hilo(fimpar);
		impar.start();
		
		try {
			par.join();
			impar.join();
		}catch(InterruptedException e) {
		}
		System.out.println("El total de lineas entre los 4 ficheros es: " + (par.getTotalLineas() + impar.getTotalLineas()));
	}

}
