package estados;

import tareas.TareaSimple;


public class Creada extends Estado {
	
	private static Creada instance = null;
	 
	   private Creada() {}
	 
	   public static Creada GetInstance()
	   {
	     if (instance == null)
	        instance = new Creada();
	 
	     return instance;
	   }

	
	public void iniciada(TareaSimple unaTarea)
	{
		
		unaTarea.setEstado(Iniciada.GetInstance());
    }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
