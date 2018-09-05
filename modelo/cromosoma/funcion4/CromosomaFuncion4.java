package modelo.cromosoma.funcion4;

import modelo.cromosoma.Cromosoma;
import modelo.gen.Gen;


public class CromosomaFuncion4 extends Cromosoma{
	
	//Constructora
	public CromosomaFuncion4(int n, double tol) {
		numGenes = n;
		genes = new Gen[numGenes];
		for (int i = 0; i < numGenes; i++)
			genes[i] = new Gen(0,Math.PI,tol);
	}
	
	//Constructora para la función clone()
	public CromosomaFuncion4(Gen[] vbles) {
		numGenes = vbles.length;
		genes = new Gen[numGenes];
		for (int i = 0; i < numGenes; i++) 
			genes[i] = vbles[i];
	}

	//Inicializamos el cromosoma inicializando sus genes 
	public void inicializaCromosoma() {
		for (int i = 0; i < numGenes; i++) 
			genes[i].inicializa();
	}

	//El cromosoma se evalua optimizando su fenotipo
	public double calcularAptitud() {
		double[] vbles = new double[numGenes];
		for (int i = 0; i < numGenes;i++)
			vbles[i] = genes[i].fenotipo();
		return f(vbles);
	}
	
	//Fórmula para la optimización de la función
	private double f(double[] vbles) {
		double suma = 0;
		for (int i = 0; i < numGenes;i++) {
			suma += Math.sin(vbles[i])*Math.pow(Math.sin( ((i+1)*Math.pow(vbles[i], 2)) / Math.PI ), 20);
		}
		return -(suma);
	}

	//Para mostrar en la ventana la posición y el valor del mínimo
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < numGenes; i++) {
			s.append("x" + i + " = " + genes[i].fenotipo());
		}
		s.append(" f(xi | 1.." + numGenes + ") = " + calcularAptitud());
		return new String(s);
	}

	@Override
	public Cromosoma clone() {
		Gen[] vbles = new Gen[numGenes];
		for (int i = 0; i < numGenes; i++)
			vbles[i] = genes[i].clone();
		return new CromosomaFuncion4(vbles);
	}
}
