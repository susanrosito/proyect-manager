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
	
	
	protected TareaSimple TSConEstadoQueNoCambia;
	protected TareaSimple tarea;

	public void setUp() {
		this.tarea = createMock(TareaSimple.class);
		this.TSConEstadoQueNoCambia = createMock(TareaSimple.class);
				
			}
	/**
	 * Testea que ambas instancias del estado  sean las mismas ya que es
	 * un singleton.
	 */
		
	public void testConstructor (){
		
		
		Assert.assertSame(this.getUnaInstancia(), this.getOtraInstancia());
		Assert.assertSame(this.getOtraInstancia(), this.getUnaInstancia());
		
	}
	
	/**
	 * Este test comprueba que cuando se le envie el mensaje enTrabajo()
	 * a un estado, si puede cambiarse lo haga y si no lance una  NoPuedeCambiarseElEstadoExcepccion.
	 */
	public void testEnTrabajo()throws NoPuedeCambiarseElEstadoExcepccion  {
		
		
		expect(TSConEstadoQueNoCambia.getEstado()).andReturn(this.estado);
		replay(TSConEstadoQueNoCambia); 
		
		
		 try {
		        this.getEstado().enTrabajo(TSConEstadoQueNoCambia);	
		        fail("No capto la excepccion :(");
		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		       
		    }
	}
	
	/**
	 * Este test comprueba que cuando se le envie el mensaje iniciada()
	 * a un estado, si puede cambiarse lo haga y si no lance una NoPuedeCambiarseElEstadoExcepccion.
	 */
	public void testIniciada()throws NoPuedeCambiarseElEstadoExcepccion  {
		

		expect(TSConEstadoQueNoCambia.getEstado()).andReturn(this.estado);
		replay(TSConEstadoQueNoCambia); 
		
		
		 try {
		        this.getEstado().iniciada(TSConEstadoQueNoCambia);	
		        fail("No capto la excepccion :(");		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		      
		    }
	}
	
	/**
	 * Este test comprueba que cuando se le envie el mensaje cerrada()
	 * a un estado, si puede cambiarse lo haga y si no lance una NoPuedeCambiarseElEstadoExcepccion.
	 */
	public void testCerrada ()	throws NoPuedeCambiarseElEstadoExcepccion 
	{
		this.getTarea().setEstado(Cerrada.GetInstance());           
        replay(this.getTarea());               
	
       this.getEstado().cerrada(this.getTarea()); 

       verify(this.getTarea());;}
	
	/**
	 * Este test comprueba que cuando se le envie el mensaje finalizada()
	 * a un estado, si puede cambiarse lo haga y si no lance una NoPuedeCambiarseElEstadoExcepccion.
	 */
	public void testFinalizada()throws NoPuedeCambiarseElEstadoExcepccion {
		this.getTarea().setEstado(Finalizada.GetInstance());           
        replay(this.getTarea());               
	
       this.getEstado().finalizada(this.getTarea()); 

       verify(this.getTarea());;
       }
	
	/**
	 * Este test comprueba que cuando se le envie el mensaje pausada()
	 * a un estado, si puede cambiarse lo haga y si no lance una NoPuedeCambiarseElEstadoExcepccion.
	 */
	public void testPausada()throws NoPuedeCambiarseElEstadoExcepccion {
				
		

		expect(TSConEstadoQueNoCambia.getEstado()).andReturn(this.estado);
		replay(TSConEstadoQueNoCambia); 
		
		
		 try {
		        this.getEstado().pausada(TSConEstadoQueNoCambia);	
		        fail("No capto la excepccion :(");		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		       
		    }
	}
	
	/**
	 * Este test comprueba que cuando se le envie el mensaje creada()
	 * a un estado, si puede cambiarse lo haga y si no lance una NoPuedeCambiarseElEstadoExcepccion.
	 */
	public void testCreada()throws NoPuedeCambiarseElEstadoExcepccion {

		expect(TSConEstadoQueNoCambia.getEstado()).andReturn(this.estado);
		replay(TSConEstadoQueNoCambia); 
		
		
		 try {
		        this.getEstado().creada(TSConEstadoQueNoCambia);	
		        fail("No capto la excepccion :(");		    }
		    catch (Exception NoPuedeCambiarseElEstadoExcepccion) {
		       
		    }
	}

	/**
	 * Este test verifica que el mensaja verificarSiEstaPausada() devuelva true en la clase Pausada
	 * y false en el resto.
	 */
	public void testVerificarSiEstaPausada()
    { assertFalse(this.getEstado().verificarSiEstaPausada()); }
		
	/**
	 * Este test verifica que el mensaja testVerificarSiEstaIniciada() devuelva true en la clase Iniciada
	 * y false en el resto.
	 */
	public void testVerificarSiEstaIniciada()
    { assertFalse(this.getEstado().verificarSiEstaIniciada()); }
    
	/**
	 * Este test verifica que el mensaja verificarSiEstaCerrada() devuelva true en la clase Cerrada
	 * y false en el resto.
	 */
	public void testVerificarSiEstaCerrada()
    { assertFalse(this.getEstado().verificarSiEstaCerrada()); }
    
	/**
	 * Este test verifica que el mensaja verificarSiEstaEnTrabajo() devuelva true en la clase EnTrabajo
	 * y false en el resto.
	 */
	public void testVerificarSiEstaEnTrabajo()
    { assertFalse(this.getEstado().verificarSiEstaEnTrabajo()); }
    
	/**
	 * Este test verifica que el mensaja testVerificarSiEstaFinalizada() devuelva true en la clase Finalizada
	 * y false en el resto.
	 */
	public void testVerificarSiEstaFinalizada()
    { assertFalse(this.getEstado().verificarSiEstaFinalizada()); }
    
	/**
	 * Este test verifica que el mensaja testVerificarSiEstaCreada() devuelva true en la clase Creada
	 * y false en el resto.
	 */
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


	public TareaSimple getTSConEstadoQueNoCambia() {
		return TSConEstadoQueNoCambia;
	}

	public void setTSConEstadoQueNoCambia(TareaSimple tSConEstadoQueNoCambia) {
		TSConEstadoQueNoCambia = tSConEstadoQueNoCambia;
	}

	
}
