package tareas;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;

import java.util.List;

import junit.framework.TestCase;

import estados.Cerrada;
import estados.EnTrabajo;
import estados.Finalizada;
import estados.Pausada;





public class TestTareaCompuesta extends TestCase{
	
	TareaSimple tareaSCerrada;
	TareaSimple tareSFinalizada;
	TareaSimple tareaSEnTrabajo;
	TareaSimple tareaSPausada;
	TareaSimple tareaSCreada;
	TareaSimple tareaSIniciada;
	
	TareaCompuesta tareaC;
	String descripccionTC = "La descripccion";
	String nombreTC= "Un Nombre";
	
	
	public void setUp()
	{
		this.tareaSCerrada = createMock(TareaSimple.class);
		//expect(tareaSCerrada.getEstado()).andReturn(Cerrada.GetInstance());
		
		this.tareSFinalizada = createMock(TareaSimple.class);
		//expect(tareSFinalizada.getEstado()).andReturn(Finalizada.GetInstance());
		
		this.tareaSEnTrabajo = createMock(TareaSimple.class);
		//expect(tareaSEnTrabajo.getEstado()).andReturn(EnTrabajo.GetInstance());
		
		this.tareaSPausada = createMock(TareaSimple.class);
		//expect(tareaSPausada.getEstado()).andReturn(Pausada.GetInstance());
		
		this.tareaSCreada = createMock(TareaSimple.class);
		//expect(tareaSPausada.getEstado()).andReturn(Creada.GetInstance());
		
		this.tareaSIniciada = createMock(TareaSimple.class);
		//expect(tareaSPausada.getEstado()).andReturn(Iniciada.GetInstance());
		
		this.tareaC = new TareaCompuesta(this.nombreTC,this.descripccionTC , null);
		
		
	}
       
	public void testConstructor (){
		
		assertSame("Confirma que la descripcción enviada por parametro se guardo correctamente " +
				"en la variable de la tarea compuesta.",this.getDescripccionTC(),this.getTareaC().getDescripcion());
		
		assertSame("Confirma que el nombre enviado por parametro se guardo correctamente " +
				"en la variable de la tarea compuesta.",this.getNombreTC(),this.getTareaC().getNombre());
		
		assertTrue("Confirma que no hay subtareas al crearse una tarea compuesta.", 
				this.getTareaC().getTareasQueLaComponenen().size()==0);
		
	}
	
	public void testAgregarTarea()
	{
		TareaCompuesta tC= this.getTareaC();
		int tamañoAnteriorDeListaDeTareas=tC.getTareasQueLaComponenen().size();
		tC.agregarTarea(this.getTareaSCreada());
		int tamañoActual=tC.getTareasQueLaComponenen().size();
		
		assertTrue("Confirma que el tamaño de la lista de tareas aumento solo uno en cantidad.", 
				tamañoAnteriorDeListaDeTareas+1==tamañoActual);
		assertTrue("Confirma que la tarea pasada por parametro efectivamente esta ahora en la tarea compuesta.", 
				tC.getTareasQueLaComponenen().contains(this.getTareaSCreada()));
		
	}
	
  
     public void testTieneOrden() {
		
		assertFalse("Comprueba que el metodo retorne falso.", this.getTareaC().tieneOrden());
		
		}
     
     
	
	/*   public void testVerificarSiEstaPausada() {
		
		this.getTareaC().agregarTarea(this.getTareaSEnTrabajo());
		this.getTareaC().agregarTarea(this.getTareaSCreada());
		this.getTareaC().agregarTarea(this.getTareaSPausada());
		
		
	}*/

	public TareaSimple getTareaSCerrada() {
		return tareaSCerrada;
	}

	public void setTareaSCerrada(TareaSimple tareaSCerrada) {
		this.tareaSCerrada = tareaSCerrada;
	}

	public TareaSimple getTareSFinalizada() {
		return tareSFinalizada;
	}

	public void setTareSFinalizada(TareaSimple tareSFinalizada) {
		this.tareSFinalizada = tareSFinalizada;
	}

	public TareaSimple getTareaSEnTrabajo() {
		return tareaSEnTrabajo;
	}

	public void setTareaSEnTrabajo(TareaSimple tareaSEnTrabajo) {
		this.tareaSEnTrabajo = tareaSEnTrabajo;
	}

	public TareaSimple getTareaSPausada() {
		return tareaSPausada;
	}

	public void setTareaSPausada(TareaSimple tareaSPausada) {
		this.tareaSPausada = tareaSPausada;
	}

	public String getDescripccionTC() {
		return descripccionTC;
	}

	public void setDescripccionTC(String descripccionTC) {
		this.descripccionTC = descripccionTC;
	}

	public String getNombreTC() {
		return nombreTC;
	}

	public void setNombreTC(String nombreTC) {
		this.nombreTC = nombreTC;
	}

	public TareaSimple getTareaSCreada() {
		return tareaSCreada;
	}

	public void setTareaSCreada(TareaSimple tareaSCreada) {
		this.tareaSCreada = tareaSCreada;
	}

	public TareaSimple getTareaSIniciada() {
		return tareaSIniciada;
	}

	public void setTareaSIniciada(TareaSimple tareaSIniciada) {
		this.tareaSIniciada = tareaSIniciada;
	}

	public TareaCompuesta getTareaC() {
		return tareaC;
	}

	public void setTareaC(TareaCompuesta tareaC) {
		this.tareaC = tareaC;
	}


}
