package modelo.reproduccion;

import algoritmoGenetico.FactoriaFunciones;
import modelo.cromosoma.Cromosoma;
import modelo.cruce.Cruce;

public interface Reproduccion {
	public void reproduccion(int individuos, double prob_cruce, FactoriaFunciones factFunciones, Cruce metodoCruce, Cromosoma[] poblacion);
}
