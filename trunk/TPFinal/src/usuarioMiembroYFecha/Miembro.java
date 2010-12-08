package usuarioMiembroYFecha;

import proyectoYSistema.Proyecto;

/**
 * Esta clase representar� al miembro de un proyecto. Para ello va a tener una variable
 * del tipo String, el rol que ocupa en el proyecto.
 * Dos variables del tipo fecha que representar�n la fecha de inicio desde que tiene ese
 * rol en ese proyecto y la fecha del fin del mismo, que indicar� que ese ya no es su
 * rol actual. 
 * Una variable del tipo Protyecto, el proyecto en el que esta.
 * Un int que representa las horas trabajadas en ese proyecto con ese rol.
 * Y por ultimo un usuario que identifica a la persona.
 */
public class Miembro {

	private Usuario usuario;
	private String rol;
	private int hsTrabajadas;
	private Proyecto proyecto;
	private Fecha fechaInicio;
	private Fecha fechaFin;

	/**
	 * El constructor recibe dos parametros, un usuario que representa a la persona
	 * que pasa a ser miembro para luego ser introducido en un proyecto, y un String 
	 * que representa el rol que va a desempe�ar.
	 * Las horas trabajadas hasta el momento son cero y la fecha de Inicio es la fecha
	 * actual.
	 * Las dem�s variables se mantienen en null.
	 * @param elUsuario
	 * @param rol
	 */
	public Miembro(Usuario elUsuario, String rol) {
		this.usuario = elUsuario;
		this.rol = rol;
		this.hsTrabajadas = 0;
		this.proyecto = null;
		this.fechaInicio = new Fecha();
		this.fechaFin = null;

	}

	/**
	 * El metodo cambiarRol() recibe un solo parametro del tipo String que es 
	 * el nuevo rol del usuario al que se le envia el mensaje. Lo que retornar� 
	 * sera una nueva instancia nueva de miembro que contendra el mismo usuario 
	 * que la instancia anterior pero con otro String que representa su nuevo rol.
	 * @param otroRol
	 * @return
	 */
	protected Miembro cambiarRol(String otroRol) {

		this.setFechaFin(new Fecha());
		Miembro nuevaInstancia = new Miembro(this.getUsuario(), otroRol);
		return nuevaInstancia;
	}

	public String toString()
	{
		return this.getUsuario().getNombre();
		
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getHsTrabajadas() {
		return hsTrabajadas;
	}

	public void setHsTrabajadas(int hsTrabajadas) {
		this.hsTrabajadas = hsTrabajadas;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public Fecha getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Fecha fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Fecha getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Fecha fechaFin) {
		this.fechaFin = fechaFin;
	}



}
