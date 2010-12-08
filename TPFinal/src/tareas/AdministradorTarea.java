package tareas;

import java.util.List;

import estados.NoPuedeCambiarseElEstadoExcepccion;

import usuarioMiembroYFecha.Fecha;
import usuarioMiembroYFecha.Miembro;

public interface AdministradorTarea {

	public boolean sosTareaSimple();
	
	public String verEstado();

	public boolean estaEnTiempo();
	
	public String getNombre();
	
	public String getDescripcion();
	
	public boolean estaProximaAVencer();

	public void cerrate(String note);

	public void reAbrite(String note);

	public boolean tieneOrden();
	
     public Miembro getMiembroAsignado();
	
	public Fecha getFechaCreacion();
	
	
	public Fecha getFechaEstimadaFinalizacion();

	public Fecha getFechaFinalizacion() ;

	public void setPorcentajeFinalizacion(float porcentajeFinalizacion);

	public float getPorcentajeFinalizacion();

	public List<Miembro> obtenerMiembros();

	public boolean verificarSiEstaPausada();

	public boolean verificarSiEstaIniciada();

	public boolean verificarSiEstaCerrada();

	public boolean verificarSiEstaEnTrabajo();

	public boolean verificarSiEstaFinalizada();

	public boolean verificarSiEstaCreada();

	public void pausate() throws NoPuedeCambiarseElEstadoExcepccion;

	public void poneteEnTrabajo() throws NoPuedeCambiarseElEstadoExcepccion;

	public void iniciate() throws NoPuedeCambiarseElEstadoExcepccion;

	public void finalizate();
}