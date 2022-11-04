package compartirinfo.tres;

public class SacarDinero extends Thread {
	private Cuenta c;

	public SacarDinero(String n, Cuenta c) {
		super(n);
		this.c = c;
	}

	public void run() {
		for (int x = 1; x <= 4; x++) {
			c.RetirarDinero(10, getName());
		}
		
		try {
			sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(getName() == "Juan" ) {
			System.out.println("Juan ha sacado dinero " + c.getGastoJuan() + " veces");
		}
		if(getName() == "Ana") {
			System.out.println("Juan ha sacado dinero " + c.getGastoAna() + " veces");
		}
	}// run
}
