/**
 *  Pila de pedidos, los pedidos realizados por los clientes(HiloCliente) se guardarán aquí con un maximo de
 *  pedidos igual al número de ayudantes(HiloAyudante) creados.
 */
package EjercicioHilos;

/**
 * @author alu
 *
 */
public class PilaPedidos {

	String [] listaPedidos;
	private int size;
	private int last=0;
	
	//CONSTRUCTOR DE LA PILA (EL  TAMAÑO DEPENDE DEL NUMERO DE AYUDANTES)
	public PilaPedidos(int size){
		this.size = size;
		listaPedidos = new String[size];
		last = 0;
	}
	
	//AÑADE UN PEDIDO A LA PILA
	//SE HACE DE MANERA SINCRONIZADA PARA QUE NO PUEDAN ACCEDER AL MISMO TIEMPO LOS AYUDANTES
	public synchronized boolean agnade (String pedido) {
		if (!estaLlena()) {
			listaPedidos[last] = pedido;
			last++;
			return true;
		}
		return false;
	}

	//RETIRA UN ENTERO DE LA PILA, 
	//SE HACE DE MANERA SINCRONIZADA PARA QUE NO PUEDAN ACCEDER AL MISMO TIEMPO LOS AYUDANTES
	public synchronized String sacar () {
		if (!estaVacia()) {
			last--;
			return listaPedidos[last];
		}
		return "";
	}
	
	//VACÍA LA PILA
	public void vaciar() {
		last = 0;
	}


	 //COMPRUEBA SI LA PILA ESTÁ VACÍA
	public boolean estaVacia() {
		if (last < 1) return true;
		return false;
	}

	//COMPRUEBA SI LA PILA ESTÁ LLENA
	public boolean estaLlena() {
		if (last == size) {
			return true;
		}
		return false;
	}
}
