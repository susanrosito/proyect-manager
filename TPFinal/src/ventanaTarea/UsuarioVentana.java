package ventanaTarea;

import java.awt.Checkbox;
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import proyectoYSistema.Sistema;

import usuarioMiembroYFecha.Usuario;

public class UsuarioVentana extends JPanel implements InterfaceObserversSistema{

	// las variables que se encargan de manejar la lista
	private Vector<Usuario> listaUsuarioVector = new Vector<Usuario>();
	private JList listaUsuarioJList;
	private JScrollPane scrollUsuario = new JScrollPane();
	
	//el sistema
	private Sistema sistema;

	// el panel de datos del Usuario con los Jlabels y los text fields
	private JPanel panelDatos = new JPanel();
	private TextField textoNombreUsuario = new TextField();
	private TextField textoEmailUsuario = new TextField();
	private JLabel labelNombreUsuario = new JLabel("Nombre Del Usuario Actual");
	private JLabel labelEmailUsuario = new JLabel("Email Del Usuario Actual");

	// botones de la pestanha Usuario
	private JPanel panelBotones = new JPanel();
	private JButton crear = new JButton("Crear Usuario");
	private JButton modificar = new JButton("Modificar Usuario");
	private JButton eliminar = new JButton("Eliminar Usuario");
/**
 * Contructor de la ventana,requiere de un sistema como
 * parametro.
 * @param sist
 */
	public UsuarioVentana(Sistema sist) {
		sistema = sist;
		this.listaUsuarioVector = sist.getUsuarios();
		this.inicializarVentana();
	}

	public void inicializarVentana() {

		// agrega las cosas de la lista
		listaUsuarioJList = new JList(listaUsuarioVector);
		scrollUsuario.setViewportView(listaUsuarioJList);
		this.listaUsuarioJList.setToolTipText("Usuarios");
		// setear color de fondo
		this.listaUsuarioJList.setBackground(Color.ORANGE);
		// setear renderers
		listaUsuarioJList.setCellRenderer(new UsuarioRenderer());

			// los Paneles
		// el panel de los botones
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		panelBotones.add(crear);
		panelBotones.add(modificar);
		panelBotones.add(eliminar);

		modificar.setEnabled(false);
		eliminar.setEnabled(false);

		// el panel de datos
		panelDatos.setLayout(new GridLayout(4, 2));
		panelDatos.add(labelNombreUsuario);
		panelDatos.add(textoNombreUsuario);
		panelDatos.add(labelEmailUsuario);
		panelDatos.add(textoEmailUsuario);
		// seteo diversas cosas a la clase
		this.setLayout(new GridLayout(2, 2));
		this.add(panelBotones);
		this.add(scrollUsuario);
		this.add(panelDatos);
		listaUsuarioJList.getSelectedValue();

		// agrego las acciones de la lista y de los botones de proyecto
		this.addAcions();
		}

	protected void addAcions() {
		listaUsuarioJList.addListSelectionListener(new SeleccionarElemento());

		listaUsuarioJList.addMouseListener(new DeseleccionarElemento());

		crear.addActionListener(new CrearUsuario());

		modificar.addActionListener(new ModificarUsuario());

		eliminar.addActionListener(new EliminarUsuario());
	}

	class SeleccionarElemento implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			try {
				//si no tengo ningun usuario seleccionado me desabilita 
				//los botones modificar y eliminar.
				if (listaUsuarioJList.isSelectionEmpty()) {

					modificar.setEnabled(false);
					eliminar.setEnabled(false);
				} else {
					//si tengo un usuario seleccionado me habilita 
					//los botones modificar y eliminar y ademas completa
					//los campos de la ventana con los datos del usuario.
					Usuario usuario = (Usuario) listaUsuarioJList
							.getSelectedValue();
					textoNombreUsuario.setText(usuario.getNombre());
					textoEmailUsuario.setText(usuario.getEmail());
					modificar.setEnabled(true);
					eliminar.setEnabled(true);
				}

			} catch (NullPointerException ex) {
			}

		}
	}

	class DeseleccionarElemento implements MouseListener {

		public void mouseClicked(MouseEvent e) {
				//es para deseleccionar un elemento
			if (e.getClickCount() == 2) {
				listaUsuarioJList.clearSelection();
				textoNombreUsuario.setText("");
				textoEmailUsuario.setText("");
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

	class CrearUsuario implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			//si los campos de texto no estan vacios puede crear usuarios
			if (!(textoNombreUsuario.getText().isEmpty() | textoEmailUsuario.getText().isEmpty())) {
				
				//crea un usuario tomando los datos de los campos de texto de la ventana
				sistema.crearUnUsuario(textoNombreUsuario.getText(),textoEmailUsuario.getText());
				listaUsuarioJList.setListData(sistema.getUsuarios());
				sistema.notificarObservadores();
			}
			else {
				JOptionPane
				.showMessageDialog(
						crear,
						"Tenes que completar los 2 campos,nombre y email.",
						"Error", JOptionPane.WARNING_MESSAGE);

			}
		}

	}

	class ModificarUsuario implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// modifica los valores nombre y email del usuario actual
			// tomando los valores que se encuentran en los campos de texto
			Usuario usuario = (Usuario) listaUsuarioJList.getSelectedValue();
			usuario.setNombre(textoNombreUsuario.getText());
			usuario.setEmail(textoEmailUsuario.getText());
			listaUsuarioJList.setListData(sistema.getUsuarios());
			sistema.notificarObservadores();
		}

	}

	class EliminarUsuario implements ActionListener {
		//elimina el usuario seleccionado
		public void actionPerformed(ActionEvent e) {
			listaUsuarioVector.remove(listaUsuarioJList.getSelectedValue());
			listaUsuarioJList.setListData(listaUsuarioVector);
			sistema.notificarObservadores();
		}
	}

/**
 * Actualiza esta ventana a partir de un sistema.
 */
	public void actualizarObservadores(Sistema sistema) {

		listaUsuarioJList.setListData(sistema.getUsuarios());
	}

}
