package tareas;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.*;


import junit.framework.TestCase;




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
	TareaCompuesta tareaCompuestaEnTrabajo;
	TareaCompuesta tareaCompuestaIniciada;
	TareaCompuesta tareaCompuestaCerrada;
	TareaCompuesta tareaCompuestaFinalizada;
	TareaCompuesta tareaCompuestaCreada;
	TareaCompuesta tareaCompuestaPausada;
	
	
	
	public void setUp()
	{
		this.tareaSCerrada = createMock(TareaSimple.class);
		this.tareSFinalizada = createMock(TareaSimple.class);
		this.tareaSEnTrabajo = createMock(TareaSimple.class);
		this.tareaSPausada = createMock(TareaSimple.class);
		this.tareaSCreada = createMock(TareaSimple.class);
		this.tareaSIniciada = createMock(TareaSimple.class);
			
		this.tareaC = new TareaCompuesta(this.nombreTC,this.descripccionTC , null);
		
		this.tareaCompuestaEnTrabajo=new TareaCompuesta(null,null,null);
		tareaCompuestaEnTrabajo.agregarTarea(this.getTareaSCreada());
		tareaCompuestaEnTrabajo.agregarTarea(this.getTareaSEnTrabajo());
		tareaCompuestaEnTrabajo.agregarTarea(this.getTareSFinalizada());
		tareaCompuestaEnTrabajo.agregarTarea(this.getTareaSEnTrabajo());
		tareaCompuestaEnTrabajo.agregarTarea(this.getTareaSIniciada());
		
		this.tareaCompuestaIniciada=new TareaCompuesta(null,null,null);
		tareaCompuestaIniciada.agregarTarea(this.getTareaSIniciada());
		tareaCompuestaIniciada.agregarTarea(this.getTareaSIniciada());
		tareaCompuestaIniciada.agregarTarea(this.getTareaSIniciada());
		
		this.tareaCompuestaFinalizada=new TareaCompuesta(null,null,null);
		tareaCompuestaFinalizada.agregarTarea(this.getTareSFinalizada());
		tareaCompuestaFinalizada.agregarTarea(this.getTareSFinalizada());
		tareaCompuestaFinalizada.agregarTarea(this.getTareSFinalizada());
		
		this.tareaCompuestaCerrada=new TareaCompuesta(null,null,null);
		tareaCompuestaCerrada.agregarTarea(this.getTareaSCerrada());
		tareaCompuestaCerrada.agregarTarea(this.getTareaSCerrada());
		tareaCompuestaCerrada.agregarTarea(this.getTareaSCerrada());
		
		this.tareaCompuestaCreada=new TareaCompuesta(null,null,null);
		tareaCompuestaCreada.agregarTarea(this.getTareaSCreada());
		tareaCompuestaCreada.agregarTarea(this.getTareaSCreada());
		tareaCompuestaCreada.agregarTarea(this.getTareaSCreada());
		
		this.tareaCompuestaPausada=new TareaCompuesta(null,null,null);
		tareaCompuestaPausada.agregarTarea(this.getTareaSCreada());
		tareaCompuestaPausada.agregarTarea(this.getTareaSEnTrabajo());
		tareaCompuestaPausada.agregarTarea(this.getTareaSPausada());
		
	}
       
	protected void tearDown() {}
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
     
     
	
	   public void testVerificarSiEstaEnTrabajo() {
		 
		   expect(tareSFinalizada.verificarSiEstaEnTrabajo()).andReturn(false);
		   expect(tareSFinalizada.verificarSiEstaIniciada()).andReturn(false);
		   expect(tareSFinalizada.verificarSiEstaCreada()).andReturn(false);
		   expect(tareSFinalizada.verificarSiEstaFinalizada()).andReturn(true);
		   replay(tareSFinalizada);		
           
           expect(tareaSEnTrabajo.verificarSiEstaEnTrabajo()).andReturn(true);
		   expect(tareaSEnTrabajo.verificarSiEstaIniciada()).andReturn(false);
		   expect(tareaSEnTrabajo.verificarSiEstaCreada()).andReturn(false);
		   expect(tareaSEnTrabajo.verificarSiEstaFinalizada()).andReturn(false);
		   expect(tareaSEnTrabajo.verificarSiEstaEnTrabajo()).andReturn(true);
		   expect(tareaSEnTrabajo.verificarSiEstaIniciada()).andReturn(false);
		   expect(tareaSEnTrabajo.verificarSiEstaCreada()).andReturn(false);
		   expect(tareaSEnTrabajo.verificarSiEstaFinalizada()).andReturn(false);
           replay(tareaSEnTrabajo);
           
           expect(tareaSCreada.verificarSiEstaEnTrabajo()).andReturn(false);
		   expect(tareaSCreada.verificarSiEstaIniciada()).andReturn(false);
		   expect(tareaSCreada.verificarSiEstaCreada()).andReturn(true);
		   expect(tareaSCreada.verificarSiEstaFinalizada()).andReturn(false);
           replay(tareaSCreada);
           
   		   expect(tareaSIniciada.verificarSiEstaEnTrabajo()).andReturn(false);
		   expect(tareaSIniciada.verificarSiEstaIniciada()).andReturn(true);
		   expect(tareaSIniciada.verificarSiEstaCreada()).andReturn(false);
		   expect(tareaSIniciada.verificarSiEstaFinalizada()).andReturn(false);
   		   replay(tareaSIniciada);
   		
   		   assertTrue("Confirma que el estado de la tareaCompuestaEnTrabajo efectivamente sea enTrabajo.", 
				this.getTareaCompuestaEnTrabajo().verificarSiEstaEnTrabajo());
   		  
	}

	   
	   public void testVerificarSiEstaIniciada() {
			 
		             
   		   
		   expect(tareaSIniciada.verificarSiEstaIniciada()).andReturn(true);
		   expect(tareaSIniciada.verificarSiEstaCreada()).andReturn(false);
		   expect(tareaSIniciada.verificarSiEstaIniciada()).andReturn(true);
		   expect(tareaSIniciada.verificarSiEstaCreada()).andReturn(false);
		   expect(tareaSIniciada.verificarSiEstaIniciada()).andReturn(true);
		   expect(tareaSIniciada.verificarSiEstaCreada()).andReturn(false);
		   
  		  replay(tareaSIniciada);
   		
   		   assertTrue("Confirma que el estado de la tareaCompuestaIniciada efectivamente sea Iniciada.", 
				this.getTareaCompuestaIniciada().verificarSiEstaIniciada());
   		  
	}
	   
 public void testVerificarSiEstaFinalizada() {
			 		             
   		   		   
		   expect(tareSFinalizada.verificarSiEstaFinalizada()).andReturn(true);
		   expect(tareSFinalizada.verificarSiEstaFinalizada()).andReturn(true);
		   expect(tareSFinalizada.verificarSiEstaFinalizada()).andReturn(true);
		    replay(tareSFinalizada);
   		
   		   assertTrue("Confirma que el estado de la tareaCompuestaFinalizada efectivamente sea finalizada.", 
				this.getTareaCompuestaFinalizada().verificarSiEstaFinalizada());
   		  
	}
 
 public void testVerificarSiEstaCerrada() {
      
		   
	   expect(tareaSCerrada.verificarSiEstaCerrada()).andReturn(true);
	   expect(tareaSCerrada.verificarSiEstaCerrada()).andReturn(true);
	   expect(tareaSCerrada.verificarSiEstaCerrada()).andReturn(true);
	    replay(tareaSCerrada);
		
		   assertTrue("Confirma que el estado de la tareaCompuestaCerrada efectivamente sea cerrada.", 
			this.getTareaCompuestaCerrada().verificarSiEstaCerrada());
		  
}
 
 public void testVerificarSiEstaCreada() {
     
	   
	   expect(tareaSCreada.verificarSiEstaCreada()).andReturn(true);
	   expect(tareaSCreada.verificarSiEstaCreada()).andReturn(true);
	   expect(tareaSCreada.verificarSiEstaCreada()).andReturn(true);
	    replay(tareaSCreada);
		
		   assertTrue("Confirma que el estado de la tareaCompuestaCerrada efectivamente sea cerrada.", 
			this.getTareaCompuestaCreada().verificarSiEstaCreada());
		  
}
 
 public void testVerificarSiEstaPausada() {
     
	   
	   expect(tareaSPausada.verificarSiEstaPausada()).andReturn(true);
	   replay(tareaSPausada);
	   expect(tareaSCreada.verificarSiEstaPausada()).andReturn(false);
	   replay(tareaSCreada);
	   expect(tareaSEnTrabajo.verificarSiEstaPausada()).andReturn(false);
	   replay(tareaSEnTrabajo);
		
		   assertTrue("Confirma que el estado de la tareaCompuestaCerrada efectivamente sea cerrada.", 
			this.getTareaCompuestaPausada().verificarSiEstaPausada());
		  
}
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

	public TareaCompuesta getTareaCompuestaEnTrabajo() {
		return tareaCompuestaEnTrabajo;
	}

	public void setTareaCompuestaEnTrabajo(TareaCompuesta tareaCompuestaEnTrabajo) {
		this.tareaCompuestaEnTrabajo = tareaCompuestaEnTrabajo;
	}

	public TareaCompuesta getTareaCompuestaIniciada() {
		return tareaCompuestaIniciada;
	}

	public void setTareaCompuestaIniciada(TareaCompuesta tareaCompuestaIniciada) {
		this.tareaCompuestaIniciada = tareaCompuestaIniciada;
	}

	public TareaCompuesta getTareaCompuestaCerrada() {
		return tareaCompuestaCerrada;
	}

	public void setTareaCompuestaCerrada(TareaCompuesta tareaCompuestaCerrada) {
		this.tareaCompuestaCerrada = tareaCompuestaCerrada;
	}

	public TareaCompuesta getTareaCompuestaFinalizada() {
		return tareaCompuestaFinalizada;
	}

	public void setTareaCompuestaFinalizada(TareaCompuesta tareaCompuestaFinalizada) {
		this.tareaCompuestaFinalizada = tareaCompuestaFinalizada;
	}

	public TareaCompuesta getTareaCompuestaCreada() {
		return tareaCompuestaCreada;
	}

	public void setTareaCompuestaCreada(TareaCompuesta tareaCompuestaCreada) {
		this.tareaCompuestaCreada = tareaCompuestaCreada;
	}

	public TareaCompuesta getTareaCompuestaPausada() {
		return tareaCompuestaPausada;
	}

	public void setTareaCompuestaPausada(TareaCompuesta tareaCompuestaPausada) {
		this.tareaCompuestaPausada = tareaCompuestaPausada;
	}




}
