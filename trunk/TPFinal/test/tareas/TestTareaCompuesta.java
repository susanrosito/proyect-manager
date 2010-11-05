package tareas;

import static org.easymock.EasyMock.*;

import java.util.LinkedList;
import java.util.List;

import usuarioMiembroYFecha.Miembro;

import estados.*;


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
	
	TareaCompuesta otraTareaCompuesta;
	
	Miembro miembroTareaS1;
	List<Miembro> listaTareaS1;

	Miembro miembroTareaS2 ;
	List<Miembro> listaTareaS2;
		
	Miembro miembroTareaC1;
	Miembro miembro2TareaC1;
	Miembro miembro3TareaC1;
	List<Miembro> listaTareaC1;
	
	List<Miembro> listaConTodosLosMiembros;
	
	String stringMotivo;
	
	
	
	public void setUp()
	{	
		this.stringMotivo= "Se re abre la tarea porque...";
		this.tareaSCerrada = createMock(TareaSimple.class);
		expect(tareaSCerrada.verificarSiEstaIniciada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSCerrada.verificarSiEstaCreada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSCerrada.verificarSiEstaEnTrabajo()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSCerrada.verificarSiEstaFinalizada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSCerrada.verificarSiEstaCerrada()).andReturn(true);
		expectLastCall().anyTimes();
		expect(tareaSCerrada.verificarSiEstaPausada()).andReturn(false);
		expectLastCall().anyTimes();
		this.tareaSCerrada.reAbrite(this.stringMotivo);
		
		expectLastCall().anyTimes();
		
		
		this.tareSFinalizada = createMock(TareaSimple.class);
		expect(tareSFinalizada.verificarSiEstaIniciada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareSFinalizada.verificarSiEstaCreada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareSFinalizada.verificarSiEstaEnTrabajo()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareSFinalizada.verificarSiEstaFinalizada()).andReturn(true);
		expectLastCall().anyTimes();
		expect(tareSFinalizada.verificarSiEstaCerrada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareSFinalizada.verificarSiEstaPausada()).andReturn(false);
		expectLastCall().anyTimes();
		
		this.tareaSEnTrabajo = createMock(TareaSimple.class);
		expect(tareaSEnTrabajo.verificarSiEstaIniciada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSEnTrabajo.verificarSiEstaCreada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSEnTrabajo.verificarSiEstaEnTrabajo()).andReturn(true);
		expectLastCall().anyTimes();
		expect(tareaSEnTrabajo.verificarSiEstaFinalizada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSEnTrabajo.verificarSiEstaCerrada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSEnTrabajo.verificarSiEstaPausada()).andReturn(false);
		expectLastCall().anyTimes();
		this.tareaSEnTrabajo.cerrate();
		
		this.tareaSPausada = createMock(TareaSimple.class);
		expect(tareaSPausada.verificarSiEstaIniciada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSPausada.verificarSiEstaCreada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSPausada.verificarSiEstaEnTrabajo()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSPausada.verificarSiEstaFinalizada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSPausada.verificarSiEstaCerrada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSPausada.verificarSiEstaPausada()).andReturn(true);
		expectLastCall().anyTimes();
		
		this.tareaSCreada = createMock(TareaSimple.class);
		expect(tareaSCreada.verificarSiEstaIniciada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSCreada.verificarSiEstaCreada()).andReturn(true);
		expectLastCall().anyTimes();
		expect(tareaSCreada.verificarSiEstaEnTrabajo()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSCreada.verificarSiEstaFinalizada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSCreada.verificarSiEstaCerrada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSCreada.verificarSiEstaPausada()).andReturn(false);
		expectLastCall().anyTimes();
		this.tareaSCreada.cerrate();
		
		this.tareaSIniciada = createMock(TareaSimple.class);
		expect(tareaSIniciada.verificarSiEstaIniciada()).andReturn(true);
		expectLastCall().anyTimes();
		expect(tareaSIniciada.verificarSiEstaCreada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSIniciada.verificarSiEstaEnTrabajo()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSIniciada.verificarSiEstaFinalizada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSIniciada.verificarSiEstaCerrada()).andReturn(false);
		expectLastCall().anyTimes();
		expect(tareaSIniciada.verificarSiEstaPausada()).andReturn(false);
		expectLastCall().anyTimes();
		this.tareaSIniciada.cerrate();
		
		replay(tareaSIniciada, tareSFinalizada, tareaSCreada, tareaSCerrada, tareaSEnTrabajo, tareaSPausada );
			
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
		
				
		miembroTareaS1= createMock(Miembro.class);
		listaTareaS1 = new LinkedList<Miembro>();
		listaTareaS1.add(miembroTareaS1);
		
		miembroTareaS2= createMock(Miembro.class);
		listaTareaS2 = new LinkedList<Miembro>();
		listaTareaS2.add(miembroTareaS2);
		
		miembroTareaC1= createMock(Miembro.class);
		miembro2TareaC1= createMock(Miembro.class);
		miembro3TareaC1= createMock(Miembro.class);
		listaTareaC1 = new LinkedList<Miembro>();
		listaTareaC1.add(miembroTareaC1);
		listaTareaC1.add(miembro2TareaC1);
		listaTareaC1.add(miembro3TareaC1);
		
		listaConTodosLosMiembros= new LinkedList<Miembro>();
		listaConTodosLosMiembros.add(miembroTareaC1);
		listaConTodosLosMiembros.add(miembro2TareaC1);
		listaConTodosLosMiembros.add(miembro3TareaC1);
		listaConTodosLosMiembros.add(miembroTareaS2);
		listaConTodosLosMiembros.add(miembroTareaS1);
		
		this.otraTareaCompuesta= new TareaCompuesta(null,null,null);

		
		
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
     
     	
	   public void testVerificarSiEstaEnTrabajoCuandoEstaEnTrabajo() {
		 
		   		
   		   assertTrue("Confirma que el estado de la tareaCompuestaEnTrabajo efectivamente sea enTrabajo.", 
				this.getTareaCompuestaEnTrabajo().verificarSiEstaEnTrabajo());
   		  
	}

	   public void testVerificarSiEstaEnTrabajoCuandoNOEstaEnTrabajo() {
			 
	   		
   		   assertFalse("Confirma que el estado de la tareaCompuestaCerrada no sea enTrabajo.", 
				this.getTareaCompuestaCerrada().verificarSiEstaEnTrabajo());
   		   assertFalse("Confirma que el estado de la tareaCompuestaIniciada no sea enTrabajo.", 
				this.getTareaCompuestaIniciada().verificarSiEstaEnTrabajo());
   		   assertFalse("Confirma que el estado de la tareaCompuestaCreada no sea enTrabajo.", 
				this.getTareaCompuestaCreada().verificarSiEstaEnTrabajo());
   		   assertFalse("Confirma que el estado de la tareaCompuestaPausada no sea enTrabajo.", 
				this.getTareaCompuestaPausada().verificarSiEstaEnTrabajo());
   		   assertFalse("Confirma que el estado de la tareaCompuestaFinalizada no sea enTrabajo.", 
				this.getTareaCompuestaFinalizada().verificarSiEstaEnTrabajo());
   		  
	}
	   
	   public void testVerificarSiEstaIniciadaCuandoEstaIniciada() {
			 	            
   		
   		   assertTrue("Confirma que el estado de la tareaCompuestaIniciada efectivamente sea Iniciada.", 
				this.getTareaCompuestaIniciada().verificarSiEstaIniciada());
   		  
	}
	   
	   public void testVerificarSiEstaIniciadaCuandoNOEstaIniciada() {
			 
	   		
   		   assertFalse("Confirma que el estado de la tareaCompuestaCerrada no sea Iniciada.", 
				this.getTareaCompuestaCerrada().verificarSiEstaIniciada());
   		   assertFalse("Confirma que el estado de la tareaCompuestaEntrabajo no sea Iniciada.", 
				this.getTareaCompuestaEnTrabajo().verificarSiEstaIniciada());
   		   assertFalse("Confirma que el estado de la tareaCompuestaCreada no sea Iniciada.", 
				this.getTareaCompuestaCreada().verificarSiEstaIniciada());
   		   assertFalse("Confirma que el estado de la tareaCompuestaPausada no sea Iniciada.", 
				this.getTareaCompuestaPausada().verificarSiEstaIniciada());
   		   assertFalse("Confirma que el estado de la tareaCompuestaFinalizada no sea Iniciada.", 
				this.getTareaCompuestaFinalizada().verificarSiEstaIniciada());
   		  
	}
	   
 public void testVerificarSiEstaFinalizadaCuandoEstaFinalizada() {
			 		             
   	
   		   assertTrue("Confirma que el estado de la tareaCompuestaFinalizada efectivamente sea finalizada.", 
				this.getTareaCompuestaFinalizada().verificarSiEstaFinalizada());
   		  
	}
 
 public void testVerificarSiEstaFinalizadaCuandoNOEstaFinalizada() {
      
	       assertFalse("Confirma que el estado de la tareaCompuestaCerrada no sea Finalizada.", 
				this.getTareaCompuestaCerrada().verificarSiEstaFinalizada());
		   assertFalse("Confirma que el estado de la tareaCompuestaEntrabajo no sea Finalizada.", 
				this.getTareaCompuestaEnTrabajo().verificarSiEstaFinalizada());
		   assertFalse("Confirma que el estado de la tareaCompuestaCreada no sea Finalizada.", 
				this.getTareaCompuestaCreada().verificarSiEstaFinalizada());
		   assertFalse("Confirma que el estado de la tareaCompuestaPausada no sea Finalizada.", 
				this.getTareaCompuestaPausada().verificarSiEstaFinalizada());
		   assertFalse("Confirma que el estado de la tareaCompuestaIniciada no sea Finalizada.", 
				this.getTareaCompuestaIniciada().verificarSiEstaFinalizada());
		  
}
 
 public void testVerificarSiEstaCerradaCuandoEstaCerrada() {
      
	
		   assertTrue("Confirma que el estado de la tareaCompuestaCerrada efectivamente sea cerrada.", 
			this.getTareaCompuestaCerrada().verificarSiEstaCerrada());
		  
}
 
 public void testVerificarSiEstaCerradaCuandoNOEstaCerrada() {
     
		
	       assertFalse("Confirma que el estado de la tareaCompuestaFinalizada no sea Cerrada.", 
				this.getTareaCompuestaFinalizada().verificarSiEstaCerrada());
		   assertFalse("Confirma que el estado de la tareaCompuestaEntrabajo no sea Cerrada.", 
				this.getTareaCompuestaEnTrabajo().verificarSiEstaCerrada());
		   assertFalse("Confirma que el estado de la tareaCompuestaCreada no sea Cerrada.", 
				this.getTareaCompuestaCreada().verificarSiEstaCerrada());
		   assertFalse("Confirma que el estado de la tareaCompuestaPausada no sea Cerrada.", 
				this.getTareaCompuestaPausada().verificarSiEstaCerrada());
		   assertFalse("Confirma que el estado de la tareaCompuestaIniciada no sea Cerrada.", 
				this.getTareaCompuestaIniciada().verificarSiEstaCerrada());
	  
}
 public void testVerificarSiEstaCreadaCuandoEstaCreada() {
     
	
		   assertTrue("Confirma que el estado de la tareaCompuestaCerrada efectivamente sea cerrada.", 
			this.getTareaCompuestaCreada().verificarSiEstaCreada());
		  
}
 
 public void testVerificarSiEstaCerradaCuandoNOEstaCreada() {
     
		
       assertFalse("Confirma que el estado de la tareaCompuestaFinalizada no sea Creada.", 
			this.getTareaCompuestaFinalizada().verificarSiEstaCreada());
	   assertFalse("Confirma que el estado de la tareaCompuestaEntrabajo no sea Creada.", 
			this.getTareaCompuestaEnTrabajo().verificarSiEstaCreada());
	   assertFalse("Confirma que el estado de la tareaCompuestaCerrada no sea Creada.", 
			this.getTareaCompuestaCerrada().verificarSiEstaCreada());
	   assertFalse("Confirma que el estado de la tareaCompuestaPausada no sea Creada.", 
			this.getTareaCompuestaPausada().verificarSiEstaCreada());
	   assertFalse("Confirma que el estado de la tareaCompuestaIniciada no sea Creada.", 
			this.getTareaCompuestaIniciada().verificarSiEstaCreada());

}
 public void testVerificarSiEstaPausadaCuandoEstaPausada() {
	     
	 
	 assertTrue("Confirma que el estado de la tareaCompuestaCerrada efectivamente sea cerrada.", 
		this.getTareaCompuestaPausada().verificarSiEstaPausada());
 
		  
}
 
 public void testVerificarSiEstaPausadaCuandoNOEstaPausada() {
     
		assertFalse("Confirma que el estado de la tareaCompuestaFinalizada no sea Pausada.", 
				this.getTareaCompuestaFinalizada().verificarSiEstaPausada());
		   assertFalse("Confirma que el estado de la tareaCompuestaEntrabajo no sea Pausada.", 
				this.getTareaCompuestaEnTrabajo().verificarSiEstaPausada());
		   assertFalse("Confirma que el estado de la tareaCompuestaCerrada no sea Pausada.", 
				this.getTareaCompuestaCerrada().verificarSiEstaPausada());
		   assertFalse("Confirma que el estado de la tareaCompuestaCreada no sea Pausada.", 
				this.getTareaCompuestaCreada().verificarSiEstaPausada());
		   assertFalse("Confirma que el estado de la tareaCompuestaIniciada no sea Pausada.", 
				this.getTareaCompuestaIniciada().verificarSiEstaPausada());
	  
}
 
 public void testVerEstadoTareaEnTrabajo() {
	 assertEquals("Confirma que el estado de la tareaCompuestaEntrabajo es EnTrabajo.", 
				EnTrabajo.GetInstance().toString(),this.getTareaCompuestaEnTrabajo().verEstado());
	 
 }
 
 public void testVerEstadoTareaIniciada() {
	 assertEquals("Confirma que el estado de la tareaCompuestaIniciada es Iniciada.", 
				Iniciada.GetInstance().toString(),this.getTareaCompuestaIniciada().verEstado());
	 
 }
 
 public void testVerEstadoTareaPausada() {
	 assertEquals("Confirma que el estado de la tareaCompuestaEntrabajo es Pausada.", 
			 Pausada.GetInstance().toString(),this.getTareaCompuestaPausada().verEstado());
	 
 }
 
 public void testVerEstadoTareaCreada() {
	 assertEquals("Confirma que el estado de la tareaCompuestaCreada es Creada.", 
			 Creada.GetInstance().toString(),this.getTareaCompuestaCreada().verEstado());
	 
 }
 
 public void testVerEstadoTareaCerrada() {
	 assertEquals("Confirma que el estado de la tareaCompuestaCerrada es Cerrada.", 
			 Cerrada.GetInstance().toString(),this.getTareaCompuestaCerrada().verEstado());
	 
 }
 
 public void testVerEstadoTareaFinalizada() {
	 assertEquals("Confirma que el estado de la tareaCompuestaFinalizada es Finalizada.", 
			 Finalizada.GetInstance().toString(),this.getTareaCompuestaFinalizada().verEstado());
	 
 }
 
 /*public void testObtenerMiembros(){
	
	 this.tareaC.agregarTarea(this.tareaSEnTrabajo);
	 this.tareaC.agregarTarea(this.tareaSCreada);
	 this.tareaC.agregarTarea(this.otraTareaCompuesta);
	 
	// expect(this.tareaSEnTrabajo.obtenerMiembros()).andReturn(this.listaTareaS1);
	 
	 
 }*/
 
 public void testReAbrite() 
 {
	
	 this.tareaC.agregarTarea(this.tareaSCerrada);
	 this.tareaC.agregarTarea(this.tareaSCerrada);
	 this.tareaC.agregarTarea(this.tareaSCerrada);
	 
	 
	 this.tareaC.reAbrite(this.stringMotivo);
	
     verify(this.tareaSCerrada);
	 
 }
 
 public void testCerrate() 
 {
	 this.tareaC.agregarTarea(this.tareaSEnTrabajo);
	 this.tareaC.agregarTarea(this.tareaSCreada);
	 this.tareaC.agregarTarea(this.tareaSIniciada);
	 
	    
		
	this.tareaC.cerrate();
	verify(this.tareaSEnTrabajo);
	verify(this.tareaSCreada);
    verify(this.tareaSIniciada);
	 
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

	public Miembro getMiembroTareaS1() {
		return miembroTareaS1;
	}

	public void setMiembroTareaS1(Miembro miembroTareaS1) {
		this.miembroTareaS1 = miembroTareaS1;
	}

	public List<Miembro> getListaTareaS1() {
		return listaTareaS1;
	}

	public void setListaTareaS1(List<Miembro> listaTareaS1) {
		this.listaTareaS1 = listaTareaS1;
	}

	public Miembro getMiembroTareaS2() {
		return miembroTareaS2;
	}

	public void setMiembroTareaS2(Miembro miembroTareaS2) {
		this.miembroTareaS2 = miembroTareaS2;
	}

	public List<Miembro> getListaTareaS2() {
		return listaTareaS2;
	}

	public void setListaTareaS2(List<Miembro> listaTareaS2) {
		this.listaTareaS2 = listaTareaS2;
	}

	public Miembro getMiembroTareaC1() {
		return miembroTareaC1;
	}

	public void setMiembroTareaC1(Miembro miembroTareaC1) {
		this.miembroTareaC1 = miembroTareaC1;
	}

	public Miembro getMiembro2TareaC1() {
		return miembro2TareaC1;
	}

	public void setMiembro2TareaC1(Miembro miembro2TareaC1) {
		this.miembro2TareaC1 = miembro2TareaC1;
	}

	public Miembro getMiembro3TareaC1() {
		return miembro3TareaC1;
	}

	public void setMiembro3TareaC1(Miembro miembro3TareaC1) {
		this.miembro3TareaC1 = miembro3TareaC1;
	}

	public List<Miembro> getListaTareaC1() {
		return listaTareaC1;
	}

	public void setListaTareaC1(List<Miembro> listaTareaC1) {
		this.listaTareaC1 = listaTareaC1;
	}

	public List<Miembro> getListaConTodosLosMiembros() {
		return listaConTodosLosMiembros;
	}

	public void setListaConTodosLosMiembros(List<Miembro> listaConTodosLosMiembros) {
		this.listaConTodosLosMiembros = listaConTodosLosMiembros;
	}




}
