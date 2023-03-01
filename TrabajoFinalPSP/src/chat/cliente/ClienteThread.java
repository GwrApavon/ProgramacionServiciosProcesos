package chat.cliente;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import javax.net.ssl.SSLSocket;

/**
 * Hilo para las funciones de un cliente
 * @author angel
 *
 */
public class ClienteThread extends Thread{

	private SSLSocket miSocket;
	BufferedWriter escribirAlServidor = null;
	BufferedReader leeRespuesta = null;
	
	public ClienteThread(SSLSocket s) {
		miSocket = s;
	}
	
	/**
	 * Función principal
	 * El cliente está permanentemente a la espera de los mensajes recibidos por el servidor
	 */
	public void run() {
		Scanner sc = new Scanner(System.in);
		try {

			//Preparo el sitio donde escribirá en el socket 
			escribirAlServidor = new BufferedWriter(new OutputStreamWriter(miSocket.getOutputStream()));
			//Preparo el sitio para leer
			leeRespuesta = new BufferedReader(new InputStreamReader(miSocket.getInputStream()));
			
			//Espero respuesta
			//Se terminaría la conversación con un "*"
			String linea;
			do {	
				linea = leeRespuesta.readLine(); 
				if(linea.equals("*")) {
					System.out.println("Se ha cerrado la conexion");
					sendMsg("#");
				}
				else {
					System.out.println(linea);
				}
			}while(!linea.equals("*"));
				leeRespuesta.close();
				escribirAlServidor.close();
				miSocket.close();
				sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para mandar un mensaje 
	 * Se llama desde otra clase
	 * @param msg mensaje que se envía
	 */
	public void sendMsg(String msg) {
		try {
			escribirAlServidor.write(msg);
			escribirAlServidor.newLine();
			escribirAlServidor.flush();
		} catch (IOException iee) {
			iee.printStackTrace();
		}
	}
	
	/**
	 * Getter del socket
	 * @return
	 */
	public SSLSocket getSocket() {
		return this.miSocket;
	}
	
	public boolean getStream() {
		return escribirAlServidor == null;
	}
}