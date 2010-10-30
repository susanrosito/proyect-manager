package estados;

import tareas.TareaSimple;
import usuarioMiembroYFecha.Fecha;
import estados.EnTrabajo;
import estados.Estado;
import estados.Finalizada;
import estados.Pausada;






public class TestPausada extends TestEstado {

	

	public void setUp()
	{

		this.setUnaInstancia(Pausada.GetInstance());
		this.setOtraInstancia(Pausada.GetInstance());;
		this.setTarea(new TareaSimple("Desarrollador", "Desarrollar TP",new Fecha("20090617")));
		this.getTarea().setEstado(Pausada.GetInstance());
		this.setEstado(Pausada.GetInstance());
	}

    public void testEnTrabajo(){
    	
    	EnTrabajo esperado = EnTrabajo.GetInstance();
   	    this.getEstado().enTrabajo(this.getTarea());
   		
   		assertSame(esperado, this.getTarea().getEstado());
   		assertNotSame(esperado, this.getEstado());
    }
	
    public void testFinalizada(TareaSimple unaTarea)	
	{Estado esperado = Finalizada.GetInstance();
	this.getEstado().finalizada(this.getTarea());
		
		assertSame(esperado, this.getTarea().getEstado());
		assertNotSame(esperado, this.getEstado());
	}
	
	

}
