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
		
		while(true) {
			try {
				synchronized (p) {
					this.dinero = 0;
					while (p.estaVacia() && cr.getNumClientes()>0) { //COMPRUEBA SI LA PILA ESTÁ VACÍA 
						System.err.println("Pila vacía, voy a esperar, soy: " + this.getName() 
											+ ". Pedidos pendientes: " + p.getLast());
						p.wait();//ESPERA SI LO ESTÁ
					}
					
					if(cr.getNumClientes()==0) {
						p.notifyAll();
						return;
					}
					String pedido = p.sacar();
					for (int i = 0; i < pedido.length(); i++) {
						if(pedido.charAt(i)>='a' && pedido.charAt(i)<='z' || 
			        	   pedido.charAt(i)>='A' && pedido.charAt(i)<='Z'){
			        	   dinero++;
				        }
					}
					cr.setDineroTotal(dinero);
					System.out.println("Hilo " + getName() + " ha terminado con el pedido: " + "\"" + pedido +"\""
										+ ". El precio del pedido ha sido:" + dinero
										+ ". Pedidos pendientes: " + p.getLast());
					p.notifyAll(); //TOMA EL PEDIDO, CALCULA EL PRECIO, LO METE EN LA CAJA Y LO NOTIFICA		
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}
