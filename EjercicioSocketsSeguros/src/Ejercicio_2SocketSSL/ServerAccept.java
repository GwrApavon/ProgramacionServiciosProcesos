package Ejercicio_2SocketSSL;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Random;

import javax.net.ssl.SSLSocket;

public class ServerAccept extends Thread{

	private SSLSocket cliente;
	
	public ServerAccept(SSLSocket c) {
		this.cliente = c;
	}
	
	public void run() {
		try {					
			//Preparo el sitio donde escribir� en el socket
			OutputStream escrituraSocket = cliente.getOutputStream();
			OutputStreamWriter escritor = new OutputStreamWriter(escrituraSocket);
			BufferedWriter escribirCliente = new BufferedWriter(escritor);
			//Preparo el sitio para leer
			InputStream lecturaSocket = cliente.getInputStream();
			InputStreamReader lector = new InputStreamReader(lecturaSocket);
			BufferedReader leeRespuesta = new BufferedReader(lector);
			
			
			String linea = leeRespuesta.readLine(); 
			System.out.println("He leido: " + linea);
			
			escribirCliente.write("Bienvenido cliente, ya puedes hablar infinitamente conmigo");
			escribirCliente.newLine();
			escribirCliente.flush();
			sleep(3000);
			do {
				linea = leeRespuesta.readLine(); 
				System.out.println("He leido: " + linea);
			}while(!linea.equals("*"));
			escribirCliente.close();		
		}catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
