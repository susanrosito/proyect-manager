package estados;


import tareas.TareaSimple;
import estados.Creada;
import estados.Iniciada;
import usuarioMiembroYFecha.Fecha;


public class TestCreada extends TestEstado {
	

	
	public void setUp()
	{
		this.setUnaInstancia(Creada.GetInstance());
		this.setOtraInstancia(Creada.GetInstance());
		this.setTarea(new TareaSimple("Desarrollador", "Desarrollar TP", new Fecha("20090617")));
		this.getTarea().setEstado(Creada.GetInstance());
		this.setEstado(Creada.GetInstance());
	}


	public void testIniciada(TareaSimple unaTarea){
		Iniciada esperado = Iniciada.GetInstance();
		 this.getEstado().iniciada(this.getTarea());
			
			assertSame(esperado, this.getTarea().getEstado());
			assertNotSame(this.getEstado(), this.getTarea().getEstado());
	}

}
