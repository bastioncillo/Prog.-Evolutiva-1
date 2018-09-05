package modelo.gen;

import java.util.Random;

import modelo.utiles.Utiles;

public class Gen {
	public boolean[] alelo;
	private int numAlelos;
	
	private double xmin;
	private double xmax;
	
	/*Constructora para la función de clonar*/
	public Gen(int n) {
		numAlelos = n;
		alelo = new boolean[numAlelos];
	}
	
	/*Constructora para un nuevo gen*/
	public Gen(double min, double max, double tol) {
		xmax = max;
		xmin = min;
		numAlelos = (int) Math.round(Math.log10( 1 + (xmax - xmin)/tol) / Math.log10(2));
		alelo = new boolean[numAlelos];
	}
	
	public void inicializa() {
		Random r = new Random();
		for(int i = 0; i < numAlelos; i++) 
			alelo[i] = r.nextBoolean();
	}
	
	/*Cálculo del fenotipo*/
	public double fenotipo() {
		return  (xmin + (xmax-xmin) * Utiles.bin2dec(alelo, numAlelos) / (Math.pow(2,numAlelos) - 1));
	}
	
	public int getNumAlelos() {
		return numAlelos;
	}
	
	public void setAlelo(int i, boolean value) {
		alelo[i] = value;
	}
	
	public boolean getAlelo(int i) {
		return alelo[i];
	}
	
	public void invertirAlelo(int i){
		alelo[i] = !alelo[i];
	}
	
	/*Clonar: para evitar que dos punteros hagan referencia al mismo alelo*/
	public Gen clone() {
		Gen clon = new Gen(this.numAlelos);
		
		for (int i = 0; i < this.numAlelos; i++)
			clon.alelo[i] = this.alelo[i];
		
		clon.xmin = this.xmin;
		clon.xmax = this.xmax;
		
		return clon;
	}
}
