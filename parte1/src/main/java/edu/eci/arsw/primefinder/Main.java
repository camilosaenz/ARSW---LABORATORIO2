package edu.eci.arsw.primefinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	
	private static BufferedReader buffer;

	public static void main(String[] args) throws InterruptedException, IOException {
		
		//Se reparte el trabajo del programa ahora en 3 Thread
		PrimeFinderThread uno=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread dos=new PrimeFinderThread(10000001, 20000000);
		PrimeFinderThread tres=new PrimeFinderThread(20000001, 30000000);
		
		// Algo importante es que los Thread deben iniciar con el .start() ya que este permite parar el Thread, mientras que el .run() no lo permite ya que este termina su ejecución.
		uno.start();
		dos.start();
		tres.start();
		int tiempoPausa = 5000; // Se inicia una variable que contiene el tiempo en el que se pausara el Thread en milisegundos.
		
		// Llama al metodo "suspend" y realiza el proceso paraa pausar el Thread en la cantidad de tiempo indicada
		suspend(uno, dos, tres, tiempoPausa);
	}
	
	public static void suspend(PrimeFinderThread uno, PrimeFinderThread dos, PrimeFinderThread tres, int tiempoPausa) throws IOException {
		
		buffer = crearBuffer(); // Buffer para detectar acciones en la consola.
		char enter = ('\n'); // variable enter o salto de linea para reanudar el Thread (Es importante detectar que se debe escribir un espacion o cualquier caracter en la consola seguido de la tecla Enter para que el Thread reanude).
		
		TimerTask tiempoInicio = new TimerTask() { //Variable tiempoInicio para poder iniciar el tiempo en la ejecucion del Thread
			
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				try {
					
					uno.pausarThread(uno); // Suspencion del Thread uno.
					dos.pausarThread(dos); // Suspencion del Thread dos.
					tres.pausarThread(tres); // Suspencion del Thread tres.
					
						while (buffer.read() != enter) {
							buffer.read	();
						}
						
					uno.iniciarThread(uno); // Reanudacion del Thread uno.
					dos.iniciarThread(dos); // Reanudacion del Thread dos.
					tres.iniciarThread(tres); // Reanudacion del Thread tres.
					
				} catch (IOException exeption) {
					// TODO Auto-generated catch block
					exeption.printStackTrace();
				}
			}
		};
		
		Timer tiempoEjecucion = new Timer();
		tiempoEjecucion.schedule(tiempoInicio, tiempoPausa);
	}
	
	public static BufferedReader crearBuffer() {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		return buffer;
	}
}
