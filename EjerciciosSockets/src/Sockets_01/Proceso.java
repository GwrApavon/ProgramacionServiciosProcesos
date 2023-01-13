package Sockets_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Proceso extends Thread{

	private Socket sckt;

	
	public Proceso(Socket c) {
		this.sckt = c;
	}
	
	public void run() {
		try {				
			// Para leer lo que llegue por el nuevo socket
			InputStream lecturaSocket = sckt.getInputStream();
			InputStreamReader lector = new InputStreamReader(lecturaSocket);
			BufferedReader leeCliente = new BufferedReader(lector);
				
			String linea = leeCliente.readLine();
			System.out.println("He leido =>" + linea);
			leeCliente.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
