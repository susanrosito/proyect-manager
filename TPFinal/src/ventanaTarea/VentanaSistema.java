package ventanaTarea;

import java.awt.Color;

import javax.swing.*;

import proyectoYSistema.*;

public class VentanaSistema extends JFrame {
		// el panel de las pestanhas
	private JTabbedPane panelConPestanias = new JTabbedPane();
	private Sistema sistema;

	public VentanaSistema() {
			// es la ventana Inicial
		
		super("Sistema");
		sistema = Sistema.GetInstance();
		panelConPestanias.addTab("Proyectos", new ProyectoVentana(sistema));
		panelConPestanias.addTab("Usuarios", new UsuarioVentana(sistema));

		this.add(panelConPestanias);
		this.pack();
		this.setLocation(100, 150);
		this.setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
			
	}

	public static void main(String[] args) {
		new VentanaSistema();
	}

}
