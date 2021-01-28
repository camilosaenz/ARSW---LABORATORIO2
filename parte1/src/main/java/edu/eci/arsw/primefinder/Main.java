package edu.eci.arsw.primefinder;

public class Main {
	public static void main(String[] args) {
		
		PrimeFinderThread uno=new PrimeFinderThread(0, 10000000);
		PrimeFinderThread dos=new PrimeFinderThread(10000001, 20000000);
		PrimeFinderThread tres=new PrimeFinderThread(20000001, 30000000);
		
		uno.start();
		dos.start();
		tres.start();
		
	}
	
}
