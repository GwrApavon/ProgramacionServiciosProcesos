/**
 * 
 */
package Ejercicio_08;

/**
 * @author alu
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for(int i = 1; i<7; i++) {
			Hilo hs = new Hilo(i);
			hs.start();
		}

	}

}
