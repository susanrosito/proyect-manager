package estados;

import tareas.TareaSimple;

public class Pausada extends Estado {
	private static Pausada instance = null;
	 
	   private Pausada() {}
	 
	   public static Pausada GetInstance()
	   {
	     if (instance == null)
	        instance = new Pausada();
	 
	     return instance;
	   }

	   public void enTrabajo(TareaSimple unaTarea){
		   
		   unaTarea.setEstado(EnTrabajo.GetInstance());
		   
	   }
	   
  public void finalizada(TareaSimple unaTarea){
		   
		   unaTarea.setEstado(Finalizada.GetInstance());
		   
	   }
  public boolean verificarSiEstaPausada()
  { return true; }
}

