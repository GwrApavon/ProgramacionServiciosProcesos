package ServidorClienteHilos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteStream {
	
	public static void main(String[] args) {
		String host = "192.168.56.1";
		int puerto = 6000;
		
		// creamos el socket
		try {
			Socket misocket = new Socket(host, puerto);
		
		// preparo el sitio donde escribir� en el socket
			OutputStream escrituraSocket = misocket.getOutputStream();
			OutputStreamWriter escritor = new OutputStreamWriter(escrituraSocket);
			BufferedWriter escribirAlServidor = new BufferedWriter(escritor);
		
		// preparo el sitio para leer
			InputStream lecturaSocket = misocket.getInputStream();
			InputStreamReader lector = new InputStreamReader(lecturaSocket);
			BufferedReader leeRespuesta = new BufferedReader(lector);
			
		//Dependiendo de lo que reciba hará una operación distinta
			
			String linea = leeRespuesta.readLine(); 
			System.out.println("He leido: " + linea);
			// Dado que la respuesta del servidor siempre llevará el mismo 
			//"Tu id es 'num' multiplicalo por 7 y devuelve" 
			//con split recogeré el número y la operación
			int id = Integer.parseInt(linea.split(" ")[3]);
			String operacion = linea.split(" ")[4];
			String respuesta = "Respuesta de prueba"; // Pongo eso por si lo siguiente no se ejecuta que se muestre algo
			//No se va a ejecutar ninguna que no sea la multiplicación porque en el ejercicio lo dice así 
			//pero igualmente añado las demás opciones
			if(operacion.equals("sumalo")) {
				respuesta = "El resultado es " + (id + 7);
			}
			else if(operacion.equals("restalo")) {
				respuesta = "El resultado es " + (id - 7);
			}
			else if(operacion.equals("multiplicalo")) {
				respuesta = "El resultado es " + (id * 7);
			}
			else if(operacion.equals("dividelo")) {
				respuesta = "El resultado es " + (id / 7);
			}
		// envío algo al servidor
			escribirAlServidor.write(respuesta);
			escribirAlServidor.newLine();
			escribirAlServidor.flush();
			
		//Recibo la confirmación
			linea = leeRespuesta.readLine(); 
			System.out.println("He recibido: " + linea);
			
		// cerrar todos los streams y sockets
			leeRespuesta.close();
			escribirAlServidor.close();
			misocket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


}
