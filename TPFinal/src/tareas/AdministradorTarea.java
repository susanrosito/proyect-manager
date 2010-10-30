package tareas;

import estados.Estado;

public interface AdministradorTarea {
	public Estado verEstado();
	public boolean estaEnTiempo();
	public boolean estaProximaAVencer();
	public void cerrate();
	public void reAbrite();
	public boolean tieneOrden();

}
