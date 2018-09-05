package modelo.cruce;

import java.util.Random;

import modelo.cromosoma.Cromosoma;
import modelo.gen.Gen;

public class CruceEstandar implements Cruce{
	
	// Cruce de este cromosoma con otro para producir dos hijos
	public void cruce(Cromosoma padre1, Cromosoma padre2, Cromosoma hijo1, Cromosoma hijo2) {
		Random r = new Random();
		
		// Para cada variable
		for (int i = 0; i < padre1.getNumGenes(); i++) {
			// Buscar punto de cruce aletorio
			int puntoCruce = r.nextInt(padre1.getGenes(i).getNumAlelos());
			
			Gen vbleHijo1 = hijo1.getGenes(i);
			Gen vbleHijo2 = hijo2.getGenes(i);
			
			Gen vblePadre1 = padre1.getGenes(i);
			Gen vblePadre2 = padre2.getGenes(i);
			
			//Para los valores del 0 al punto de cruce,
			//guardamos en cada hijo esa parte del padre correspondiente
			for (int j = 0; j < puntoCruce; j++) {
				vbleHijo1.setAlelo(j, vblePadre1.getAlelo(j));
				vbleHijo2.setAlelo(j, vblePadre2.getAlelo(j));
			}
			
			//Ahora guardamos en cada hijo la parte desde el punto de cruce al final, 
			//del padre inverso al del primer bucle
			for (int j = puntoCruce; j < padre1.getGenes(i).getNumAlelos(); j++) {
				vbleHijo1.setAlelo(j, vblePadre2.getAlelo(j));
				vbleHijo2.setAlelo(j, vblePadre1.getAlelo(j));
			}
		}
		
		//una vez tenemos en los hijos el resultado de cruzar a los padres los evlauamos
			/** M p R **/
		hijo1.setAptitud(hijo1.calcularAptitud());
			//setApttiud()
		hijo2.setAptitud(hijo2.calcularAptitud());
			/**********/
	}
}
