/**
 * 
 */
package Ejercicio_08;

/**
 * @author alu
 *
 */
public class Hilo extends Thread{
	
	private int i;
	
	public Hilo(int i) {
		this.i = i;
	}
	
	public void run() {
		System.out.println("Hola mundo, soy: " + getName() + ". He iniciado");
		try {
			Thread.sleep(i*1000);
			
			System.out.println(getName() + " He Terminado");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
