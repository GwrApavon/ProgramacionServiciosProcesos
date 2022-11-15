package Ejercicio_14;

public class Hilo extends Thread {

	private int tempMax;
	private int [] temps;
	
	public Hilo(int [] ltemp) {
		super();
		this.temps = ltemp;
	}
	public void run () {
		
		for(int x : temps) {
			if(x > tempMax) {
				tempMax = x;
			}
		}
	}
	
	public int getTempMax() {
		return tempMax;
	}
}
