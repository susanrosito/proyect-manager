package tareas;

import usuarioMiembroYFecha.Miembro;
import estados.Estado;


public interface AdministradorTarea {
	
	public Estado verEstado();
	public boolean estaEnTiempo();
	public boolean estaProximaAVencer();
	public void cerrate();
	public void reAbrite(String note);
	public boolean tieneOrden();
	public boolean verificarSiEstaPausada();
	public boolean verificarSiEstaIniciada();
	public boolean verificarSiEstaCerrada();
	public boolean verificarSiEstaEnTrabajo();
	public boolean verificarSiEstaFinalizada();
	public boolean verificarSiEstaCreada();
	public boolean contieneMiembro(Miembro miembro);
}