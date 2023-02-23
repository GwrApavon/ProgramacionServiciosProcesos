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
	int id;
	
	public ServerAccept(SSLSocket c, int id) {
		this.cliente = c;
		this.id = id;
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
			
			do {
				String linea = leeRespuesta.readLine(); 
				if(linea.equals("*")) salir = true;
				else{ 
					String msg = "Usuario "+ id + ": " + linea;
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
	
	public void sendMsgCerrar() {
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
	
	public SSLSocket getSocket() {
		return this.cliente;
	}
}
