package tareas;

import estados.Estado;

public class OrganizadorTarea implements AdministradorTarea{
		private Tarea tarea;
		private Tarea tareaAnterior;
	
		public OrganizadorTarea(){
			
		}
	public Estado verEstado() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean estaEnTiempo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estaProximaAVencer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void cerrate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reAbrite() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tieneOrden() {
		// TODO Auto-generated method stub
		return false;
	}
	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	public Tarea getTarea() {
		return tarea;
	}
	public void setTareaAnterior(Tarea tareaAnterior) {
		this.tareaAnterior = tareaAnterior;
	}
	public Tarea getTareaAnterior() {
		return tareaAnterior;
	}

}
