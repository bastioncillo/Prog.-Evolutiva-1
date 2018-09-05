package modelo.mutacion;

import modelo.cromosoma.Cromosoma;

public interface Mutacion {
	public void mutacion(int i, Cromosoma[] pob, double prob_mut);
}
