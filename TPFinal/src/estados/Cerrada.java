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
	public void iniciada(TareaSimple tarea) {
		tarea.setEstado(Iniciada.GetInstance());
	}
}
