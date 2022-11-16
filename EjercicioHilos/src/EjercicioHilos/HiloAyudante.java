/**
 *  Hilo que se va a encargar de recibir el pedido, contar las letras, devolver el dinero obtenido por el 
 *  pedido y meterlo dentro de la caja.
 */
package EjercicioHilos;

import java.util.Random;

/**
 * @author alu
 *
 */
public class HiloAyudante extends Thread{

	private PilaPedidos p;
	private CajaRegistradora cr;
	private int dinero;
	
	public HiloAyudante(PilaPedidos p, CajaRegistradora c) {
		this.p = p;
		this.cr = c;
		this.dinero = 0;
	}
	
	public void run() {
		
		
		try {
			
			Random rnd = new Random();
			
			int tiempo = rnd.nextInt(2)*1000;
			System.err.println("soy: " + this.getName() + ", voy a dormir " + tiempo);
			sleep(tiempo); // DUERME EL TIEMPO QUE SE HAYA GENERADO EN EL RANDOM
			synchronized (p) {
				while (p.estaVacia()) { //COMPRUEBA SI LA PILA ESTÁ VACÍA 
					System.err.println("Pila vacía, voy a parar, soy: " + this.getName());
					p.wait();//ESPERA SI LO ESTÁ
				}
				String pedido = p.sacar();
				for (int i = 0; i < pedido.length(); i++) {
			           if(pedido.charAt(i)>='a' && pedido.charAt(i)<='z' || 
			        	  pedido.charAt(i)>='A' && pedido.charAt(i)<='Z') {
			        	   dinero++;
			           }
					}
				cr.setDineroTotal(dinero);
				System.out.println("Hilo " + getName() + " ha terminado con el pedido: " + "\"" + pedido +"\""
									+ "El precio del pedido ha sido:" + dinero);
				p.notify(); //TOMA EL PEDIDO, CALCULA EL PRECIO, LO METE EN LA CAJA Y LO NOTIFICA
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
