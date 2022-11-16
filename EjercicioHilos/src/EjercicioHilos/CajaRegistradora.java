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
	
	public CajaRegistradora() {
		
		dineroTotal = 0;
		
	}

	public int getDineroTotal() {
		return dineroTotal;
	}

	public synchronized void setDineroTotal(int dineroTotal) {
		this.dineroTotal += dineroTotal;
	}

	@Override
	//Devuelve el dinero que hay en la caja una vez terminan los pedidos
	public String toString() {
		return "CajaRegistradora [dineroTotal=" + dineroTotal + "]";
	}
	
	
	
}
