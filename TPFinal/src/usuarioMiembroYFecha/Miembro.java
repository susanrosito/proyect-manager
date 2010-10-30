package usuarioMiembroYFecha;



import proyectoYSistema.Proyecto;


public class Miembro {

	private Usuario usuario;
	private String rol;
	private int hsTrabajadas;
	private Proyecto proyecto;
	private Fecha fechaInicio;
	private Fecha fechaFin;
	
	
	public Miembro(Usuario elUsuario, String rol)
	{
		this.usuario=elUsuario;
		this.rol=rol;
		this.hsTrabajadas=0;
		this.proyecto=null;
		this.fechaInicio=new Fecha();
		this.fechaFin=null;
	}
	
	protected Miembro cambiarRol(String otroRol) {
		
		this.setFechaFin(new Fecha());
		Miembro nuevaInstancia= new Miembro(this.getUsuario(),otroRol);
		return nuevaInstancia;
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



	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}






	
}
