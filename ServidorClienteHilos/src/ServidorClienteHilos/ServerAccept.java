package ServidorClienteHilos;

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
			Random rnd = new Random();
			int id = rnd.nextInt(0,101); // Lo pongo hasta 101 para poner algo que pueda controlar
			
			//Preparo el sitio donde escribir� en el socket
			OutputStream escrituraSocket = cliente.getOutputStream();
			OutputStreamWriter escritor = new OutputStreamWriter(escrituraSocket);
			BufferedWriter escribirCliente = new BufferedWriter(escritor);
					
			//Preparo el sitio para leer
			InputStream lecturaSocket = cliente.getInputStream();
			InputStreamReader lector = new InputStreamReader(lecturaSocket);
			BufferedReader leeRespuesta = new BufferedReader(lector);
			
			//Envío la pregunta al cliente
			//int operacion = rnd.nextInt(0,4); esto sería para  que funcionase correctamente el switch
			int operacion = 3;
			switch(operacion) {
			
				case 0: escribirCliente.write("Tu id es" + id +" sumalo por 7 y devuelve");
						break;
				case 1: escribirCliente.write("Tu id es" + id +" restalo por 7 y devuelve");
						break;
				case 2: escribirCliente.write("Tu id es" + id +" multiplicalo por 7 y devuelve");
						break;
				case 3: escribirCliente.write("Tu id es" + id +" dividelo por 7 y devuelve");
						break;
			}
			escribirCliente.newLine();
			escribirCliente.flush();
			
			String linea = leeRespuesta.readLine();
			System.out.println("He leido =>" + linea);
			
			int respuesta = Integer.parseInt(linea.split(" ")[4]);
			
			if(comprobarNumero(respuesta, operacion, id)) {
				escribirCliente.write("Tu id es" + id +" sumalo por 7 y devuelve");
				escribirCliente.newLine();
				escribirCliente.flush();
			}
			leeRespuesta.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean comprobarNumero(int res, int o, int id) {
		if(o == 0 && (res == (id+7))) {
			return true;
		}
		else if(o == 1 && (res == (id-7))) {
			return true;
		}
		else if(o == 2 && (res == (id*7))) {
			return true;
		}
		else if(o == 3 && (res == (id/7))) {
			return true;
		}
		return false;
	}
}
