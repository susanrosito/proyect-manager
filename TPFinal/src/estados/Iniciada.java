package estados;

import tareas.TareaSimple;

public class Iniciada extends Estado {

	private static Iniciada instance = null;
	 
	   private Iniciada() {}
	 
	   public static Iniciada GetInstance()
	   {
	     if (instance == null)
	        instance = new Iniciada();
	 
	     return instance;
	   }


	   public void enTrabajo(TareaSimple unaTarea){
		   
		   unaTarea.setEstado(EnTrabajo.GetInstance());
		   
	   }
	   
  public void finalizada(TareaSimple unaTarea){
		   
		   unaTarea.setEstado(Finalizada.GetInstance());
		   
	   }
	   
  public boolean verificarSiEstaIniciada()
  { return true; } 
  
  public String toString(){
	   return "Iniciada";
  }
  
  
}