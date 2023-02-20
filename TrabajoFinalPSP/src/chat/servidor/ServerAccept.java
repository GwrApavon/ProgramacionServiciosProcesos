package chat.servidor;


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
	BufferedWriter escribirCliente;
	BufferedReader leeRespuesta;
	
	public ServerAccept(SSLSocket c) {
		this.cliente = c;
	}
	
	public void run() {
		try {
			
			boolean salir = false;
			//Preparo el sitio donde escribir� en el socket 
			escribirCliente = new BufferedWriter(new OutputStreamWriter(cliente.getOutputStream()));
			//Preparo el sitio para leer
			leeRespuesta = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			 
			
			escribirCliente.write("Bienvenido cliente, ya puedes hablar infinitamente conmigo");
			escribirCliente.newLine();
			escribirCliente.flush();
			
			sleep(3000);
			String linea = leeRespuesta.readLine(); 
			do {
				System.out.println("Usuario" + 1 + ": " + linea);
				if(linea.equals("*")) {
					salir = true;
					escribirCliente.write("*");
					escribirCliente.newLine();
					escribirCliente.flush();
				}
				else{
					linea = leeRespuesta.readLine(); 
					String msg = "Usuario"+ 1 + ": " + linea;
					System.out.println(msg);
					ServerStream.enviarMensajes(msg);
				}
			}while(salir == false);

			cliente.close();
			escribirCliente.close();
			
		}catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	public void sendMsg() {
		try {
			escribirCliente.write("*");
			escribirCliente.newLine();
			escribirCliente.flush();
		} catch (IOException iee) {
			iee.printStackTrace();
		}	
	}
	public void sendMsg(String msg) {
		try {
			escribirCliente.write(msg);
			escribirCliente.newLine();
			escribirCliente.flush();
		} catch (IOException iee) {
			iee.printStackTrace();
		}	
	}
	
	public void cerrarHilo() {
		try {
			cliente.close();
			escribirCliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
