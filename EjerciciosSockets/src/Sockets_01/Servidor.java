/**
 * 
 */
package Sockets_01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import sockets.ServidorAccept;

/**
 * @author alu
 *
 */
public class Servidor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int puerto = 6000;
		
		try {
			ServerSocket miServidor = new ServerSocket(puerto);
			int cl = 3;
			do {	
				System.out.println("SERVIDOR: Escuchando por el puerto " + puerto);
				miServidor.setSoTimeout(0);
				Socket cliente = miServidor.accept();
				System.out.println("se ha conectado, esperando entrada Cliente");
				
				OutputStream escrituraSocket = cliente.getOutputStream();
				OutputStreamWriter escritor = new OutputStreamWriter(escrituraSocket);
				BufferedWriter escribirAlServidor = new BufferedWriter(escritor);
				
				escribirAlServidor.write("Bienvenido Cliente");
				escribirAlServidor.newLine();
				escribirAlServidor.flush();	
				escribirAlServidor.close();
				
				Proceso pcs = new Proceso(cliente);
				pcs.start();
				cl--;
			}while(cl > 0);
			// cierro todos los recursos
			miServidor.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
