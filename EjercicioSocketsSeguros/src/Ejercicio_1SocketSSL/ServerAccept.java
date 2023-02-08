package Ejercicio_1SocketSSL;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
							
			escribirCliente.write("Bienvenido cliente"); //Responde con un ok si el resultado es correcto
			escribirCliente.newLine();
			escribirCliente.flush();
			sleep(3000);
			escribirCliente.close();
			cliente.close();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InterruptedException ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}
	}
}
