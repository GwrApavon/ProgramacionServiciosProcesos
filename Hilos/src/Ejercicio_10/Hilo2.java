package Ejercicio_10;

public class Hilo2 extends Thread{

	Encapsulado miValor;
	public Hilo2(Encapsulado variableEncapsulada) {
		
		miValor = variableEncapsulada;
	}
	
	public void run() {
		for(int i = 0; i < 10; i++) {
			
			int tmp = miValor.getVal();
			System.out.println("Soy el hilo " + getName() + ", mi valor: " + tmp);
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
