package algoritmoGenetico;

import modelo.cromosoma.Cromosoma;
import modelo.cruce.Cruce;
import modelo.mutacion.Mutacion;
import modelo.reproduccion.Reproduccion;
import modelo.seleccion.Seleccion;

public class AlgoritmoGenetico {
	
	protected Cromosoma[] poblacion;
	protected Cromosoma mejor;
	private FactoriaFunciones factFunciones;
	
	// Parametros
	protected int individuos;
	private int generaciones;
	private double prob_cruce;
	private double prob_mutacion;
	private Seleccion metodoSeleccion;
	private Mutacion metodoMutacion;
	private Cruce metodoCruce;
	private Reproduccion metodoReproduccion;
	private boolean elitismo;
	private int tamElite;
	private String funcion;
	
	/************ Modificado para revisi�n ***************/
	private Cromosoma[] elite;
	private boolean maximizar;
	/*****************************************************/
	
	// Estadisticas
	private double[] mejorGeneracion;
	private double[] mejorGlobal;
	private double[] mediaAptitud;
	
	//Constructor
	public AlgoritmoGenetico (int t, int g, double pc, double pm, Seleccion s, boolean e, FactoriaFunciones f,String fun, Mutacion m, Cruce c, Reproduccion r) {
		individuos = t;
		generaciones = g;
		prob_cruce = pc;
		prob_mutacion = pm;
		metodoSeleccion = s;
		metodoMutacion = m;
		metodoCruce = c;
		metodoReproduccion = r;
		factFunciones = f;
		funcion = fun;
	
		poblacion = new Cromosoma[individuos];
		
		elitismo = e;
		Double d = (individuos * 0.2) + 1;
		tamElite = d.intValue(); 
		
		/************ Modificado para revisi�n ***************/
		if(elitismo){
			elite = new Cromosoma[tamElite];
		}
		
		if(funcion.equals("F3")){
			maximizar = true;
		}else{
			maximizar = false;
		}
		/*****************************************************/
		
		mejorGeneracion = new double[generaciones];
		mejorGlobal = new double[generaciones];
		mediaAptitud = new double[generaciones];
	}
	
	//Ejecucui�n del algoritmo
	public Cromosoma ejecuta() {
		inicializaPoblacion();
		evaluarPoblacion(0);	// Evaluamos la poblaci�n puntuandoa sus individuos
		
		int t=1;
		
		while (!terminacion(t)) {
			seleccion();	   // Modifica la poblacion (la sobreescribe)
			reproduccion();	   // Modifica a los progenitores			
			mutacion();		   // Modifica la poblacion a nivel de genes
			
			if(elitismo){
				reintegrar(); //Si ha habido elitismo, reintegramos la �lite en la poblaci�n
			}
			
			evaluarPoblacion(t); //Reevaluamos
			t++;
		}
		//Devolvemos el mejor Cromosoma
		return mejor;
	}
	
	//Proceso de mutaci�n de la poblaci�n
	private void mutacion() {
		metodoMutacion.mutacion(individuos, poblacion, prob_mutacion);
	}
	
	//Proceso de reproducci�n de la poblaci�n
	private void reproduccion() {
		metodoReproduccion.reproduccion(individuos, prob_cruce, factFunciones, metodoCruce, poblacion);
	}
	
	//Seleccionamos los indiv�duos de esta generaci�n que
	//participar�n en el proceso de cruce y de mutaci�n
	private void seleccion() {
		if(elitismo){
			seleccionarElite();
		}
		metodoSeleccion.selecciona(poblacion, prob_cruce);
	}
	
	
	/****************************** Modificado para revisi�n **********************/
	//Selecci�n de los indiv�duos de la poblaci�n que formar�n parte de la �lite
	public void seleccionarElite() {
		ordenaMayorAMenor();
		for(int i = 0; i < tamElite; i++){
			this.elite[i] = poblacion[i];
        }
	}
	
	//Ordenar la poblaci�n de mayor a menor aptitud
	private void ordenaMayorAMenor(){
		
		revisar_adaptacion_minimiza();
		
		Cromosoma aux;
		for(int i = 0; i < individuos; i++){
			for(int j = 0; j < i; j++){
				if(poblacion[i].getAptDesp() > poblacion[j].getAptDesp()){
					aux = poblacion[j];
					poblacion[j] = poblacion[i];
					poblacion[i] = aux;
				}
			}
		}
	}
	
	//Reintegrar la �lite en la poblaci�n
	private void reintegrar(){
		int cont = 0;
		//Sustitu�mos los peores individuos de la poblaci�n por la �lite 
		for(int i = individuos-1; i > individuos - tamElite; i--){
			poblacion[i] = elite[cont];
			cont++;
		}
	}
	/*******************************************************************************/
	
	//Comprobar si el algoritmo ha terminado
	private boolean terminacion(int t) {
		return (t >= generaciones);
	}
	
	//Evaluar poblaci�n
	private void evaluarPoblacion(int t) {
		double puntAcumulada = 0;
		double sumaAptitudes = 0;
		
		double mejorAptitud = 0;
		int posicionMejor = 0;
		
		
		
		for (int i = 0; i < individuos; i++) {
			double a = poblacion[i].getAptitud();
			sumaAptitudes += a;
			if(maximizar){
				if (a > mejorAptitud) {	
					posicionMejor = i;
					mejorAptitud = a;
				}
			}else{
				if (a < mejorAptitud) {	
					posicionMejor = i;
					mejorAptitud = a;
				}
			}
		}
		
		for (int i = 0; i < individuos; i++) {
			poblacion[i].puntua(sumaAptitudes,puntAcumulada);
			puntAcumulada += poblacion[i].getPuntuacion();
		}
		
		if (mejor == null) {
			mejor = poblacion[posicionMejor];
		}else{
			if(maximizar){
				if (mejorAptitud > mejor.getAptitud()) {	
					
					mejor = poblacion[posicionMejor];
				}
			}else{
				if (mejorAptitud < mejor.getAptitud()) {	
					
					mejor = poblacion[posicionMejor];
				}
			}
		}
		
		mejorGeneracion[t] = poblacion[posicionMejor].getAptitud();
							
		mejorGlobal[t] = mejor.getAptitud();

		mediaAptitud[t] = sumaAptitudes / individuos;
	}

	//Inicializar la poblaci�n
	private void inicializaPoblacion() {
		for (int i = 0; i < individuos; i++) {
			poblacion[i] = factFunciones.creaCromosoma();
			poblacion[i].inicializaCromosoma();
			poblacion[i].setAptitud(poblacion[i].calcularAptitud());
		}
	}
	
	//Desplazar los valores para que no sean negativos
	private void revisar_adaptacion_minimiza() {
		/****************** Modificado para revisi�n **********************/
		if(maximizar){
			double fmin = Double.POSITIVE_INFINITY;
			
			
			for(int  i = 0; i < individuos; i++){
				if(poblacion[i].getAptitud() < fmin)
					fmin = poblacion[i].getAptitud();
			}
			
			//fmin es el valor absoluto de la peor(menor) aptitud de la poblaci�n
			fmin = Math.abs(poblacion[individuos-1].getAptitud());
			
			for(int i = 0; i < individuos; i++){
				poblacion[i].setAptDesp(poblacion[i].getAptitud() + fmin);
			}
		/*****************************************************************/
		}else{
			double cmax = Double.NEGATIVE_INFINITY;
			
			for (int i = 0; i < individuos; i++) {
				if (poblacion[i].getAptitud() > cmax)
					cmax = poblacion[i].getAptitud();
			}
			
			cmax *= 1.05;
			
			for (int i = 0; i < individuos; i++){
				poblacion[i].setAptDesp(cmax - poblacion[i].getAptitud());
			}
		}
	}
	
	/*************** Getters y Setters *****************/
	public double[] getMejorPorGeneracion() {
		return this.mejorGeneracion;
	}
	
	public double[] getMejorGlobal() {
		return this.mejorGlobal;
	}
	
	public double[] getMediaAptitud() {
		return this.mediaAptitud;
	}
}
