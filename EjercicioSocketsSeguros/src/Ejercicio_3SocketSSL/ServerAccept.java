package Ejercicio_3SocketSSL;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.net.ssl.SSLSocket;

public class ServerAccept extends Thread{

	private SSLSocket cliente;
	
	public ServerAccept(SSLSocket c) {
		this.cliente = c;
	}
	
	public void run() {
		try {
			
			boolean salir = false;
			//Preparo el sitio donde escribir� en el socket
			OutputStream escrituraSocket = cliente.getOutputStream();
			OutputStreamWriter escritor = new OutputStreamWriter(escrituraSocket);
			BufferedWriter escribirCliente = new BufferedWriter(escritor);
			//Preparo el sitio para leer
			InputStream lecturaSocket = cliente.getInputStream();
			InputStreamReader lector = new InputStreamReader(lecturaSocket);
			BufferedReader leeRespuesta = new BufferedReader(lector);
			 
			
			escribirCliente.write("Bienvenido cliente, ya puedes hablar infinitamente conmigo");
			escribirCliente.newLine();
			escribirCliente.flush();
			
			sleep(3000);
			String linea = leeRespuesta.readLine(); 
			String lineaaux = linea;
			if (linea != null && !linea.equals("*")){
				System.out.println("He leido: " + linea);
				linea = null;
				do {
					sleep(3000);
					linea = leeRespuesta.readLine(); 
					if (linea != null && !linea.equals("*")){
						System.out.println("He leido: " + linea);
						lineaaux = linea;
						linea = null;
					}
					else {
						escribirCliente.write("Tiempo de espera agotado, se va a cerrar la conexión");
						escribirCliente.newLine();
						escribirCliente.flush();
						salir = true;
					}
				}while(!lineaaux.equals("*") || salir == false);
			}
			else {
				escribirCliente.write("Tiempo de espera agotado, se va a cerrar la conexión");
				escribirCliente.newLine();
				escribirCliente.flush();		
			}
			escribirCliente.close();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InterruptedException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}
	}
}
