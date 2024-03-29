package Ejercicio_09;

import java.io.*;
import java.util.ArrayList;

public class Hilo extends Thread{
	

	private ArrayList <String> ficheros = new ArrayList<String>();
	private int totalLineas;
	
	public Hilo(ArrayList <String> ficheros) {
		
		this.ficheros = ficheros;
		this.totalLineas = 0;
	}
	

	public int getTotalLineas() {
		return totalLineas;
	}

	public void run() {
		try {
			
			for (String s : ficheros) {
			
				int contadorLineas = 1;
				File f = new File(s);
				FileReader fr = new FileReader(f);
				BufferedReader br = new BufferedReader(fr);
			
				String linea = br.readLine();
				while(br.readLine() != null) {
					contadorLineas++;
					totalLineas++;
				}
				br.close();
				if(contadorLineas == 1) {
					System.out.println("(Hilo: " + getName() +") " + "El fichero " + s + " tiene " + contadorLineas + " linea");
				}
				else {
					System.out.println("(Hilo: " + getName() +") " + "El fichero " + s + " tiene " + contadorLineas + " lineas");
				}
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
