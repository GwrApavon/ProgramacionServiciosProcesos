package Ejercicio_10;

public class Main {

	public static void main(String[] args) {
		
		Encapsulado en = new Encapsulado();
		
		Hilo1 h1 = new Hilo1(en);
		Hilo2 h2 = new Hilo2(en);
		
		try {
			h1.start();
			h2.start();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
