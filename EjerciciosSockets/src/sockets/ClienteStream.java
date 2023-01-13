package sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteStream {
	
	public static void main(String[] args) {
		String host = "192.168.2.61";
		int puerto = 6000;
		
		// creamos el socket
		try {
			Socket misocket = new Socket(host, puerto);
		
		// preparo el sitio donde escribir� en el socket
			OutputStream escrituraSocket = misocket.getOutputStream();
			OutputStreamWriter escritor = new OutputStreamWriter(escrituraSocket);
			BufferedWriter escribirAlServidor = new BufferedWriter(escritor);
		
		// env�o algo al servidor
			escribirAlServidor.write("Hola Servidor!!, soy Ángel");
			escribirAlServidor.newLine();
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
