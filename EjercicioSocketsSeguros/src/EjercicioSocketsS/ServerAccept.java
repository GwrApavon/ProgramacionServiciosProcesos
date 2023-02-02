package EjercicioSocketsS;


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

public class ServerAccept extends Thread{

	private Socket cliente;
	
	public ServerAccept(Socket c) {
		this.cliente = c;
	}
	
	public void run() {
		try {			
			
			// DO WHILE CADENAFIN
			// INPUTSTREAM
			//MODIFICAR CLIENTE PARA QUE MANDE Y RECIBA DATOS
			
			//Preparo el sitio donde escribir� en el socket
			OutputStream escrituraSocket = cliente.getOutputStream();
			OutputStreamWriter escritor = new OutputStreamWriter(escrituraSocket);
			BufferedWriter escribirCliente = new BufferedWriter(escritor);
							
			escribirCliente.write("Bienvenido cliente"); //Responde con un ok si el resultado es correcto
			escribirCliente.newLine();
			escribirCliente.flush();
			
			escribirCliente.close();
			cliente.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
