/**
 *  El "negocio" que va a simular mi ejercicio va a ser un contador de letras donde por cada letra el precio
 *  aumenta en 1€
 */
package EjercicioHilos;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author alu
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int numAyudantes, numClientes;
		
		//ARRAYLIST DE CLIENTES
		ArrayList <HiloCliente> hc = new ArrayList <HiloCliente>();
		
		
		//ARRAYLIST DE AYUDANTES
		ArrayList <HiloAyudante> ha = new ArrayList <HiloAyudante>();
		
		//Scanner
		Scanner s = new Scanner(System.in);
		
		//Random
		Random rnd = new Random();
		do {
			System.out.println("Cuantos ayudantes quieres en tu negocio?");
			numAyudantes = s.nextInt();
		}while(numAyudantes<= 0);
		numClientes = rnd.nextInt(numAyudantes,numAyudantes + 20);
		
		PilaPedidos p = new PilaPedidos(numAyudantes);
		CajaRegistradora c = new CajaRegistradora(numClientes);

		for(int i = 0; i< numClientes; i++) {
			
			HiloCliente cliente = new HiloCliente (GenerarPalabra(), p);
			cliente.setName("cliente " +  (i + 1));
			hc.add(cliente);
			
		}

		for(int i = 0; i< numAyudantes; i++) {
			
			HiloAyudante ayudante = new HiloAyudante (p,c);
			ayudante.setName("ayudante " +  (i + 1));
			ha.add(ayudante);
			
		}

		for(HiloCliente hilo : hc) {
			hilo.start();
		}
		
		for(HiloAyudante hilo : ha) {			
			hilo.start();
			hilo.setPriority(1);
		}
		for(HiloCliente hilo : hc) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(HiloAyudante hilo : ha) {
			try {
				hilo.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(c.toString());
	}
	
	public static String GenerarPalabra(){
		//Metodo que genera una "palabra" o string
			Random rnd = new Random();
			String palabra = ""; 
			int num = rnd.nextInt(1,11);
	
			for (int i=0; i<num; i++){
			      int codigoAscii = rnd.nextInt(97,123);
			       palabra += (char)codigoAscii;
			}
			
			return palabra;
	}

}
