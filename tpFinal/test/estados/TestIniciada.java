package estados;

import tareas.TareaSimple;
import usuarioMiembroYFecha.Fecha;
import estados.EnTrabajo;
import estados.Estado;
import estados.Finalizada;
import estados.Iniciada;






public class TestIniciada extends TestEstado {
	
	
	
	public void setUp()
	{
		this.setUnaInstancia(Iniciada.GetInstance());
		this.setOtraInstancia(Iniciada.GetInstance());;
		this.setTarea(new TareaSimple("Desarrollador", "Desarrollar TP", new Fecha("20090617")));
		this.getTarea().setEstado(Iniciada.GetInstance());
		this.setEstado(Iniciada.GetInstance());
	}

	public void testEnTrabajo(TareaSimple unaTarea){
		EnTrabajo esperado = EnTrabajo.GetInstance();
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
