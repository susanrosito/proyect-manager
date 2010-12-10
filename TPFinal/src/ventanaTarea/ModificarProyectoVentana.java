package ventanaTarea;

import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import proyectoYSistema.Proyecto;

public class ModificarProyectoVentana extends JFrame {

	//el proyecto actual 
	private Proyecto proyectoActual;
	//las listas de miembros y usuarios
	JList jlistMiembro = new JList();
	JList jlistUsuario = new JList();
	
	//panel de datos con los campos de texto
	private JPanel panelDatos = new JPanel();
	private TextField textoNombreMiembro= new TextField();
	private TextField textoEmailMiembro= new TextField();
	private TextField textoRolMiembro= new TextField();
	
	private JLabel labelNombreMiembro = new JLabel("Nombre Miembro");
	private JLabel labelEmailMiembro = new JLabel("Email Miembro");
	private JLabel labelRolMiembro = new JLabel("Rol Miembro");
	
	//botones y los  paneles que los contienen
	private JButton administrarTareasProyecto = new JButton("Administrar Tareas Del Proyecto");
	private JButton volver = new JButton("volver");

	private JPanel panelBotonesInferiores = new JPanel();
	
	private JButton crear= new JButton("Crear Miembro");
	private JButton modificar = new JButton("Modificar Miembro");
	private JButton eliminar = new JButton("Eliminar Miembro");
	
	private JPanel panelBotonesEntreListas = new JPanel();
	

	public ModificarProyectoVentana(Proyecto proyecto) {

		this.proyectoActual = proyecto;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		inicializarVentana();
		
	}

	public void inicializarVentana() {

		// botones 
		panelBotonesInferiores.setLayout(new BoxLayout(panelBotonesInferiores,BoxLayout.Y_AXIS));
		panelBotonesInferiores.add(administrarTareasProyecto);
		panelBotonesInferiores.add(volver);
		
		
		panelBotonesEntreListas.add(crear);
		panelBotonesEntreListas.add(modificar);
		panelBotonesEntreListas.add(eliminar);
		
		
		panelDatos.add(textoNombreMiembro);
		panelDatos.add(labelNombreMiembro);
		panelDatos.add(textoEmailMiembro);
		panelDatos.add(labelEmailMiembro);
		panelDatos.add(textoRolMiembro);
		panelDatos.add(labelRolMiembro);
		panelDatos.setLayout(new GridLayout(6, 2));
		
		panelBotonesEntreListas.setLayout(new BoxLayout(panelBotonesEntreListas, BoxLayout.Y_AXIS));
		
		administrarTareasProyecto.addActionListener(new AdministrarTareas());
		volver.addActionListener(new VolverAtras());
		
		this.add(panelBotonesEntreListas);
		this.add(panelDatos);
		this.add(panelBotonesInferiores);
		
		this.setLayout(new GridLayout(2, 3));

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
