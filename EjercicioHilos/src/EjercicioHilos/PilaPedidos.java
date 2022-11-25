/**
 *  Pila de pedidos, los pedidos realizados por los clientes(HiloCliente) se guardarán aquí con un maximo de
 *  pedidos igual al doble del número de ayudantes(HiloAyudante) creados.
 */
package EjercicioHilos;

/**
 * @author alu
 *
 */
public class PilaPedidos {

	String [] listaPedidos;
	private int size = 5;
	private int last=0;
	
	//CONSTRUCTOR DE LA PILA (EL  TAMAÑO DEPENDE DEL NUMERO DE AYUDANTES)
	public PilaPedidos(int size){
		this.size = size;
		listaPedidos = new String[size];
		last = 0;
	}
	
	//AÑADE UN PEDIDO A LA PILA
	//SE HACE DE MANERA SINCRONIZADA PARA QUE NO PUEDAN ACCEDER AL MISMO TIEMPO LOS AYUDANTES
	public boolean aniade (String pedido) {
		if (!estaLlena()) {
			listaPedidos[getLast()] = pedido;
			last = getLast() + 1;
			return true;
		}
		return false;
	}

	//RETIRA UN ENTERO DE LA PILA, 
	//SE HACE DE MANERA SINCRONIZADA PARA QUE NO PUEDAN ACCEDER AL MISMO TIEMPO LOS AYUDANTES
	public String sacar () {
		if (!estaVacia()) {
			last = getLast() - 1;
			return listaPedidos[getLast()];
		}
		return "";
	}
	
	//VACÍA LA PILA
	public void vaciar() {
		last = 0;
	}


	 //COMPRUEBA SI LA PILA ESTÁ VACÍA
	public boolean estaVacia() {
		if (getLast() < 1) return true;
		return false;
	}

	//COMPRUEBA SI LA PILA ESTÁ LLENA
	public boolean estaLlena() {
		if (getLast() == size) {
			return true;
		}
		return false;
	}

	public int getLast() {
		return last;
	}
}
