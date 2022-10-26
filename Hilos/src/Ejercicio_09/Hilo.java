package Ejercicio_09;

import java.io.*;

public class Hilo extends Thread{
	
	private String s;
	int contadorLineas;
	
	public Hilo(String s) {
		this.s = s;
		this.contadorLineas = 0;
	}
	

	public void run() {
		try {
			
			File f = new File(s);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
		
			String linea = br.readLine();
			while(linea != null) {
				contadorLineas++;
				linea = br.readLine();
			}
			br.close();
			if(contadorLineas == 1) {
				System.out.println("El fichero " + s + " tiene " + contadorLineas + " linea");
			}
			else {
				System.out.println("El fichero " + s + " tiene " + contadorLineas + " lineas");
			}
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
