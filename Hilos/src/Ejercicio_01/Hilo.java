package Ejercicio_01;

public class Hilo extends Thread{

		public Hilo() {
			
		}
		
		public void run() {
			System.out.println("Este es mi nombre : " + getName());
			System.out.println("Pero puedo cambiarlo usando setName(nombre)");
			
			System.out.println("Este es mi número de identificación:" + getId());
			System.out.println("Y no puedo ser cambiado");
			
			System.out.println("Esta es mi prioridad : " + getPriority());
			System.out.println("Pero puedo cambiarlo usando setPriority(número)");
			
			
			setName("Hilo");
			System.out.println("\nAhora mi nombre es: " + getName());
			
			setPriority(4);
			System.out.println("Ahora mi prioridad es: " + getPriority());
		}
}
