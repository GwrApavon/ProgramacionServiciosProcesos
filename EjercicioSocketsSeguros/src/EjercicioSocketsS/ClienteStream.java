package EjercicioSocketsS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
					
			// preparo el sitio para leer
					InputStream lecturaSocket = miSocket.getInputStream();
					InputStreamReader lector = new InputStreamReader(lecturaSocket);
					BufferedReader leeRespuesta = new BufferedReader(lector);
						
			//Dependiendo de lo que reciba hará una operación distinta
						
					String linea = leeRespuesta.readLine(); 
					System.out.println("He leido: " + linea);
					
					leeRespuesta.close();	
					miSocket.close();
						
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


}
