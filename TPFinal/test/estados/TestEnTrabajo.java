package estados;

import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import estados.EnTrabajo;

import estados.Finalizada;
import estados.Pausada;

public class TestEnTrabajo extends TestEstado {

		
		
		public void setUp()
		{
			super.setUp();
			this.setUnaInstancia(EnTrabajo.GetInstance());
			this.setOtraInstancia(EnTrabajo.GetInstance());
			this.setEstado(EnTrabajo.GetInstance());
			this.setEstadoQueNoDebeCambiar(EnTrabajo.GetInstance());
		}

		public void testPausada() throws NoPuedeCambiarseElEstadoExcepccion {
			
			this.getTarea().setEstado(Pausada.GetInstance());           
			replay(this.getTarea());               
			
			this.getEstado().pausada(this.getTarea()); 
			
			verify(this.getTarea());  }
			
		
	    public void testFinalizada() throws NoPuedeCambiarseElEstadoExcepccion 	
		{
	    	this.getTarea().setEstado(Finalizada.GetInstance());           
		    replay(this.getTarea());               
		
		    this.getEstado().finalizada(this.getTarea()); 
		
		    verify(this.getTarea());;
		}
		
	    public void testVerificarSiEstaEnTrabajo()
	    { assertTrue(this.getEstado().verificarSiEstaEnTrabajo()); }
}	