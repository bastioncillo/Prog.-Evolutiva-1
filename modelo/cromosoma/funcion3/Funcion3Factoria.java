package modelo.cromosoma.funcion3;

import algoritmoGenetico.FactoriaFunciones;
import modelo.cromosoma.Cromosoma;

public class Funcion3Factoria implements FactoriaFunciones{
	private double tolerancia;
	
	public Funcion3Factoria(double t) {
		tolerancia = t;
	}
	
	@Override
	public Cromosoma creaCromosoma() {
		return new CromosomaFuncion3(tolerancia);
	}
}
