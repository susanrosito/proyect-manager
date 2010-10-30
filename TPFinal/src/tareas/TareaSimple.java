package tareas;

import usuarioMiembroYFecha.Miembro;
import usuarioMiembroYFecha.Fecha;

import estados.Estado;

public class TareaSimple extends Tarea {
	private Miembro miembroAsignado;
	private int count; 
	

	public TareaSimple(String nombre, String descrip,Fecha fechaEstFinalizacion) {
		super(nombre, descrip, fechaEstFinalizacion);
		this.count = 0;
	}

	public void poneteEnTrabajo() {
		this.getEstado().enTrabajo(this);
	}

	public void iniciate() {
		this.getEstado().iniciada(this);
	}

	public void finalizate() {
		this.getEstado().finalizada(this);
	}

	public void pausate() {
		this.getEstado().pausada(this);
	}

	public void cerrate() {
		this.getEstado().cerrada(this);
	}

	public Estado verEstado() {
		return super.getEstado();
	}

	public void reAbrite() {
		this.getEstado().iniciada(this);
		
	}
	public void modificarMiembroAsignado(Miembro miembro) {
		if(this.getCount() == 0){
		   this.iniciate();
		   this.setCount(getCount()+1);
		}
		this.setMiembroAsignado(miembro);
	}

	public boolean tieneOrden() {

		return true;
	}
	public void setMiembroAsignado(Miembro miembro) {
		this.miembroAsignado = miembro;

	}

	public Miembro getMiembroAsignado() {
		return this.miembroAsignado;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
    public boolean verificarSiEstaPausada()
    { return this.getEstado().verificarSiEstaPausada(); }
   
    public boolean verificarSiEstaIniciada()
    { return this.getEstado().verificarSiEstaIniciada(); }
   
    public boolean verificarSiEstaCerrada()
    { return this.getEstado().verificarSiEstaCerrada(); }
   
    public boolean verificarSiEstaEnTrabajo()
    { return this.getEstado().verificarSiEstaEnTrabajo(); }
   
    public boolean verificarSiEstaFinalizada()
    { return this.getEstado().verificarSiEstaFinalizada(); }
   
    public boolean verificarSiEstaCreada()
    { return this.getEstado().verificarSiEstaCreada(); }
}
