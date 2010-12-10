package ventanaTarea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

import proyectoYSistema.Sistema;


import usuarioMiembroYFecha.Usuario;



public class VentanaCrearNuevoUsuario extends VentanaConfirmacion {

	private JPanel ventanaActual;
	private Sistema sistema;
	private JPanel panel;
	
	public VentanaCrearNuevoUsuario(Sistema sist) {
		
		this.labelTexto1.setText("Nombre Usuario");
		this.labelTexto2.setText("Email Usuario");
		sistema = sist;
		//this.panel = panel;
	}
	

	
	public void onAceptar() {
		
		sistema.crearUnUsuario(campoTexto1.toString(), campoTexto2.toString());
		
	}
}
