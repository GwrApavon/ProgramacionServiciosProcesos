/**
 * 
 */
package pru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author alu
 *
 */
public class VerPagina {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL midireccion, midireccion2;
		try {
			midireccion = new URL("http://ajedrezaragon.es/index.html");
			midireccion2 = new URL("http", "ajedrezaragon.es", 80, "/noticias/noticias.html");
			
			System.out.println("Host: " + midireccion.getHost());
			System.out.println("Protocolo: " + midireccion.getProtocol());
			System.out.println("Fichero: " + midireccion.getFile());
			System.out.println("Puerto: " + midireccion.getPort());
			System.out.println("Path: " + midireccion.getPath());
			URLConnection conn = midireccion.openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
//			String linea;
//			while((linea = br.readLine()) != null) {
//				System.out.println(linea);
//			}
		} catch (MalformedURLException e) {
			
			System.err.println("La URL está mal");
			e.printStackTrace();
		} catch (IOException e) {

			System.err.println("Ta mal");
			e.printStackTrace();
		}
		
		
	}

}
