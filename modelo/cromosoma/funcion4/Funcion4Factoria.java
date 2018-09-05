package modelo.cromosoma.funcion4;

import algoritmoGenetico.FactoriaFunciones;
import modelo.cromosoma.Cromosoma;

public class Funcion4Factoria implements FactoriaFunciones{
	private int n;
	private double tol;
	
	public Funcion4Factoria(int n, double t) {
		this.n = n;
		tol = t;
	}
	
	@Override
	public Cromosoma creaCromosoma() {
		return new CromosomaFuncion4(n,tol);
	}
}
