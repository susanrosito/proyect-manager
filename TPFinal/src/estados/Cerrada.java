package estados;

import tareas.TareaSimple;

public class Cerrada extends Estado {
	
	private static Cerrada instance = null;
	 
	   private Cerrada() {}
	 
	   public static Cerrada GetInstance()
	   {
	     if (instance == null)
	        instance = new Cerrada();
	 
	     return instance;
	   }
 public void iniciada(TareaSimple unaTarea){
		   
		   unaTarea.setEstado(Iniciada.GetInstance());
		   
	   }
	   public boolean verificarSiEstaCerrada()
	    { return true; }
	  
	   public String toString(){
		   return "Cerrada";
	   }
		   
	}
	   