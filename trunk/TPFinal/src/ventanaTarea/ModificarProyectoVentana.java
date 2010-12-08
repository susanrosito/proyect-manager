package ventanaTarea;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import proyectoYSistema.Proyecto;

public class ModificarProyectoVentana extends JFrame {

	private JButton administrarTareasProyecto = new JButton(
			"Administrar Tareas Del Proyecto");
	private JButton volver = new JButton("volver");
	private Proyecto proyectoActual;

	public ModificarProyectoVentana(Proyecto proyecto) {

		this.proyectoActual = proyecto;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		inicializarVentana();
		
	}

	public void inicializarVentana() {

		administrarTareasProyecto.addActionListener(new AdministrarTareas());
		volver.addActionListener(new VolverAtras());
		this.add(administrarTareasProyecto);
		this.add(volver);

		this.setLayout(new GridLayout(2, 2));

		pack();
		setVisible(true);

	}

	class AdministrarTareas implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			new AdministradorDeTareas(proyectoActual, null);

		}

	}

	class VolverAtras implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			dispose();

		}

	
	}


}
