/**
 * Crea un programa en Java en el package “ejercicio1” que lea de teclado dos números y muestre por la salida estándar la división 
 * del primero entre el segundo. El programa ha de controlar todos los errores que se puedan producir y sacarlos por la salida de error, 
 * la ejecución normal saldrá la salida output. 
 * Haz ahora otro programa que ejecute al primero y no se produzca ningún bloqueo. Ha de mostrarse la salida (tanto si es de error 
 * o es la correcta) de lo que el primer programa produce
 */
package ejercicio1;

import java.io.*;
import java.util.Scanner;

/**
 * @author alu
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		  Scanner s = new Scanner(System.in);
		  Runtime r = Runtime.getRuntime();    
		  
		  String comando= "java -cp bin ejercicio1.Division" ;
		  Process p=null;
	      try {
	  		  p = r.exec( comando );
	          InputStream is = p.getInputStream();
	          OutputStream os = p.getOutputStream();
	          BufferedWriter bw = new BufferedWriter(new OutputStreamWriter (os));
	          BufferedReader br = new BufferedReader(new InputStreamReader (is));    
	          
	          String linea;
			  while((linea = br.readLine())!=null) { //lee una linea del fichero 
				 
	              System.out.println(linea); 
	              bw.write(s.nextLine());
	              bw.write('\n');
	              bw.flush();
	             
	          }
			 bw.close();
             br.close();
			 
	        } 
	        catch (Exception e) {
	              e.printStackTrace();
	        }
			
			// CONTROL DE LOS MENSAJES DE ERROR
			try {
				InputStream es = p.getErrorStream();					
				BufferedReader br = new BufferedReader(new InputStreamReader(es));
				String liner = null;
				while ((liner = br.readLine()) != null) {
					System.out.println("ERROR >" + liner);
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			
			// COMPROBACION DE ERROR:  0 indica que ha terminado bien
			int exitVal;
			try {
				exitVal = p.waitFor();
				System.out.println("Valor de Salida: " + exitVal);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
	}  
	

}
