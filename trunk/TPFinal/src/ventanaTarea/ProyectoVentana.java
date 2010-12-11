package ventanaTarea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
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
import javax.swing.plaf.BorderUIResource;

import proyectoYSistema.Proyecto;
import proyectoYSistema.Sistema;
import usuarioMiembroYFecha.Usuario;

public class ProyectoVentana extends JPanel {

	// las variables que se encargan de manejar la lista
	private Vector<Proyecto> listaProyectosVector = new Vector<Proyecto>();
	private JList listaProyectosJList;
	private JScrollPane scrollProyectos = new JScrollPane();
	// el panel de datos que contiene los campos de texto y los labels
	private JPanel panelDatos = new JPanel();
	private TextField textoNombreProyecto = new TextField();
	private TextField textoDescripcionProyecto = new TextField();
	private JLabel labelNombreProyecto = new JLabel("NombreProyecto");
	private JLabel labelDescripcionProyecto = new JLabel("DescripcionProyecto");

	private Sistema sistema;

	// botones de la pestanha proyecto
	private JPanel panelBotones = new JPanel();
	private JButton crear = new JButton("crear");
	private JButton modificar = new JButton("modificar");
	private JButton eliminar = new JButton("eliminar");
	private JButton modificarProyecto = new JButton("modificarProyecto ");

	// botones de la parte inferior de la ventana
	JPanel panelBotonesInferiores = new JPanel();

	// private JButton reabrir = new JButton("reabrir");
	// private JButton cerrar = new JButton("cerrar");

	public ProyectoVentana(Sistema sis) {
		sistema = sis;
		this.listaProyectosVector = sis.getProyectos();
		inicializarVentana();

	}

	private void inicializarVentana() {

		// le creo una Jlist con el vector a mostrar
		listaProyectosJList = new JList(listaProyectosVector);
		// seteo el scroll para la Jlist
		scrollProyectos.setViewportView(listaProyectosJList);
		listaProyectosJList.setVisibleRowCount(4);

		this.listaProyectosJList.setBackground(Color.ORANGE);

		// aca compongo y seteo todos los paneles

		// el panel de los botones crear,modificar y eliminar
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		panelBotones.add(crear);
		panelBotones.add(modificar);
		panelBotones.add(eliminar);

		modificar.setEnabled(false);
		eliminar.setEnabled(false);

		// el panel de los botones modificarProyecto
		panelBotonesInferiores.setLayout(new GridLayout(2, 2));
		panelBotonesInferiores.add(modificarProyecto);

		// , BorderLayout.CENTER

		// el panel de datos
		panelDatos.setLayout(new GridLayout(4, 2));
		panelDatos.add(labelNombreProyecto);
		panelDatos.add(textoNombreProyecto);
		panelDatos.add(labelDescripcionProyecto);
		panelDatos.add(textoDescripcionProyecto);

		// agrego los renderers
		listaProyectosJList.setCellRenderer(new ProyectoRenderer());

		// / componer panels
		this.setLayout(new GridLayout(2, 2));
		this.add(scrollProyectos);
		this.add(panelBotones);
		this.add(panelDatos);
		this.add(panelBotonesInferiores);

		// agrego las acciones de la lista y de los botones de proyecto
		this.addAcions();

	}

	protected void addAcions() {
		listaProyectosJList.addListSelectionListener(new SeleccionarElemento());

		listaProyectosJList.addMouseListener(new DeseleccionarElemento());

		crear.addActionListener(new CrearProyecto());

		modificar.addActionListener(new ModificarProyecto());

		eliminar.addActionListener(new EliminarProyecto());

		modificarProyecto.addActionListener(new ModificarProyectoActual());
	}

	class SeleccionarElemento implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			try {

				if (listaProyectosJList.isSelectionEmpty()) {
					modificar.setEnabled(false);
					eliminar.setEnabled(false);
				} else {
					Proyecto proyecto = (Proyecto) listaProyectosJList
							.getSelectedValue();
					textoNombreProyecto.setText(proyecto.getNombre());
					textoDescripcionProyecto.setText(proyecto.getDescripcion());
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
				listaProyectosJList.clearSelection();
				textoNombreProyecto.setText("");
				textoDescripcionProyecto.setText("");
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

	class CrearProyecto implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			// el usuario es null porque no creamos la interfaz visual
			// con la diferencia de estar "logeado" con un usuario server el
			// cual seria el creador.

			sistema.crearUnProyecto(textoNombreProyecto.getText(),
					textoDescripcionProyecto.getText(), null);
			listaProyectosJList.setListData(sistema.getProyectos());
		}

	}

	class ModificarProyectoActual implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// tiene que abrir otra ventana

			if (!listaProyectosJList.isSelectionEmpty()) {
				new ModificarProyectoVentana((Proyecto) listaProyectosJList
						.getSelectedValue());
			}
			else{
				JOptionPane.showMessageDialog(modificarProyecto,
						"Selecciona un proyecto.", "Error",
				JOptionPane.WARNING_MESSAGE);	
			}
		}
	}

	class ModificarProyecto implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Proyecto proyecto = (Proyecto) listaProyectosJList
					.getSelectedValue();
			proyecto.setNombre(textoNombreProyecto.getText());
			proyecto.setDescripcion(textoDescripcionProyecto.getText());
			listaProyectosJList.setListData(sistema.getProyectos());

		}

	}

	class EliminarProyecto implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			listaProyectosVector.remove(listaProyectosJList.getSelectedValue());
			listaProyectosJList.setListData(listaProyectosVector);
		}
	}

}
