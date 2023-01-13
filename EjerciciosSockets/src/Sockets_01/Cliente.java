/**
 * 
 */
package Sockets_01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author alu
 *
 */
public class Cliente {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String host = "192.168.2.61";
		int puerto = 6000;
		
		// creamos el socket
		try {
			Socket misocket = new Socket(host, puerto);

			System.out.println("Esperando respuesta del servidor");
			Proceso p = new Proceso (misocket);
			p.join();
			OutputStream escrituraSocket = misocket.getOutputStream();
			OutputStreamWriter escritor = new OutputStreamWriter(escrituraSocket);
			BufferedWriter escribirAlServidor = new BufferedWriter(escritor);
			
			escribirAlServidor.write("Hola servidor");
			escribirAlServidor.newLine();
			escribirAlServidor.flush();	
			escribirAlServidor.close();
			misocket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
