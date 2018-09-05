package modelo.cromosoma.funcion3;

import modelo.cromosoma.Cromosoma;
import modelo.gen.Gen;

public class CromosomaFuncion3 extends Cromosoma{
	
	//Constructora para la función clone()
	public CromosomaFuncion3(Gen x, Gen y) {
		numGenes = 2;
		genes = new Gen[numGenes];
		genes[0] = x;
		genes[1] = y;
	}
	
	//Constructora 
	public CromosomaFuncion3(double t) {
		numGenes = 2;
		genes = new Gen[numGenes];
		genes[0] = new Gen(-3, 12.1, t);		// x
		genes[1] = new Gen(4.1, 5.8, t);		// y
	}
	
	//Inicializamos el cromosoma inicializando sus genes 
	public void inicializaCromosoma() {
		genes[0].inicializa();
		genes[1].inicializa();
	}

	//El cromosoma se evalua optimizando su fenotipo
	public double calcularAptitud() {
		double x = genes[0].fenotipo();
		double y = genes[1].fenotipo();
		return f(x,y);
	}
	
	//Fórmula para la optimización de la función
	private double f(double x, double y) {
		return 21.5 + (x*Math.sin(4*Math.PI*x)) + (y*Math.sin(20*Math.PI*y));
	}

	//Para mostrar en la ventana la posición y el valor del mínimo
	public String toString() {
		double x = genes[0].fenotipo();
		double y = genes[1].fenotipo();
		return "x = " + x + " , y = " + y + "      " + "f(x,y) = " + f(x,y);
	}

	@Override
	public Cromosoma clone() {
		return new CromosomaFuncion3(genes[0].clone(),genes[1].clone());
	}
}
