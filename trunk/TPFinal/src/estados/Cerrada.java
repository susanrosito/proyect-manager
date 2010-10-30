package estados;

public class Cerrada extends Estado {
	
	private static Cerrada instance = null;
	 
	   private Cerrada() {}
	 
	   public static Cerrada GetInstance()
	   {
	     if (instance == null)
	        instance = new Cerrada();
	 
	     return instance;
	   }
	
	   public boolean verificarSiEstaCerrada()
	    { return true; }

		   
	}
	   