package modelo.cromosoma.funcion1;

import algoritmoGenetico.FactoriaFunciones;
import modelo.cromosoma.Cromosoma;

public class Funcion1Factoria implements FactoriaFunciones{
private double tolerancia;
	
	public Funcion1Factoria(double t) {
		tolerancia = t;
	}
	
	public Cromosoma creaCromosoma() {
		return new CromosomaFuncion1(tolerancia);
	}

}
