package tareas;

import java.util.List;

import usuarioMiembroYFecha.Miembro;

public interface AdministradorTarea {

	public String verEstado();

	public boolean estaEnTiempo();

	public boolean estaProximaAVencer();

	public void cerrate();

	public void reAbrite(String note);

	public boolean tieneOrden();

	public List<Miembro> obtenerMiembros();

	public boolean verificarSiEstaPausada();

	public boolean verificarSiEstaIniciada();

	public boolean verificarSiEstaCerrada();

	public boolean verificarSiEstaEnTrabajo();

	public boolean verificarSiEstaFinalizada();

	public boolean verificarSiEstaCreada();
}