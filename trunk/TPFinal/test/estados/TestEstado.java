package estados;



import tareas.TareaSimple;
import usuarioMiembroYFecha.Fecha;
import estados.Cerrada;
import estados.Estado;

import junit.framework.Assert;
import junit.framework.TestCase;

    public class TestEstado extends TestCase {
	private Estado unaInstancia;
	private Estado otraInstancia;
	private Estado estado;
	
	private TareaSimple tarea;

	public void setUp() {
		this.setTarea(new TareaSimple("Desarrollador", "Desarrollo tp",new Fecha("20090617")));
	}
	
	public void testConstructor (){
		
		//Testea que ambas instancias de Cerrada sean las mismas ya que es
		// un singleton.
		Assert.assertSame(this.getUnaInstancia(), this.getOtraInstancia());
		Assert.assertSame(this.getOtraInstancia(), this.getUnaInstancia());
	
	}
	
	public void testEnTrabajo(TareaSimple unaTarea){
		Estado esperado = this.getTarea().getEstado();
		 estado.enTrabajo(this.getTarea());
			
			assertSame(esperado, this.getTarea().getEstado());
	}
	
	public void testIniciada(TareaSimple unaTarea){
		Estado esperado = this.getTarea().getEstado();
		 estado.iniciada(this.getTarea());
			
			assertSame(esperado, this.getTarea().getEstado());
	}
	
	public void testCerrada(TareaSimple unaTarea)	
	{Estado esperado = Cerrada.GetInstance();
	 estado.cerrada(this.getTarea());
		
		assertSame(esperado, this.getTarea().getEstado());}
	
	public void testFinalizada(TareaSimple unaTarea){
		Estado esperado = this.getTarea().getEstado();
		 estado.finalizada(this.getTarea());
			
			assertSame(esperado, this.getTarea().getEstado());
	}
	
	public void testPausada(TareaSimple unaTarea){
		Estado esperado = this.getTarea().getEstado();
		 estado.pausada(this.getTarea());
			
			assertSame(esperado, this.getTarea().getEstado());
	}
	
	public void testCreada(TareaSimple unaTarea){
		Estado esperado = this.getTarea().getEstado();
		 estado.creada(this.getTarea());
			
			assertSame(esperado, this.getTarea().getEstado());
	}

	public Estado getUnaInstancia() {
		return unaInstancia;
	}
	
	public void setUnaInstancia(Estado unaInstancia) {
		this.unaInstancia = unaInstancia;
	}

	public Estado getOtraInstancia() {
		return otraInstancia;
	}

	public void setOtraInstancia(Estado otraInstancia) {
		this.otraInstancia = otraInstancia;
	}

	
	public TareaSimple getTarea() {
		return tarea;
	}

	public void setTarea(TareaSimple tarea) {
		this.tarea = tarea;
	}
	
	

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	

}
