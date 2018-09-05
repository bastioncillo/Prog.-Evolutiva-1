package modelo.mutacion;

import java.util.Random;

import modelo.cromosoma.Cromosoma;
import modelo.gen.Gen;

public class MutacionPtoAPto implements Mutacion{
	
	/*Mutación a nivel de la población*/
	public void mutacion(int individuos, Cromosoma[] poblacion, double prob_mutacion){
		boolean mutado;
		
		//Por cada individuo de la población...
		for (int i = 0; i < individuos; i++) {
			//...vemos si mutamos su cromosoma
			mutado = mutacionCromosoma(poblacion[i], prob_mutacion);
			if (mutado)		/** M p R **/
				poblacion[i].setAptitud(poblacion[i].calcularAptitud());
							//setAptitud()
							/**********/
		}
		
	}
	
	/*Mutación a nivel del cromosma*/
	private boolean mutacionCromosoma(Cromosoma indv, double prob_mut){
		boolean mutado = false;
		
		//Para cada gen del cromsoma...
		for (int i = 0; i < indv.getNumGenes(); i++) {
			//...vemos si mutamos sus alelos
			if (mutacionGen(indv.getGenes(i), prob_mut))
				mutado = true;
		}
		
		return mutado;
		
	}

	/*Mutación a nivel del gen*/
	private boolean mutacionGen(Gen gen, double prob_mut){
		
	boolean mutado = false;
		
		Random r = new Random();
		double prob;
		
		//Para cada alelo del gen...
		for (int i = 0; i < gen.getNumAlelos(); i++) {
			prob = r.nextDouble();
			//...si la probabilidad de mutación es mayor 
			//que un valor aleatorio mutamos ese alelo 
			//(invertimos su valor booleano)
			if (prob < prob_mut) {
				gen.invertirAlelo(i);
				mutado = true;
			}
		}
		return mutado;
		
	}
}
