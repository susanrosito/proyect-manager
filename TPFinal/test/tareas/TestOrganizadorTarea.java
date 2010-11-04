package tareas;

import static org.easymock.EasyMock.createMock;
import estados.Cerrada;
import estados.EnTrabajo;

import static org.easymock.EasyMock.*;

import junit.framework.TestCase;


public class TestOrganizadorTarea extends TestCase{

	protected TareaSimple tareaS;
	protected TareaCompuesta tareaC;
	
	protected TareaSimple tareaAnterior;
	protected OrganizadorTarea orgConTareaC;
	protected OrganizadorTarea orgConTareaS;
	
	protected String stringMotivo;
	
	
	public void setUp()
	{
		this.tareaAnterior= createMock(TareaSimple.class);
		this.tareaS= createMock(TareaSimple.class);
		this.tareaC= createMock(TareaCompuesta.class);	
		
		this.orgConTareaC= new OrganizadorTarea(this.tareaAnterior, this.tareaC );
		this.orgConTareaS= new OrganizadorTarea(this.tareaAnterior, this.tareaS );
		
		this.stringMotivo= "Se re abre la tarea porque...";
		
	}
	
	public void tearDown(){}
	
	public void testPuedeTrabajarseEnEllaVerdadero()
	{
		expect(this.getTareaAnterior().verificarSiEstaFinalizada()).andReturn(true);     
		replay(this.getTareaAnterior());
	      assertTrue("Este test corrobora que el metodo si fije si la tarea anterior " +
	      		"realmente esta finalizada y debe dar true.",this.getOrgConTareaS().puedeTrabajarseEnElla());
	
	}
	
	public void testPuedeTrabajarseEnEllaFalso()
	{
		expect(this.getTareaAnterior().verificarSiEstaFinalizada()).andReturn(false);     
		replay(this.getTareaAnterior());
	      assertFalse("Este test corrobora que el metodo si fije si la tarea anterior " +
		      		"realmente esta finalizada y debe dar false.",this.getOrgConTareaS().puedeTrabajarseEnElla());
	
	}
	
	public void testCerrateTareaSimple() {
		
		this.getTareaS().cerrate();           
        replay(this.getTareaS());               
	
       this.getOrgConTareaS().cerrate(); 

       verify(this.getTareaS());;
       }
		
   public void testCerrateTareaCompuesta() {
		
		this.getTareaC().cerrate();           
        replay(this.getTareaC());               
	
       this.getOrgConTareaC().cerrate(); 

       verify(this.getTareaC());;
       }

	
	public void testEstaEnTiempoTareaSimple() {
		
		expect(this.getTareaS().estaEnTiempo()).andReturn(true);     
		replay(this.getTareaS());
	      assertTrue("Se fija que el metodo retorne exactamente lo que retornaria" +
	      		"la tarea el enviarle el mismo metodo.",this.getOrgConTareaS().estaEnTiempo());
	       
	}

	
	public void testEstaEnTiempoTareaCompuesta() {
		
		
		expect(this.getTareaC().estaEnTiempo()).andReturn(true);     
		replay(this.getTareaC());             
	      assertTrue(this.getOrgConTareaC().estaEnTiempo()); 

          
       
       
}
	public void testEstaProximaAVencerTareaSimple() {
		expect(this.getTareaS().estaProximaAVencer()).andReturn(false);     
		replay(this.getTareaS());
	      assertFalse(this.getOrgConTareaS().estaProximaAVencer());
	}
	
	public void testEstaProximaAVencerTareaCompuesta() {
		expect(this.getTareaC().estaProximaAVencer()).andReturn(false);     
		replay(this.getTareaC());
	      assertFalse(this.getOrgConTareaC().estaProximaAVencer());
	}

	
	public void testReAbriteTareaSimple() {
		
		this.getTareaS().reAbrite(stringMotivo);           
        replay(this.getTareaS());               
	
       this.getOrgConTareaS().reAbrite(stringMotivo); 

       verify(this.getTareaS());;
       }
		
   public void testReAbriteTareaCompuesta() {
		
		this.getTareaC().reAbrite(stringMotivo);           
        replay(this.getTareaC());               
	
       this.getOrgConTareaC().reAbrite(stringMotivo); 

       verify(this.getTareaC());;
       }
	
	public void testTieneOrden() {
		
		assertTrue("Se fija que devuelva true cuando contiene una tarea simple.",this.getOrgConTareaS().tieneOrden());
		assertTrue("Se fija que devuelva true cuando contiene una tarea compuesta.",this.getOrgConTareaS().tieneOrden());
	}

	
	public void testVerEstadoTareaSimple() {
		
		expect(this.getTareaS().verEstado()).andReturn(Cerrada.GetInstance().toString());     
		replay(this.getTareaS());
	      assertSame("Se fija que realmente retorne el estado que tiene la tarea dentro del organizador, " +
	      		"en este caso: Cerrada", this.getOrgConTareaS().verEstado(), Cerrada.GetInstance().toString());
		
	}

      public void testVerEstadoTareaCompuesta() {
		
		expect(this.getTareaC().verEstado()).andReturn(EnTrabajo.GetInstance().toString());     
		replay(this.getTareaC());
	      assertSame("Se fija que realmente retorne el estado que tiene la tarea dentro del organizador, " +
	      		"en este caso: EnTrabajo", this.getOrgConTareaC().verEstado(), EnTrabajo.GetInstance().toString());
		
	}
	
      
      public TareaSimple getTareaS() {
		return tareaS;
	}

	public void setTareaS(TareaSimple tareaS) {
		this.tareaS = tareaS;
	}

	public TareaCompuesta getTareaC() {
		return tareaC;
	}

	public void setTareaC(TareaCompuesta tareaC) {
		this.tareaC = tareaC;
	}

	public OrganizadorTarea getOrgConTareaC() {
		return orgConTareaC;
	}

	public void setOrgConTareaC(OrganizadorTarea orgConTareaC) {
		this.orgConTareaC = orgConTareaC;
	}

	public OrganizadorTarea getOrgConTareaS() {
		return orgConTareaS;
	}

	public void setOrgConTareaS(OrganizadorTarea orgConTareaS) {
		this.orgConTareaS = orgConTareaS;
	}

	public TareaSimple getTareaAnterior() {
		return tareaAnterior;
	}

	public void setTareaAnterior(TareaSimple tareaAnterior) {
		this.tareaAnterior = tareaAnterior;
	}

	

	
	
}
