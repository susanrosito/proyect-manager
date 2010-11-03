package estados;

public class Finalizada extends Estado {

	private static Finalizada instance = null;
	 
	   private Finalizada() {}
	 
	   public static Finalizada GetInstance()
	   {
	     if (instance == null)
	        instance = new Finalizada();
	 
	     return instance;
	   }
	   public boolean verificarSiEstaFinalizada()
	    { return true; }

	   public String toString(){
		   return "Finalizada";
	   }
}
