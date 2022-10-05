/**
 * 
 */
package ejercicio1;

import java.io.*;


/**
 * @author alu
 *
 */
public class LectorTeclado {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Primer Número:");
			int num1 = br.read();
			
			System.out.println("Segundo Número:");
			int num2 = br.read();
			
			System.out.println("La division de " + num1 + "/" + num2 +" es: " + (num1/num2));
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		


	}

}
