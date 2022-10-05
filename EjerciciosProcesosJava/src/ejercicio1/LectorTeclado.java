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
		String entrada = "";
		try {
			System.out.println("Primer Número:");
			entrada = br.readLine();
			int num1 = Integer.parseInt(entrada);
			
			System.out.println("Segundo Número:");
			entrada = br.readLine();
			int num2 = Integer.parseInt(entrada);
			
			System.out.println("La division de " + num1 + "/" + num2 +" es: " + (num1/num2));
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		


	}

}
