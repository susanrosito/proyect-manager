package estados;

import tareas.TareaSimple;
import usuarioMiembroYFecha.Fecha;
import estados.EnTrabajo;
import estados.Estado;
import estados.Finalizada;
import estados.Pausada;

public class TestEnTrabajo extends TestEstado {

		
		
		public void setUp()
		{
			this.setUnaInstancia(EnTrabajo.GetInstance());
			this.setOtraInstancia(EnTrabajo.GetInstance());
			this.setTarea(new TareaSimple("Desarrollador", "Desarrollar TP",new Fecha("20090617")));
			this.getTarea().setEstado(EnTrabajo.GetInstance());
			this.setEstado(EnTrabajo.GetInstance());
		}

		public void testPausada(TareaSimple unaTarea){
			Pausada esperado = Pausada.GetInstance();
			 this.getEstado().pausada(this.getTarea());
				
				assertSame(esperado, this.getTarea().getEstado());
				assertNotSame(this.getEstado(), this.getTarea().getEstado());
		}
		
	    public void testFinalizada(TareaSimple unaTarea)	
		{Estado esperado = Finalizada.GetInstance();
		this.getEstado().finalizada(this.getTarea());
			
			assertSame(esperado, this.getTarea().getEstado());
			assertNotSame(esperado, this.getEstado());
		}
		
}	