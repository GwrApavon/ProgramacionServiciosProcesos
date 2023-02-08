package ServidorClienteHilos;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ServidorStream {

	public static void main(String[] args) {
		int puerto = 6000;
		Scanner sc = new Scanner (System.in);
		try {
			ServerSocket miServidor = new ServerSocket(puerto);
			System.out.println("Cuantos clientes quieres aceptar?");
			int cl = sc.nextInt();
			sc.nextLine();
			do {
				
				System.out.println("SERVIDOR: Escuchando por el puerto " + puerto);
				miServidor.setSoTimeout(0);
				Socket cliente = miServidor.accept();
				System.out.println("se ha conectado, esperando entrada Cliente");
				ServerAccept sa = new ServerAccept(cliente);
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
