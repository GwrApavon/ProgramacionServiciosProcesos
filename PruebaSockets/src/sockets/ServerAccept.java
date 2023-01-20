package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAccept extends Thread{

	private Socket cliente;
	private ServerSocket server;
	
	public ServerAccept(ServerSocket ss, Socket c) {
		this.cliente = c;
		this.server = ss;
	}
	
	public void run() {
		try {				
			// Para leer lo que llegue por el nuevo socket
			InputStream lecturaSocket = cliente.getInputStream();
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
