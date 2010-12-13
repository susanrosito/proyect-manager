package ventanaTarea;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import proyectoYSistema.Proyecto;
import proyectoYSistema.Sistema;
import proyectoYSistema.UsuarioYaTieneRolExepcion;
import usuarioMiembroYFecha.Miembro;
import usuarioMiembroYFecha.Usuario;

public class ModificarProyectoVentana extends JFrame implements
		InterfaceObserversSistema, InterfaceObserversProyecto {

	// el proyecto actual
	private Proyecto proyectoActual;
	// las listas de miembros y usuarios
	JList jlistMiembro = new JList();
	JList jlistUsuario = new JList();
	private JScrollPane scroll = new JScrollPane();

	// panel de datos con los campos de texto
	private JPanel panelDatos = new JPanel();
	private TextField textoNombreMiembro = new TextField();
	private TextField textoEmailMiembro = new TextField();
	private TextField textoRolMiembro = new TextField();

	private JLabel labelNombreMiembro = new JLabel("Nombre Miembro");
	private JLabel labelEmailMiembro = new JLabel("Email Miembro");
	private JLabel labelRolMiembro = new JLabel("Rol Miembro");

	// botones y los paneles que los contienen
	private JButton administrarTareasProyecto = new JButton(
			"Administrar Tareas Del Proyecto");
	private JButton volver = new JButton("volver");

	private JPanel panelBotonesInferiores = new JPanel();

	private JButton crearMiembro = new JButton("Crear Miembro");
	private JButton eliminarMiembro = new JButton("Eliminar Miembro");

	private JPanel panelBotonesEntreListas = new JPanel();

	/**
	 * constructor de la clase recibe como parametro un proyecto, el cual es el
	 * seleccionado de la jlist de los proyectos y un sistema al cual la
	 * instancia de esta clase se agrega como observador
	 */
	public ModificarProyectoVentana(Proyecto proyecto,
			Vector<Usuario> listaUsuarios) {
		super("proyecto actual");
		this.proyectoActual = proyecto;
		this.jlistMiembro.setListData(proyecto.getListaDeMiembros());
		this.jlistUsuario.setListData(listaUsuarios);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		inicializarVentana();

	}

	/**
	 * es el metodo que inicializa la ventana
	 */
	public void inicializarVentana() {
		// lista, agregarle el scroll y el color de fondo

		scroll.setViewportView(jlistMiembro);
		this.jlistMiembro.setBackground(Color.ORANGE);
		scroll.setViewportView(jlistUsuario);
		this.jlistUsuario.setBackground(Color.ORANGE);

		// botones
		panelBotonesInferiores.setLayout(new BoxLayout(panelBotonesInferiores,
				BoxLayout.Y_AXIS));
		panelBotonesInferiores.add(administrarTareasProyecto);
		panelBotonesInferiores.add(volver);

		// ///////
		panelBotonesEntreListas.add(crearMiembro);

		panelBotonesEntreListas.add(eliminarMiembro);

		// /////
		panelDatos.add(labelNombreMiembro);
		panelDatos.add(textoNombreMiembro);
		panelDatos.add(labelEmailMiembro);
		panelDatos.add(textoEmailMiembro);
		panelDatos.add(labelRolMiembro);
		panelDatos.add(textoRolMiembro);
		panelDatos.setLayout(new GridLayout(6, 2));

		textoNombreMiembro.setEditable(false);
		textoEmailMiembro.setEditable(false);
		// //
		panelBotonesEntreListas.setLayout(new BoxLayout(
				panelBotonesEntreListas, BoxLayout.Y_AXIS));

		administrarTareasProyecto.addActionListener(new AdministrarTareas());
		// asignar acciones a los botones
		crearMiembro.addActionListener(new CrearMiembro());
		// modificarMiembro.addActionListener(l);
		eliminarMiembro.addActionListener(new EliminarMiembro());

		volver.addActionListener(new VolverAtras());

		this.add(jlistMiembro);
		this.add(panelBotonesEntreListas);
		this.add(jlistUsuario);
		this.add(panelDatos);
		this.add(panelBotonesInferiores);

		this.setLayout(new GridLayout(2, 3));

		pack();
		setVisible(true);

	}

	class CrearMiembro implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Usuario user = (Usuario) jlistUsuario.getSelectedValue();
			try {
				proyectoActual.agregarMiembro(user, textoRolMiembro.getText());
			} catch (UsuarioYaTieneRolExepcion e1) {

				e1.printStackTrace();
			}
			proyectoActual.notificarObservadores();
		}

	}

	class EliminarMiembro implements ActionListener {

		public void actionPerformed(ActionEvent e) {

		}

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

	public void actualizarObservadores(Sistema sistema) {
		jlistUsuario.setListData(sistema.getUsuarios());

	}

	public void actualizarObservadores(Proyecto proyecto) {
		jlistMiembro.setListData(proyecto.getListaDeMiembros());

	}

}
