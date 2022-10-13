/**
 * 
 */
package ejercicio1;

import java.io.*;


/**
 * @author alu
 *
 */
public class Division {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String entrada = "";
		try {
			System.out.println("Primer Numero:");
			entrada = br.readLine();
			int num1 = Integer.parseInt(entrada);
			
			System.out.println("Segundo Numero:");
			entrada = br.readLine();
			int num2 = Integer.parseInt(entrada);
			double division = 0;
			try {
				division = (double)num1/num2;
				System.out.println("La division de " + num1 + "/" + num2 +" es: " + division);
			}catch(ArithmeticException AE) {
				AE.printStackTrace();
			}
			br.close();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		


	}

}
