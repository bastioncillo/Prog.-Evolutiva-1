package modelo.cromosoma.funcion5;

import algoritmoGenetico.FactoriaFunciones;
import modelo.cromosoma.Cromosoma;

public class Funcion5Factoria implements FactoriaFunciones{
	private double tolerancia;
	
	public Funcion5Factoria(double t) {
		tolerancia = t;
	}
	
	@Override
	public Cromosoma creaCromosoma() {
		return new CromosomaFuncion5(tolerancia);
	}
}
