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
	// modelo

	private Sistema sistema;

	// el panel de datos del Usuario seleccionado
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

	public UsuarioVentana(Sistema sist) {
		sistema = sist;
		this.listaUsuarioVector = sist.getUsuarios();
		this.inicializarVentana();
	}

	public void inicializarVentana() {

		// poner a punto la lista
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

				if (listaUsuarioJList.isSelectionEmpty()) {

					modificar.setEnabled(false);
					eliminar.setEnabled(false);
				} else {

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
			
			sistema.crearUnUsuario(textoNombreUsuario.getText(),textoEmailUsuario.getText());
			listaUsuarioJList.setListData(sistema.getUsuarios());
			sistema.notificarObservadores();
		}

	}

	class ModificarUsuario implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Usuario usuario = (Usuario) listaUsuarioJList.getSelectedValue();
			usuario.setNombre(textoNombreUsuario.getText());
			usuario.setEmail(textoEmailUsuario.getText());
			listaUsuarioJList.setListData(sistema.getUsuarios());
			sistema.notificarObservadores();
		}

	}

	class EliminarUsuario implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			listaUsuarioVector.remove(listaUsuarioJList.getSelectedValue());
			listaUsuarioJList.setListData(listaUsuarioVector);
			sistema.notificarObservadores();
		}
	}


	public void actualizarObservadores(Sistema sistema) {

		listaUsuarioJList.setListData(sistema.getUsuarios());
	}

}
