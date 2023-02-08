package Ejercicio_3SocketSSL;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ClienteStream {
	
	public static void main(String[] args) {
		String host = "192.168.2.61";
		int puerto = 6000;
		String ficheroProperties = "parametros.properties";
		Properties properties = new Properties();
		
		try {
			properties.load(new BufferedReader(new FileReader(ficheroProperties)));
			puerto = Integer.parseInt(properties.getProperty("puerto"));
		}catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		System.setProperty("javax.net.ssl.trustStore", properties.getProperty("usuarioAlmacen"));
		System.setProperty("javax.net.ssl.trustStorePassword",properties.getProperty("usuarioPassAlmacen"));
		// creamos el socket
		
		try {
			SSLSocketFactory factoria = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket miSocket = (SSLSocket) factoria.createSocket(host,puerto);
			ClienteThread ct = new ClienteThread(miSocket);
			ct.start();	
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


}
