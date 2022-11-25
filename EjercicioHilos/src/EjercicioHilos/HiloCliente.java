/**
 *  Hilo que se va a encargar de realizar pedidos que se irán almacenando en la Pila de pedidos.
 */
package EjercicioHilos;

import java.util.Random;

/**
 * @author alu
 *
 */
public class HiloCliente extends Thread {

	private PilaPedidos p;
	private String pedido;
	
	public HiloCliente(String pedido, PilaPedidos pila) {
		this.pedido = pedido;
		this.p = pila;
	}
	
	public void run() {
		Random rnd = new Random();
		try {
			synchronized (p) {
				while (p.estaLlena()) { //COMPRUEBA SI LA PILA ESTÁ LLENA 
					System.err.println("No puedo producir mas, esperare, soy " + this.getName());
					p.wait(); //ESPERA SI LO ESTÁ
				}; 
				System.out.println("añado " + pedido + ", soy: " + this.getName()
									+ ". Pedidos pendientes: " + p.getLast());
				p.aniade(pedido); //AÑADE EL PEDIDO SI NO LO ESTÁ Y LO NOTIFICA
				p.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
