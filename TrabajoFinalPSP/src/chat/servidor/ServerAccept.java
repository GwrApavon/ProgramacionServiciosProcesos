package chat.servidor;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.net.ssl.SSLSocket;

/**
 * Hilo para las funciones del servidor
 * @author angel
 *
 */
public class ServerAccept extends Thread{

	private SSLSocket cliente;
	BufferedWriter escribirCliente;
	BufferedReader leeRespuesta;
	int id;
	
	//Constructor
	public ServerAccept(SSLSocket c, int id) {
		this.cliente = c;
		this.id = id;
	}
	
	/**
	 * El servidor está permanentemente recibiendo mensajes de los clientes.
	 * En el momento que le llega un mensaje de un cliente, le pasa este al resto de hilos presentes
	 * y estos los envían a sus respectivos clientes.
	 * También se mandará al propio cliente que lo ha mandado (me parece lo más lógico poder ver tus propios mensajes)
	 */
	public void run() {
		try {
			
			boolean salir = false;
			
			//Preparo el sitio donde escribirá en el socket 
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
					//Envío de mensajes a los clientes
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
	
	/**
	 * Método para cerrar la conexión del cliente
	 */
	public void sendMsgCerrar() {
		try {
			escribirCliente.write("*");
			escribirCliente.newLine();
			escribirCliente.flush();
		} catch (IOException iee) {
			iee.printStackTrace();
		}	
	}
	
	/**
	 * Método para enviar el mensaje al cliente
	 * @param msg
	 */
	public void sendMsg(String msg) {
		try {
			escribirCliente.write(msg);
			escribirCliente.newLine();
			escribirCliente.flush();
		} catch (IOException iee) {
			iee.printStackTrace();
		}	
	}
	
	/**
	 * Método para cerrar las conexiones antes de apagar el servidor
	 */
	public void cerrarHilo() {
		try {
			cliente.close();
			escribirCliente.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Getter del socket
	 * @return
	 */
	public SSLSocket getSocket() {
		return this.cliente;
	}
}
