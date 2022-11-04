package Ejercicio_10;

public class Encapsulado {

		private int valor = 10;
		
		public Encapsulado() {
			
		}
		
		public synchronized void setVal(int i) {
			valor = i;
		}
		
		public synchronized int getVal() {
			return valor;
		}

		
}

