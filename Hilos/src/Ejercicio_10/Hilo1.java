package Ejercicio_10;

public class Hilo1 extends Thread{

	Encapsulado miValor;
	public Hilo1(Encapsulado variableEncapsulada) {
		
		miValor = variableEncapsulada;
	}
	
	public void run() {
		for(int i = 0; i < 10; i++) {
			
			miValor.setVal(i);
			System.out.println("Soy el hilo " + getName() + " y pongo " + i + " en la variable");
			try {
				sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
