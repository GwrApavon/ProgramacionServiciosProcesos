package chat.cliente;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import javax.net.ssl.SSLSocket;

public class ClienteThread extends Thread{

	SSLSocket miSocket;
	public ClienteThread(SSLSocket s) {
		miSocket = s;
	}
	
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		// preparo el sitio donde escribir� en el socket
		OutputStream escrituraSocket;
		try {
			escrituraSocket = miSocket.getOutputStream();
			OutputStreamWriter escritor = new OutputStreamWriter(escrituraSocket);
			BufferedWriter escribirAlServidor = new BufferedWriter(escritor);
			// preparo el sitio para leer
			InputStream lecturaSocket = miSocket.getInputStream();
			InputStreamReader lector = new InputStreamReader(lecturaSocket);
			BufferedReader leeRespuesta = new BufferedReader(lector);
			//Leo la respuesta obtenida del servidor	
			String linea = leeRespuesta.readLine(); 
			System.out.println("He leido: " + linea);
			//Empiezo con el dialogo infinito con el servidor (sin esperar respuesta)
			//Se terminaría la conversación con un "*"
			String msg;
			do {
				msg = sc.nextLine();
				escribirAlServidor.write(msg);
				escribirAlServidor.newLine();
				escribirAlServidor.flush();
			}while(!msg.equals("*"));
		
				leeRespuesta.close();
				escribirAlServidor.close();
				miSocket.close();
				sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
