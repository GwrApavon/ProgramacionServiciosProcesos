package chat.servidor;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Properties;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import interfaz.VentanaServidor;
import util.CapturePanel;
import util.Consumer;
import util.ProxyPrintStream;
import util.StreamCapturer;


public class ServerStream {

	private static final String PROPERTIES_FILE = "src/config/data.properties";
	public static final ArrayList <ServerAccept> clientes = new ArrayList <ServerAccept>();
		/**
		 * Muestra en pantalla un panel con un área de texto que mostrará la salida indicada (out o err)
		 * @param textAreaServidor
		 */
		public static void cargaPanel(Consumer textAreaServidor) {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						VentanaServidor window = new VentanaServidor();
						window.agnadePanel(textAreaServidor);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	
		}
	
	public static void main(String[] args) {
		
		Properties properties = new Properties();
		int puerto = 6000;
		String host = "localhost";
		Consumer textAreaServidor = new CapturePanel(); // area de texto que meteremos en una ventana

		try {
			
		    properties.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
		    puerto = Integer.valueOf(properties.getProperty("puerto"));
		    host = properties.getProperty("servidorHost");

		    // preparo el panel de salida de mensajes---------
			System.setOut(new PrintStream(new StreamCapturer("STDOUT", textAreaServidor, System.out)));
			
			
			// guardaré la salida de error en un fichero de log
			System.setErr(new ProxyPrintStream(System.err, "stderr.log"));
			//------------------------------------------------
		    
			System.setProperty("javax.net.ssl.keyStore", properties.getProperty("nombreAlmacenSSL"));
			System.setProperty("javax.net.ssl.keyStorePassword", properties.getProperty("passwordAlmacenSSL"));
			SSLServerSocketFactory factoria = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			SSLServerSocket miServidor = (SSLServerSocket) factoria.createServerSocket(puerto,0, InetAddress.getByName(host));
			System.out.println("SERVIDOR: Escuchando por el puerto " + puerto);
			miServidor.setSoTimeout(0);
			//ArrayList<MiHilo> listaHilos = new ArrayList<MiHilo>();
			cargaPanel(textAreaServidor);
			for (int i = 1; i <= Integer.parseInt(properties.getProperty("numClientes")); i++) {
				
				SSLSocket cliente = (SSLSocket) miServidor.accept();
				System.out.println("Conectado cliente " + i);
				ServerAccept sa = new ServerAccept(cliente);
				clientes.add(sa);
				sa.start();
			}

			// espero a que terminen los hilos para cerrar???
			miServidor.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
	}
	public static void enviarMensajes(String msg) {
		for (ServerAccept sA : clientes) {
			sA.sendMsg(msg);
		}
	}
	public static void cerrarServer() {
		for (ServerAccept sA : clientes) {
			sA.sendMsg();
			sA.cerrarHilo();
		}
	}
	
}
