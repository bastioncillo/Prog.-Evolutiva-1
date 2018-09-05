package modelo.cromosoma.funcion2;

import modelo.cromosoma.Cromosoma;
import modelo.gen.Gen;

public class CromosomaFuncion2 extends Cromosoma{
	
	//Constructora para la función clone()
	public CromosomaFuncion2(Gen x, Gen y) {
		numGenes = 2;
		genes = new Gen[numGenes];
		genes[0] = x;
		genes[1] = y;
	}
	
	//Constructora
	public CromosomaFuncion2(double t) {
		numGenes = 2;
		genes = new Gen[numGenes];
		genes[0] = new Gen(-512, 512, t);		// x
		genes[1] = new Gen(-512, 512, t);		// y
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
		return -(y +47)*Math.sin(Math.sqrt(Math.abs(y+(x/2)+47))) 
				- x*Math.sin(Math.sqrt(Math.abs(x-(y+47))));
	}

	//Para mostrar en la ventana la posición y el valor del mínimo
	public String toString() {
		double x = genes[0].fenotipo();
		double y = genes[1].fenotipo();
		return "x = " + x + " , y = " + y + "      " + "f(x,y) = " + f(x,y);
	}

	@Override
	public Cromosoma clone() {
		return new CromosomaFuncion2(genes[0].clone(),genes[1].clone());
	}
}
