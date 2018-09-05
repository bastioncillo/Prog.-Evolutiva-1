package modelo.reproduccion;

import java.util.Random;

import algoritmoGenetico.FactoriaFunciones;
import modelo.cromosoma.Cromosoma;
import modelo.cruce.Cruce;

public class ReproduccionEstandar implements Reproduccion{
	//Proceso de reproducción de la población
	public void reproduccion(int individuos, double prob_cruce, 
			FactoriaFunciones factFunciones, Cruce metodoCruce, Cromosoma[] poblacion) {
		int[] progenitores = new int[individuos];
		int numProgenitores = 0;
		
		Random r = new Random();
		
		//Pasamos por todos los indiviudos de la población y
		//si su probabilidad de cruce es mayor que un valor aleatorio
		//los seleccionamos para cruzarlos
		for (int i = 0; i < individuos; i++) {
			double trial = r.nextDouble();
			if (trial < prob_cruce) {
				progenitores[numProgenitores] = i;
				numProgenitores++;
			}
		}
		
		//Si el número de progenitores es par lo reducimos en uno
		if (numProgenitores % 2 == 1) numProgenitores--;
		
		//Vamos cruzando los progenitores de dos en dos para generar la descendencia
		for (int i = 0; i < numProgenitores; i += 2) {
			Cromosoma hijo1 = factFunciones.creaCromosoma();
			Cromosoma hijo2 = factFunciones.creaCromosoma();
			metodoCruce.cruce(poblacion[progenitores[i]], poblacion[progenitores[i+1]], hijo1, hijo2);
			poblacion[i] = hijo1;
			poblacion[i+1] = hijo2;
		}		
	}
}
