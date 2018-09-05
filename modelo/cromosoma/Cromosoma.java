package modelo.cromosoma;

import modelo.gen.Gen;

public abstract class Cromosoma {
	protected Gen [] genes; //array de genes
	protected double aptitud; //función de evaluación fitness adaptación
	protected double puntuacion; //puntuacion relativa(aptitud/suma)
	protected double punt_acum; //puntuacion acumulada para selección
	protected int numGenes; //numero de genes del cromosoma
	/***************** Modificado para revisión ***********************/
	protected double apt_desp; //aptitud desplazada
	/******************************************************************/
	
	// Se evalua la funcion a optimizar con los fenotipos de cada variable
	public abstract double calcularAptitud();
	
	// Debe inicializar cada variable(GenVector), llamando al inicializa de cada una de ellas
	public abstract void inicializaCromosoma();
	
	public abstract String toString();
	
	// Devuelve un cromosoma identico al original (una copia)
	public abstract Cromosoma clone();
	
	//Cálculo de la puntuación del cromosoma y de el valor acumulado de los anteriores a él
	public void puntua(double suma, double acumulada) {
					/**M p R**/
		puntuacion = aptitud/suma;
				   //aptitud
				   /********/
		punt_acum = acumulada + puntuacion;
	}
	
	/***************Getters y Setters************/
	public void setAptitud(double a) {
		this.aptitud = a;
	}

	public double getPunt_acum() {
		return this.punt_acum;
	}

	public double getAptitud() {
		return this.aptitud;
	}

	public void setPuntuacion(double p) {
		this.puntuacion = p;		
	}

	public double getPuntuacion() {
		return this.puntuacion;
	}

	public void setPunt_acum(double pa) {
		this.punt_acum = pa;
	}

	public int getNumGenes() {
		return this.numGenes;
	}

	public Gen getGenes(int i) {
		return this.genes[i];
	}
	
	/****************** Modificado para revisión ***********************/
	public double getAptDesp(){
		return this.apt_desp;
	}
	
	public void setAptDesp(double a){
		this.apt_desp = a;
	}
	/******************************************************************/
}
