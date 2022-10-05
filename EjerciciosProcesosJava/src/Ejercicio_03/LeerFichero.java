/**
 * 
 */
package Ejercicio_03;

import java.io.*;
import java.util.Scanner;
/**
 * @author alu
 *
 */
public class LeerFichero {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		try {
			
			System.out.println("Introduzca la ruta del archivo que quieres leer:");
			String file = s.nextLine();
			File myFile = new File(file);
			FileReader fr = new FileReader(myFile);
			BufferedReader br = new BufferedReader(fr);
			String linea = "";
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			br.close();
			s.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
}
