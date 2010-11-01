package usuarioMiembroYFecha;

public class Usuario {

	private String nombre;
	private String email;

	public Usuario(String unNombre, String unEmail) {
		this.nombre = unNombre;
		this.email = unEmail;

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
