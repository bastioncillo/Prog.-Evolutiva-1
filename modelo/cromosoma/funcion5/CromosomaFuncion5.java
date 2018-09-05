package modelo.cromosoma.funcion5;

import modelo.cromosoma.Cromosoma;
import modelo.gen.Gen;

public class CromosomaFuncion5 extends Cromosoma{
	
	//Constructora para la función clone()
	public CromosomaFuncion5(Gen x1, Gen x2) {
		numGenes = 2;
		genes = new Gen[numGenes];
		genes[0] = x1;
		genes[1] = x2;
	}
	
	//Constructora
	public CromosomaFuncion5(double t) {
		numGenes = 2;
		genes = new Gen[numGenes];
		genes[0] = new Gen(-10, 10, t);		// x1
		genes[1] = new Gen(-10, 10, t);		// x2
	}
	
	//Inicializamos el cromosoma inicializando sus genes 
	public void inicializaCromosoma() {
		genes[0].inicializa();
		genes[1].inicializa();
	}

	//El cromosoma se evalua optimizando su fenotipo
	public double calcularAptitud() {
		double x1 = genes[0].fenotipo();
		double x2 = genes[1].fenotipo();
		return f(x1,x2);
	}

	//Fórmula para la optimización de la función
	private double f(double x1, double x2) {
		
		double suma1 = 0;
		for (int i = 1; i <= 5; i++)
			suma1 += i * Math.cos((i+1)*x1 + i);
		
		double suma2 = 0;
		for (int i = 1; i <= 5; i++)
			suma2 += i * Math.cos((i+1)*x2 + i);
		
		return suma1*suma2;
	}

	//Para mostrar en la ventana la posición y el valor del mínimo
	public String toString() {
		double x1 = genes[0].fenotipo();
		double x2 = genes[1].fenotipo();
		return "x1 = " + x1 + " , x2 = " + x2 + "      " + "f(xi, i = 1..2) = " + f(x1,x2);
	}

	@Override
	public Cromosoma clone() {
		return new CromosomaFuncion5(genes[0].clone(),genes[1].clone());
	}
}
