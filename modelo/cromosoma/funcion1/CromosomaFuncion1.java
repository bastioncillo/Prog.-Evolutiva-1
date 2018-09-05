package modelo.cromosoma.funcion1;

import modelo.cromosoma.Cromosoma;
import modelo.gen.Gen;


public class CromosomaFuncion1 extends Cromosoma{
	
	//Constructora para la funci�n clone()
	public CromosomaFuncion1(Gen x) {
		numGenes = 1;
		genes = new Gen[numGenes];
		genes[0] = x;
	}
	
	//Constructora
	public CromosomaFuncion1(double t) {
		numGenes = 1;
		genes = new Gen[numGenes];
		genes[0] = new Gen(-250,250,t);
	}
	
	//Inicializamos el cromosoma inicializando sus genes 
	public void inicializaCromosoma() {
		genes[0].inicializa();
	}

	//El cromosoma se evalua optimizando su fenotipo
	public double calcularAptitud() {
		double x = genes[0].fenotipo();
		return f(x);
	}
	
	//F�rmula para la optimizaci�n de la funci�n
	private double f(double x) {
		return -( Math.abs( x * Math.sin( Math.sqrt( Math.abs( x ) ) ) ) );
	}

	//Para mostrar en la ventana la posici�n y el valor del m�nimo
	public String toString() {
		double x = genes[0].fenotipo();
		return "x= " + x + "    " + "f(x) = " + f(x);
	}

	@Override
	public Cromosoma clone() {
		return new CromosomaFuncion1(this.genes[0].clone());
	}
}
