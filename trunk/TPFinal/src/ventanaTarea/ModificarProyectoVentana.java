package ventanaTarea;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
		this.proyectoActual.agregarObservador(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		inicializarVentana();

	}

	/**
	 * es el metodo que inicializa la ventana
	 */
	public void inicializarVentana() {
		// lista, agregarle el scroll y el color de fondo

		scroll.setViewportView(jlistMiembro);
		this.jlistUsuario.setCellRenderer(new UsuarioRenderer());
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

		this.add(jlistMiembro);
		this.add(panelBotonesEntreListas);
		this.add(jlistUsuario);
		this.add(panelDatos);
		this.add(panelBotonesInferiores);

		this.setLayout(new GridLayout(2, 3));

		addActions();

		pack();
		setVisible(true);

	}

	public void addActions() {

		// asignar acciones a los botones
		crearMiembro.addActionListener(new CrearMiembro());
		eliminarMiembro.addActionListener(new EliminarMiembro());
		volver.addActionListener(new VolverAtras());

		// asignar acciones a las JList
		jlistMiembro
				.addListSelectionListener(new SeleccionarElementoJListMiembro());
		jlistMiembro.addMouseListener(new DeseleccionarElementoJListMiembro());

		jlistUsuario
				.addListSelectionListener(new SeleccionarElementoJListUsuario());
		jlistUsuario.addMouseListener(new DeseleccionarElementoJListUsuario());
	}

	class SeleccionarElementoJListUsuario implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			try {

				if (!jlistMiembro.isSelectionEmpty()) {

					Usuario usuario = (Usuario) jlistUsuario.getSelectedValue();
					textoNombreMiembro.setText(usuario.getNombre());
					textoEmailMiembro.setText(usuario.getEmail());

				}

			} catch (NullPointerException ex) {
			}

		}
	}

	class DeseleccionarElementoJListUsuario implements MouseListener {

		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {
				jlistUsuario.clearSelection();
			}
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}

	}

	class SeleccionarElementoJListMiembro implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			try {

				if (!jlistMiembro.isSelectionEmpty()) {

					Miembro miembro = (Miembro) jlistMiembro.getSelectedValue();
					textoNombreMiembro
							.setText(miembro.getUsuario().getNombre());
					textoEmailMiembro.setText(miembro.getUsuario().getEmail());
					textoRolMiembro.setText(miembro.getRol());
				}

			} catch (NullPointerException ex) {
			}

		}
	}

	class DeseleccionarElementoJListMiembro implements MouseListener {

		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) {
				jlistMiembro.clearSelection();
				textoNombreMiembro.setText("");
				textoEmailMiembro.setText("");
				textoRolMiembro.setText("");
			}
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}

	}

	class CrearMiembro implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (!jlistUsuario.isSelectionEmpty()) {

				if (!textoRolMiembro.getText().isEmpty()) {
					Usuario user = (Usuario) jlistUsuario.getSelectedValue();
					try {
						proyectoActual.agregarMiembro(user, textoRolMiembro
								.getText());
						proyectoActual.notificarObservadores();
					} catch (UsuarioYaTieneRolExepcion e1) {

						JOptionPane
								.showMessageDialog(
										crearMiembro,
										"Ya existe un miembro creado a partir de ese usuario.",
										"Error", JOptionPane.WARNING_MESSAGE);

					}

				} else {
					JOptionPane.showMessageDialog(crearMiembro,
							"Te falta ingresar un rol.", "Error",
							JOptionPane.WARNING_MESSAGE);
				}

			} else {
				JOptionPane.showMessageDialog(crearMiembro,
						"Primero tenes que tener seleccionado un usuario.",
						"Error", JOptionPane.WARNING_MESSAGE);
			}

		}

	}

	class EliminarMiembro implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			proyectoActual.getListaDeMiembros().remove(
					jlistMiembro.getSelectedValue());
			proyectoActual.notificarObservadores();
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
