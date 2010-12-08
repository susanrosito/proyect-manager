package ventanaTarea;

import javax.swing.*;

import proyectoYSistema.*;

public class VentanaSistema extends JFrame {
	// ////// esta clase para armar las pesta�as JTabbedPane

	// el panel de las pesta�as
	private JTabbedPane panelConPestanias = new JTabbedPane();

	public VentanaSistema() {

		super("Sistema");
		Sistema sist = Sistema.GetInstance();
		panelConPestanias.addTab("Proyectos",
				new ProyectoVentana(sist.getProyectos()));
		panelConPestanias.addTab("Usuarios",
				new UsuarioVentana(sist.getUsuarios()));
		this.add(panelConPestanias);
		this.pack();
		this.setLocation(250, 150);
		this.setVisible(true);		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new VentanaSistema();
	}

}
