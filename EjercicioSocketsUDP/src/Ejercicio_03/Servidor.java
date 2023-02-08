package Ejercicio_03;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class Servidor {

	public static void main(String[] args) {
	
		int puertoEscucha = 16005;
		int tamagnoBufferPkt = 1024;
		boolean salir = false;
		DatagramSocket socket= null;
		try {
			
		//	InetAddress origen = Inet4Address.getByName("192.168.1.130");
			InetAddress origen = Inet4Address.getLocalHost();
			socket = new DatagramSocket(puertoEscucha, origen);
			
				
				System.out.println("SERVIDOR:");
				System.out.println("\tEscucho por: " + puertoEscucha + " y la ip " + origen.getHostAddress());
	
				// recibir un paquete
				
				byte[] bufferTemporal = new byte[tamagnoBufferPkt];
			
			do {
				DatagramPacket paqueteRecibido = new DatagramPacket(bufferTemporal, bufferTemporal.length);
				System.out.println("Esperando datagrama.....");
				
				socket.setSoTimeout(10000);
				socket.receive(paqueteRecibido);
				System.out.println("<---RECIBIDO:" + paqueteToString(paqueteRecibido));		
				
			}while(!salir);
		}catch(SocketTimeoutException iioe) {
			System.out.println("El paquete se ha perdido");
			iioe.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
					
		}finally {
			if (socket != null) {
				socket.close();
			}
		}
	}
	
	public static String paqueteToString (DatagramPacket paquete) {
		 return ("|" + new String(paquete.getData(), 0, paquete.getLength()) + "|" + paquete.getLength() + "|" + 
				 paquete.getAddress().getHostAddress() + "|" + paquete.getPort() + "|");
	}
}
