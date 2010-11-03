package estados;

import tareas.TareaSimple;

/**
 * Esta es la clase que modela los aspectos generales de los estados por los cuales
 * transita una tarea. Es abstracta y tiene todos los metods que tienen definidos sus
 * sub clases.
 */
public abstract class Estado {
	
	/**
	 * Este metodo cambia el estado si es posible a enTrabajo.
	 * Si no lo es, lanza una excepcción.
	 * @throws NoPuedeCambiarseElEstadoExcepccion 
	 */
	public void enTrabajo(TareaSimple unaTarea) throws NoPuedeCambiarseElEstadoExcepccion  
	{ throw new NoPuedeCambiarseElEstadoExcepccion(); }
	
	/**
	 * Este metodo cambia el estado si es posible a iniciada.
	 * Si no lo es, lanza una excepcción.
	 * @throws NoPuedeCambiarseElEstadoExcepccion 
	 */
	public void iniciada(TareaSimple unaTarea)throws NoPuedeCambiarseElEstadoExcepccion  
	{ throw new NoPuedeCambiarseElEstadoExcepccion(); }
	
	/**
	 * Este metodo cambia el estado a cerrada.
	 * Ya que siempre es posible hacerlo, nunca genera ninguna excepcción.
	 */
	public void cerrada(TareaSimple unaTarea)	
	{unaTarea.setEstado(Cerrada.GetInstance());}
	
	/**
	 * Este metodo cambia el estado si es posible a finalizada.
	 * Si no lo es, lanza una excepcción.
	 * @throws NoPuedeCambiarseElEstadoExcepccion 
	 */
	public void finalizada(TareaSimple unaTarea) 
	{ unaTarea.setEstado(Finalizada.GetInstance()); }
	
	/**
	 * Este metodo cambia el estado si es posible a pausada.
	 * Si no lo es, lanza una excepcción.
	 * @throws NoPuedeCambiarseElEstadoExcepccion 
	 */
	public void pausada(TareaSimple unaTarea)throws NoPuedeCambiarseElEstadoExcepccion  
	{ throw new NoPuedeCambiarseElEstadoExcepccion(); }
	
	/**
	 * Este metodo cambia el estado si es posible a creada.
	 * Si no lo es, lanza una excepcción.
	 * @throws NoPuedeCambiarseElEstadoExcepccion 
	 */
    protected void creada(TareaSimple unaTarea)throws NoPuedeCambiarseElEstadoExcepccion  
	{ throw new NoPuedeCambiarseElEstadoExcepccion(); }

    /**
     * Este metodo retorna un boleano indicando si la tarea
     * esta pausada.
     */
    public boolean verificarSiEstaPausada()
    { return false; }
    
    /**
     * Este metodo retorna un boleano indicando si la tarea
     * esta iniciada.
     */
    public boolean verificarSiEstaIniciada()
    { return false; }
    
    /**
     * Este metodo retorna un boleano indicando si la tarea
     * esta cerrada.
     */
    public boolean verificarSiEstaCerrada()
    { return false; }
    
    /**
     * Este metodo retorna un boleano indicando si la tarea
     * esta enTrabajo.
     */
    public boolean verificarSiEstaEnTrabajo()
    { return false; }
    
    /**
     * Este metodo retorna un boleano indicando si la tarea
     * esta finalizada.
     */
    public boolean verificarSiEstaFinalizada()
    { return false; }
    
    /**
     * Este metodo retorna un boleano indicando si la tarea
     * esta creada.
     */
    public boolean verificarSiEstaCreada()
    { return false; }
    

	
	
	


}
