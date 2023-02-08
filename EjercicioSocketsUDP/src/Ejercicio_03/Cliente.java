package Ejercicio_03;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws InterruptedException {
		
		Scanner sc = new Scanner (System.in);
		int puertoServidorDestino = 16005;
		int tamagnoBufferPkt = 1024;
	
		DatagramSocket socket = null;
		String mensajeStr;
		try {
			
			// PREPARO EL DESTINO
			InetAddress destino = Inet4Address.getByName("192.168.2.61");
			//InetAddress destino = Inet4Address.getLocalHost();
			
			do {
					
				byte[] mensaje = new byte[tamagnoBufferPkt];
				
				System.out.println("Escribe lo que quieras enviar:");
				mensajeStr = sc.nextLine();
			
				mensaje = mensajeStr.getBytes();
				
				// construyo el datagrama que quiero enviar
				// creamos el Datagrama diciendo...
				// el mensaje en bytes // la longitud del mensaje // la m�quina de destino // el puerto de destino
				DatagramPacket paqueteEnv = new DatagramPacket(mensaje, mensaje.length, destino, puertoServidorDestino);
				
				socket = new DatagramSocket();
				System.out.println("CLIENTE:");
				System.out.println("\tEnvio por: " + socket.getLocalPort() + " y la ip " + Inet4Address.getLocalHost().getHostAddress() );
				System.out.println("ENVIANDO -->" + paqueteToString(paqueteEnv));
	
				socket.send(paqueteEnv);
				
				// SI QUIERO ESCUCHAR UNA RESPUESTA APROVECHO EL SOCKET QUE HE ABIERTO YO MISMA
				byte[] bufferTemporal = new byte[tamagnoBufferPkt];
				DatagramPacket paqueteRecibido = new DatagramPacket(bufferTemporal, bufferTemporal.length);
				System.out.println("Esperando datagrama.....");
			}while(!mensajeStr.equalsIgnoreCase("*"));
		
		}catch(SocketTimeoutException iioe) {
				System.out.println("El paquete se ha perdido");
				iioe.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada salida");
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
