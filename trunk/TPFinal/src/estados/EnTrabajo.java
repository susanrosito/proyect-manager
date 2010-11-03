package estados;

import tareas.TareaSimple;

public class EnTrabajo extends Estado {

	private static EnTrabajo instance = null;
	 
	   private EnTrabajo() {}
	 
	   public static EnTrabajo GetInstance()
	   {
	     if (instance == null)
	        instance = new EnTrabajo();
	 
	     return instance;
	   }
	   
	   public void pausada(TareaSimple unaTarea)
		{
			
			unaTarea.setEstado(Pausada.GetInstance());
	    }
	   
  public void finalizada(TareaSimple unaTarea){
		   
		   unaTarea.setEstado(Finalizada.GetInstance());
		   
	   }
	   
  public boolean verificarSiEstaEnTrabajo()
  { return true; }
  
  public String toString(){
	   return "En Trabajo";
  }
}
