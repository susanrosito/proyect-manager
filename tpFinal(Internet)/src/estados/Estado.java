package estados;

import tareas.TareaSimple;

public abstract class Estado {
	
	public void enTrabajo(TareaSimple unaTarea){}
	
	public void iniciada(TareaSimple unaTarea){}
	
	public void cerrada(TareaSimple unaTarea)	
	{unaTarea.setEstado(Cerrada.GetInstance());}
	
	public void finalizada(TareaSimple unaTarea){}
	
	public void pausada(TareaSimple unaTarea){}
	
    protected void creada(TareaSimple unaTarea){}

}
