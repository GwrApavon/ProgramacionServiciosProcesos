/**
 * 	Caja donde se va a ir guardando el dinero de forma sincronizada
 */
package EjercicioHilos;

/**
 * @author alu
 *
 */
public class CajaRegistradora {

	private int dineroTotal;
	private int numClientes;
	
	public CajaRegistradora(int clientes) {
		
		dineroTotal = 0;
		numClientes = clientes;
	}

	public synchronized int getNumClientes() {
		return numClientes;
	}

	public int getDineroTotal() {
		return dineroTotal;
	}

	public synchronized void setDineroTotal(int dineroTotal) {
		this.numClientes--;
		this.dineroTotal += dineroTotal;
	}

	@Override
	//Devuelve el dinero que hay en la caja una vez terminan los pedidos
	public String toString() {
		return "CajaRegistradora [dineroTotal=" + getDineroTotal() + "]";
	}
	
	
	
}
