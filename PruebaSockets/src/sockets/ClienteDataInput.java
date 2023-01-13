package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteDataInput {
	
	public static void main(String[] args) {
		String host = "localhost";
		int puerto = 6000;
		
		// creamos el socket
		try {
			Socket misocket = new Socket(host, puerto);
				
		// preparo el sitio donde escribiré en el socket
			OutputStream escrituraSocket = misocket.getOutputStream();
			DataOutputStream escribirAlServidor = new DataOutputStream(escrituraSocket);
		
		// envío algo al servidor
			escribirAlServidor.writeUTF("Hola Servidor!!");
			escribirAlServidor.flush();
			
		// cerrar todos los streams y sockets
			escribirAlServidor.close();
			misocket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
