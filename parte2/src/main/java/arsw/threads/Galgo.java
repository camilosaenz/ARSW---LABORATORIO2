package arsw.threads;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	
	private ReentrantLock mutex;
	private int paso;
	private Carril carril;
	RegistroLlegada regl;

	public Galgo(Carril carril, String name, RegistroLlegada reg, ReentrantLock mutex) {
		super(name);
		this.carril = carril;
		this.mutex = mutex;
		paso = 0;
		this.regl=reg;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {			
			Thread.sleep(100);
			carril.setPasoOn(paso++);
			carril.displayPasos(paso);
			int ubicacion = 0;
			if (paso == carril.size()) {
				carril.finish();
				try{
					mutex.lock();
					ubicacion=regl.getUltimaPosicionAlcanzada();
					regl.setUltimaPosicionAlcanzada(ubicacion+1);
					System.out.println("El galgo "+this.getName()+" llego en la posicion "+ubicacion);
					}finally {
						mutex.unlock();
					}
				if (ubicacion==1){
					regl.setGanador(this.getName());
					}
			}
		}
	}


	@Override
	public void run() {
		
		try {
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
