package usuarioMiembroYFecha;

/**
 * Esta clase va a representar a todas las personas que estan en el sistema
 * por medio de un String que va a ser el nombre y de otro String que va a ser
 * el Email. Asumimos que dos personas no van a tener el mismo Email.
 */
public class Usuario {

	private String nombre;
	private String email;

	/**
	 * El costructor recibe dos String, el primero es el que representa su
	 * nombre y el segundo es el que representa su casilla de Email.
	 * @param unNombre
	 * @param unEmail
	 */
	public Usuario(String unNombre, String unEmail) {
		this.nombre = unNombre;
		this.email = unEmail;

	}

	/**
	 * Redefinimos el Equals para poder comparar si dos usuarios son iguales
	 * basandonos en que dos usuarios diferentes NO pueden tener el mismo mail.
	 */
	public boolean equals(Usuario usuario)
	{
		return this.getEmail()==usuario.getEmail();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
