package chat.cliente;

import java.awt.EventQueue;
import java.awt.Window;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import interfaz.VentanaCliente;
import util.CapturePanel;
import util.Consumer;
import util.ProxyPrintStream;
import util.StreamCapturer;
/**
 * Cliente de un chat
 * @author angel
 *
 */
public class ClientStream {
	
	private static final String PROPERTIES_FILE = "src/config/data.properties";
	
	//Interfaz
	public static void cargaPanel(Consumer textAreaCliente, ClienteThread ct) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCliente window = new VentanaCliente();
					window.setCt(ct);
					window.agnadePanel(textAreaCliente);
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	//Main
	public static void main(String[] args) {
		String host = "192.168.2.61";
		int puerto = 6000;
		Properties properties = new Properties();
		Consumer txtAreaCliente = new CapturePanel();
		
		try {
			//Properties
			properties.load(new BufferedReader(new FileReader(PROPERTIES_FILE)));
			puerto = Integer.parseInt(properties.getProperty("puerto"));
			host = properties.getProperty("servidorHost");
			System.setProperty("javax.net.ssl.trustStore", properties.getProperty("usuarioAlmacen"));
			System.setProperty("javax.net.ssl.trustStorePassword",properties.getProperty("usuarioPassAlmacen"));

			//Creamos el socket seguro del cliente
			SSLSocketFactory factoria = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket miSocket = (SSLSocket) factoria.createSocket(host,puerto);
			ClienteThread ct = new ClienteThread(miSocket);
			
			//preparo el panel de salida de mensajes---------
			System.setOut(new PrintStream(new StreamCapturer("STDOUT", txtAreaCliente, System.out)));
			cargaPanel(txtAreaCliente, ct);
			
			//guardaré la salida de error en un fichero de log
			System.setErr(new ProxyPrintStream(System.err, "stderr.log"));
			//------------------------------------------------
			
		ct.start();	
		}catch(FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}

	}
}
