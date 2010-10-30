package estados;



import tareas.TareaSimple;
import static org.easymock.EasyMock.*;
import estados.Cerrada;
import estados.Estado;


import junit.framework.Assert;
import junit.framework.TestCase;

    public class TestEstado extends TestCase {
	private Estado unaInstancia;
	private Estado otraInstancia;
	protected Estado estado;
	protected Estado estadoQueNoDebeCambiar;
	
	protected TareaSimple TSConEstadoQueNoCambia;
	protected TareaSimple tarea;

	public void setUp() {
		this.tarea = createMock(TareaSimple.class);
		this.TSConEstadoQueNoCambia = createMock(TareaSimple.class);
				
			}
	
	public void testConstructor (){
		
		//Testea que ambas instancias de Cerrada sean las mismas ya que es
		//un singleton.
		Assert.assertSame(this.getUnaInstancia(), this.getOtraInstancia());
		Assert.assertSame(this.getOtraInstancia(), this.getUnaInstancia());
		
	}
	
	//@(expected = NoPuedeCambiarseElEstadoExcepccion.class)  
	public void testEnTrabajo()throws NoPuedeCambiarseElEstadoExcepccion  {
		
		
		expect(TSConEstadoQueNoCambia.getEstado()).andReturn(this.estado);
		replay(TSConEstadoQueNoCambia); 
		
		
		 try {
		        this.getEstado().enTrabajo(TSConEstadoQueNoCambia);	
		        fail("No exception caught :(");
		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		       // assertEquals(this.getTSConEstadoQueNoCambia().getEstado()., NoPuedeCambiarseElEstadoExcepccion.getCause().getClass());
		        //assertEquals("Message",NoPuedeCambiarseElEstadoExcepccion.getMessage());
		    }
	}
	
	public void testIniciada()throws NoPuedeCambiarseElEstadoExcepccion  {
		

		expect(TSConEstadoQueNoCambia.getEstado()).andReturn(this.estado);
		replay(TSConEstadoQueNoCambia); 
		
		
		 try {
		        this.getEstado().iniciada(TSConEstadoQueNoCambia);	
		        fail("No exception caught :(");
		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		       // assertEquals(this.getTSConEstadoQueNoCambia().getEstado()., NoPuedeCambiarseElEstadoExcepccion.getCause().getClass());
		        //assertEquals("Message",NoPuedeCambiarseElEstadoExcepccion.getMessage());
		    }
	}
	
	public void testCerrada ()	throws NoPuedeCambiarseElEstadoExcepccion 
	{
		this.getTarea().setEstado(Cerrada.GetInstance());           
        replay(this.getTarea());               
	
       this.getEstado().cerrada(this.getTarea()); 

       verify(this.getTarea());;}
	
	public void testFinalizada()throws NoPuedeCambiarseElEstadoExcepccion {

		expect(TSConEstadoQueNoCambia.getEstado()).andReturn(this.estado);
		replay(TSConEstadoQueNoCambia); 
		
		
		 try {
		        this.getEstado().finalizada(TSConEstadoQueNoCambia);	
		        fail("No exception caught :(");
		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		       // assertEquals(this.getTSConEstadoQueNoCambia().getEstado()., NoPuedeCambiarseElEstadoExcepccion.getCause().getClass());
		        //assertEquals("Message",NoPuedeCambiarseElEstadoExcepccion.getMessage());
		    }
	}
	
	public void testPausada()throws NoPuedeCambiarseElEstadoExcepccion {
				
		

		expect(TSConEstadoQueNoCambia.getEstado()).andReturn(this.estado);
		replay(TSConEstadoQueNoCambia); 
		
		
		 try {
		        this.getEstado().pausada(TSConEstadoQueNoCambia);	
		        fail("No exception caught :(");
		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		       // assertEquals(this.getTSConEstadoQueNoCambia().getEstado()., NoPuedeCambiarseElEstadoExcepccion.getCause().getClass());
		        //assertEquals("Message",NoPuedeCambiarseElEstadoExcepccion.getMessage());
		    }
	}
	
	public void testCreada()throws NoPuedeCambiarseElEstadoExcepccion {

		expect(TSConEstadoQueNoCambia.getEstado()).andReturn(this.estado);
		replay(TSConEstadoQueNoCambia); 
		
		
		 try {
		        this.getEstado().creada(TSConEstadoQueNoCambia);	
		        fail("No exception caught :(");
		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		       // assertEquals(this.getTSConEstadoQueNoCambia().getEstado()., NoPuedeCambiarseElEstadoExcepccion.getCause().getClass());
		        //assertEquals("Message",NoPuedeCambiarseElEstadoExcepccion.getMessage());
		    }
	}

	public void testVerificarSiEstaPausada()
    { assertFalse(this.getEstado().verificarSiEstaPausada()); }
    
	public void testVerificarSiEstaIniciada()
    { assertFalse(this.getEstado().verificarSiEstaIniciada()); }
    
	public void testVerificarSiEstaCerrada()
    { assertFalse(this.getEstado().verificarSiEstaCerrada()); }
    
	public void testVerificarSiEstaEnTrabajo()
    { assertFalse(this.getEstado().verificarSiEstaEnTrabajo()); }
    
	public void testVerificarSiEstaFinalizada()
    { assertFalse(this.getEstado().verificarSiEstaFinalizada()); }
    
	public void testVerificarSiEstaCreada()
    { assertFalse(this.getEstado().verificarSiEstaCreada()); }
	
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

	public Estado getEstadoQueNoDebeCambiar() {
		return estadoQueNoDebeCambiar;
	}

	public void setEstadoQueNoDebeCambiar(Estado estadoQueNoDebeCambiar) {
		this.estadoQueNoDebeCambiar = estadoQueNoDebeCambiar;
	}

	public TareaSimple getTSConEstadoQueNoCambia() {
		return TSConEstadoQueNoCambia;
	}

	public void setTSConEstadoQueNoCambia(TareaSimple tSConEstadoQueNoCambia) {
		TSConEstadoQueNoCambia = tSConEstadoQueNoCambia;
	}

	
}
