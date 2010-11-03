
	package tareas;

	import java.util.ArrayList;
	import java.util.List;

import usuarioMiembroYFecha.Fecha;
import usuarioMiembroYFecha.Miembro;




import estados.Estado;
	/**
	 * La clase tareaCompuesta se va a encargar de englobar las tareas
	 * que posean sub tareas y deban ser reflejadas en la modalidad del
	 * trabajo. 
	 *
	 */

	public class TareaCompuesta extends Tarea {

		private List <AdministradorTarea> tareasQueLaComponenen; 
		
		public TareaCompuesta(String nombre, String descrip, Fecha fechaEstimadaFinalizacion) {
			super(nombre, descrip, fechaEstimadaFinalizacion);
			this.tareasQueLaComponenen= new ArrayList <AdministradorTarea> ();		
		}

		/**
		 * Este mensaje se encarga de cerrar todas las sub tareas de
		 * la tarea compuesta.
		 */
		public void cerrate() {
			
			for ( AdministradorTarea at : this.getTareasQueLaComponenen())
			{ at.cerrate();}
			
		}

		 
		/**
		 * Este mensaje se encarga de reabrir todas las sub tareas de
		 * la tarea compuesta.
		 */
		public void reAbrite(String a) {
			
			for ( AdministradorTarea at : this.getTareasQueLaComponenen())
			{ at.reAbrite(a);}
			
		}

		/**
		 * Este mensaje retorna si la tarea compuesta en su totalidad debe ser realizada despues
		 * de otra tarea. Como si no esta dentro de un orgnaizadorDeTarea quiere decir que no
		 * tiene ordenm entonces este metodo retorna false.
		 */
		public boolean tieneOrden() {
			
			return false;
		}
	    
		/** Este mensaje retorna si el estado de la tarea compuesta es Finalizada. 
		 * Consideramos que el estado de una tarea compuesta es finalizada cuando
		 * todas sus subtareas estan finalizadas.
		 */
		public boolean verificarSiEstaFinalizada() {
			
			boolean estaFinalizada=true;
			for ( AdministradorTarea at : this.getTareasQueLaComponenen())
			{ estaFinalizada= estaFinalizada && at.verificarSiEstaFinalizada();}
			return estaFinalizada;
		}

		/** Este mensaje retorna si el estado de la tarea compuesta es cerrada. 
		 * Consideramos que el estado de una tarea compuesta es cerrada cuando
		 * todas sus subtareas estan cerradas.
		 */
		public boolean verificarSiEstaCerrada() {
			
			boolean estaCerrada=true;
			for ( AdministradorTarea at : this.getTareasQueLaComponenen())
			{ estaCerrada= estaCerrada && at.verificarSiEstaCerrada();}
			return estaCerrada;
		}

		/** Este mensaje retorna si el estado de la tarea compuesta es creada. 
		 * Consideramos que el estado de una tarea compuesta es creada cuando
		 * todas sus subtareas estan creadas.
		 */
		public boolean verificarSiEstaCreada() {
			
			boolean estaCreada=true;
			for ( AdministradorTarea at : this.getTareasQueLaComponenen())
			{ estaCreada= estaCreada && at.verificarSiEstaCreada();}
			return estaCreada;
		}

		/** Este mensaje retorna si el estado de la tarea compuesta es enTrabajo. 
		 * Consideramos que el estado de una tarea compuesta es enTrabajo cuando
		 * todas sus subtareas que no esten iniciadas, creadas o finalizadas 
		 * esten enTrabajo.
		 */
		public boolean verificarSiEstaEnTrabajo() {
			
			boolean estaEnTrabajo=true;
			for ( AdministradorTarea at : this.getTareasQueLaComponenen())
			{ estaEnTrabajo= estaEnTrabajo && (at.verificarSiEstaEnTrabajo() | at.verificarSiEstaFinalizada()
					                           | at.verificarSiEstaCreada() | at.verificarSiEstaIniciada());  }
			return estaEnTrabajo;
		}


		/** Este mensaje retorna si el estado de la tarea compuesta es iniciada. 
		 * Consideramos que el estado de una tarea compuesta es iniciada cuando
		 * todas sus subtareas que no esten creadas estan iniciadas.
		 */
		
		public boolean verificarSiEstaIniciada() {
			
			boolean estaIniciada=true;
			for ( AdministradorTarea at : this.getTareasQueLaComponenen())
			{ estaIniciada= estaIniciada && (at.verificarSiEstaIniciada() | at.verificarSiEstaCreada());  }
			return estaIniciada;
		}

		/** Este mensaje retorna si el estado de la tarea compuesta es pausada. 
		 * Consideramos que el estado de una tarea compuesta es pausada cuando
		 * alguna de todas sus subtareas este pausada.
		 */
		public boolean verificarSiEstaPausada() {
			
			boolean estaPausada=false;
			for ( AdministradorTarea at : this.getTareasQueLaComponenen())
			{ estaPausada= estaPausada  | at.verificarSiEstaPausada();  }
			return estaPausada;
		}
		
		/**
		 * Este mensaje retorna el estado de la tarea compuesta basandose en los estados 
		 * de sus subtareas.
		 */
		
		public String verEstado() {
			
			return null;
		}

		/**
		 * Este mensaje agrega a la tarea pasada por parametro a
		 * la tarea compuesta.
		 * 
		 */
		protected void agregarTarea(AdministradorTarea tarea)
		{
			List<AdministradorTarea> tareasYaAgregadas=this.getTareasQueLaComponenen();
			tareasYaAgregadas.add(tarea);
			this.setTareasQueLaComponenen(tareasYaAgregadas);
			
		}
		
		/**
		 * Verifica si el miembro se encuentra en la Tarea,por medio de un booleano, 
		 * buscando en todas sus subtareas.
		 * @param miembro Miembro
		 * @return boolean
		 */
		public boolean contieneMiembro(Miembro miembro) {
			
			boolean contieneAlMiembro=false;
			for ( AdministradorTarea at : this.getTareasQueLaComponenen())
			{ contieneAlMiembro= contieneAlMiembro  | at.contieneMiembro(miembro);  }
			return contieneAlMiembro;
		}


		public void setTareasQueLaComponenen(List <AdministradorTarea> tareasQueLaComponenen) {
			this.tareasQueLaComponenen = tareasQueLaComponenen;
		}


		public List <AdministradorTarea> getTareasQueLaComponenen() {
			return tareasQueLaComponenen;
		}


	



	}


