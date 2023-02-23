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

	private SSLSocket miSocket;
	BufferedWriter escribirAlServidor = null;
	BufferedReader leeRespuesta = null;
	
	public ClienteThread(SSLSocket s) {
		miSocket = s;
	}
	
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		try {
			//Iniciar Buffers 
			escribirAlServidor = new BufferedWriter(new OutputStreamWriter(miSocket.getOutputStream()));
			leeRespuesta = new BufferedReader(new InputStreamReader(miSocket.getInputStream()));
			
			//Espero respuesta
			//Se terminaría la conversación con un "*"
			String linea;
			do {	
				linea = leeRespuesta.readLine(); 
				if(linea.equals("*")) {
					System.out.println("Se ha cerrado la conexion");
				}
				else {
					System.out.println(linea);
				}
			}while(!linea.equals("*"));
				leeRespuesta.close();
				escribirAlServidor.close();
				miSocket.close();
				sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMsg(String msg) {
		try {
			escribirAlServidor.write(msg);
			escribirAlServidor.newLine();
			escribirAlServidor.flush();
		} catch (IOException iee) {
			iee.printStackTrace();
		}
	}
	
	public SSLSocket getSocket() {
		return this.miSocket;
	}
}
