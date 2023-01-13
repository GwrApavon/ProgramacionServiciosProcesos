package sockets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ServidorStream {

	public static void main(String[] args) {
		int puerto = 6000;
		
		try {
			ServerSocket miServidor = new ServerSocket(puerto);
			int cl = 3;
			do {	
				System.out.println("SERVIDOR: Escuchando por el puerto " + puerto);
				miServidor.setSoTimeout(0);
				Socket cliente = miServidor.accept();
				System.out.println("se ha conectado, esperando entrada Cliente");
				ServidorAccept sa = new ServidorAccept(miServidor, cliente);
				sa.start();
				cl--;
			}while(cl > 0);
			// cierro todos los recursos
			miServidor.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
