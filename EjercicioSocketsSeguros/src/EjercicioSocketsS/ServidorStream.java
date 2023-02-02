package EjercicioSocketsS;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Properties;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;


public class ServidorStream {

	public static void main(String[] args) {
		String ficheroProperties = "parametros.properties";
		Properties properties = new Properties();
		int puerto = 6000;
		String host = "localhost";
		try {
			properties.load(new BufferedReader(new FileReader(ficheroProperties)));
			puerto = Integer.parseInt(properties.getProperty("puerto"));
			host = properties.getProperty("servidorHost");
		}catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		System.setProperty("javax.net.ssl.keyStore", properties.getProperty("nombreAlmacenSSL"));
		System.setProperty("javax.net.ssl.keyStorePassword", properties.getProperty("passwordAlmacenSSL"));
	
		try {
			SSLServerSocketFactory factoria = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket miServidor = (SSLServerSocket) factoria.createServerSocket(puerto,0, InetAddress.getByName(host));
			
			int cl = Integer.parseInt(properties.getProperty("numClientes"));
			do {
				System.out.println("SERVIDOR: Escuchando por el puerto " + puerto);
				miServidor.setSoTimeout(3000);
				Socket cliente = miServidor.accept();
				System.out.println("se ha conectado, esperando entrada Cliente");
				ServerAccept sa = new ServerAccept(cliente);
				sa.start();
				cl--;
			}while(cl > 0);
			// cierro todos los recursos
			miServidor.close();
			
		}catch(SocketTimeoutException stoe) {
			stoe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
