package proyectoYSistema;


import java.util.LinkedList;
import java.util.List;

import usuarioMiembroYFecha.*;



public class Sistema {
	
	
		private static Sistema instance = null;
		private List<Proyecto> proyectos;
		private List<Usuario> usuarios;
		
		// constructor del singleton //
		private Sistema() {
			this.proyectos= new LinkedList<Proyecto>();
			this.usuarios = new LinkedList<Usuario>();
		}
		
		public static Sistema GetInstance() {
			
			if (instance == null)
			{instance = new Sistema();}
			
			return instance;
		}

		public List<Proyecto> getProyectos() {
			
			return this.proyectos;
		}

		public List<Usuario> getUsuarios() {
			return usuarios;
		}

		public void setUsuarios(List<Usuario> usuarios) {
			this.usuarios = usuarios;
		}

		public void setProyectos(List<Proyecto> proyectos) {
			this.proyectos = proyectos;
		}

		
}
