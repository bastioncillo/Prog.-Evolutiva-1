package modelo.cromosoma.funcion2;

import algoritmoGenetico.FactoriaFunciones;
import modelo.cromosoma.Cromosoma;

public class Funcion2Factoria implements FactoriaFunciones{

private double tolerancia;
	
	public Funcion2Factoria(double t) {
		tolerancia = t;
	}
	
	@Override
	public Cromosoma creaCromosoma() {
		return new CromosomaFuncion2(tolerancia);
	}
}
