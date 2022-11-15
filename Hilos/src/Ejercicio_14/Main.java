/**
 * 
 */
package Ejercicio_14;

import java.util.*;

/**
 * @author alu
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Variables
		int tempMax = 0;
		final int NUMDIAS = 3650;
		// Objetos
		Random r = new Random();
		Scanner s = new Scanner(System.in);
		
		//Arrays de temperaturas
		int [] temps = new int [NUMDIAS];
		int [] aux = null;
		// ArrayList de hilos 
		ArrayList <Hilo> hilos = new ArrayList<Hilo>();
		
		//Rellenar el array de temperaturas
		for(int i = 0; i < NUMDIAS; i++) {
			temps[i] = r.nextInt(-30, 51);
		}
		
		//Pedir cantidad de hilos
		System.out.println("En cuantos hilos quieres dividir el Array: ");
		int cant = s.nextInt();
		
		//Crear subarray de temperaturas y asignarsela a cada hilo
		int n = 0;
		for(int idx = 0; idx < NUMDIAS; idx++) {
			if (n == 0) {
				aux = new int [NUMDIAS/cant];
			}
			if(n == NUMDIAS/cant) {
				Hilo hs = new Hilo(aux);	
				hilos.add(hs);
				aux = new int [NUMDIAS/cant];
				n = 0;
			}
			aux[n] += temps[idx];
			if(idx == NUMDIAS - 1) {
				Hilo hs = new Hilo(aux);	
				hilos.add(hs);
			}		
			n++;
		}
		//Iniciar y esperar hilos
		for (Hilo hilo : hilos) {
			hilo.start();
			
		}
		for (Hilo h : hilos) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Recoger los datos de los hilos y mostrarlos por pantalla
		for (Hilo hilo : hilos) {
			if(tempMax < hilo.getTempMax())
				tempMax = hilo.getTempMax();
		}
		
		//Sale siempre 50 porque hay muchos datos, si el número de días se reduce empiezan a salir otros números
		System.out.println("La temperatura máxima es: " + tempMax);
	}

}
