package ventanaTarea;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;
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

import proyectoYSistema.Proyecto;
import proyectoYSistema.Sistema;

public class ProyectoVentana extends JPanel implements
		InterfaceObserversSistema {

	// las variables que se encargan de manejar la lista
	private Vector<Proyecto> listaProyectosVector = new Vector<Proyecto>();
	private JList listaProyectosJList;
	private JScrollPane scrollProyectos = new JScrollPane();
	// el panel de datos que contiene los campos de texto y los labels
	private JPanel panelDatos = new JPanel();
	private TextArea textoNotaCierreProyecto = new TextArea(null, 2, 1, 1);
	private TextField textoNombreProyecto = new TextField();
	private TextArea textoDescripcionProyecto = new TextArea(null, 2, 4, 1);
	private JLabel labelNombreProyecto = new JLabel(
			"Nombre Del Proyecto Actual");
	private JLabel labelDescripcionProyecto = new JLabel(
			"Descripcion Del Proyecto Actual");
	// el sistema
	private Sistema sistema;

	// botones de la pestanha proyecto
	private JPanel panelBotones = new JPanel();
	private JButton crear = new JButton("Crear Proyecto");
	private JButton modificar = new JButton("Modificar Proyecto");
	private JButton eliminar = new JButton("Eliminar Proyecto");
	private JButton modificarProyecto = new JButton("Administrar Proyecto ");

	// botones de la parte inferior de la ventana
	JPanel panelBotonesInferiores = new JPanel();

	private JButton reabrir = new JButton("Reabrir Proyecto");
	private JButton cerrar = new JButton("Cerrar Proyecto");

	public ProyectoVentana(Sistema sis) {
		sistema = sis;
		sis.agregarObservador(this);
		this.listaProyectosVector = sis.getProyectos();
		inicializarVentana();

	}

	private void inicializarVentana() {
		// le creo una Jlist con el vector a mostrar
		listaProyectosJList = new JList(listaProyectosVector);
		// seteo el scroll para la Jlist
		scrollProyectos.setViewportView(listaProyectosJList);
		this.listaProyectosJList.setToolTipText("Proyectos");
		this.listaProyectosJList.setBackground(Color.ORANGE);

		// se componen y setean todos los paneles

		// el panel de los botones crear,modificar y eliminar
		panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
		panelBotones.add(crear);
		panelBotones.add(modificar);
		panelBotones.add(eliminar);

		modificar.setEnabled(false);
		eliminar.setEnabled(false);

		// el panel de los botones modificarProyecto
		panelBotonesInferiores.setLayout(new BoxLayout(panelBotonesInferiores,
				BoxLayout.Y_AXIS));
		panelBotonesInferiores.add(modificarProyecto);
		panelBotonesInferiores.add(reabrir);
		panelBotonesInferiores.add(cerrar);
		panelBotonesInferiores.add(textoNotaCierreProyecto);

		// el panel de datos
		panelDatos.setLayout(new GridLayout(4, 2));
		panelDatos.add(labelNombreProyecto);
		panelDatos.add(textoNombreProyecto);
		panelDatos.add(labelDescripcionProyecto);
		panelDatos.add(textoDescripcionProyecto);

		// agrego los renderers
		listaProyectosJList.setCellRenderer(new ProyectoRenderer());

		// componer panels
		this.setLayout(new GridLayout(2, 2));
		this.add(scrollProyectos);
		this.add(panelBotones);
		this.add(panelDatos);
		this.add(panelBotonesInferiores);

		// agrego las acciones de la lista y de los botones de proyecto
		// y el estado inicial de los botones
		this.modificar.setEnabled(false);
		this.eliminar.setEnabled(false);
		this.cerrar.setEnabled(false);
		this.reabrir.setEnabled(false);
		modificarProyecto.setEnabled(false);

		this.addAcions();

	}

	protected void addAcions() {
		listaProyectosJList.addListSelectionListener(new SeleccionarElemento());

		listaProyectosJList.addMouseListener(new DeseleccionarElemento());

		crear.addActionListener(new CrearProyecto());

		modificar.addActionListener(new ModificarProyecto());

		eliminar.addActionListener(new EliminarProyecto());

		modificarProyecto.addActionListener(new ModificarProyectoActual());

		reabrir.addActionListener(new ReabrirProyecto());

		cerrar.addActionListener(new CerrarProyecto());
	}

	class SeleccionarElemento implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			try {
				// si no esta seleccionado ningun elemento de la lista
				// entonces que bloquee los botones
				if (listaProyectosJList.isSelectionEmpty()) {
					modificar.setEnabled(false);
					eliminar.setEnabled(false);
					cerrar.setEnabled(false);
					reabrir.setEnabled(false);
				} else {
					Proyecto proyecto = (Proyecto) listaProyectosJList
							.getSelectedValue();
					textoNombreProyecto.setText(proyecto.getNombre());
					textoDescripcionProyecto.setText(proyecto.getDescripcion());
					modificar.setEnabled(true);
					eliminar.setEnabled(true);
					// si esta cerrada que bloquee cerrar y permita reabrir
					if (proyecto.getEstaCerrada()) {

						cerrar.setEnabled(false);
						reabrir.setEnabled(true);
						modificarProyecto.setEnabled(false);
						// si esta abierta que bloquee cerrar y permita cerrar
					} else {
						cerrar.setEnabled(true);
						reabrir.setEnabled(false);
						modificarProyecto.setEnabled(true);
					}

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

		// no se usan
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

			if (!(textoNombreProyecto.getText().isEmpty() | textoDescripcionProyecto
					.getText().isEmpty())) {

				// el usuario es null porque no creamos la interfaz visual
				// con la diferencia de estar "logeado" con un usuario server el
				// cual seria el creador.

				sistema.crearUnProyecto(textoNombreProyecto.getText(),
						textoDescripcionProyecto.getText(), null);
				textoNombreProyecto.setText("");
				textoDescripcionProyecto.setText("");
				sistema.notificarObservadores();
			} else {

				JOptionPane
						.showMessageDialog(
								crear,
								"Tenes que completar los 2 campos,nombre y descripcion.",
								"Error", JOptionPane.WARNING_MESSAGE);
			}

		}

	}

	class ModificarProyectoActual implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//crea una nueva ventana para poder administrar correctamente el proyecto actual
			if (!listaProyectosJList.isSelectionEmpty()) {
				sistema.agregarObservador(new ModificarProyectoVentana(
						(Proyecto) listaProyectosJList.getSelectedValue(),
						sistema.getUsuarios()));
			} else {
				JOptionPane.showMessageDialog(modificarProyecto,
						"Selecciona un proyecto.", "Error",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	class ModificarProyecto implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// modifica los valores nombre y descripcion del proyecto actual
			// tomando los valores que se encuentran en los campos de texto
			Proyecto proyecto = (Proyecto) listaProyectosJList
					.getSelectedValue();
			proyecto.setNombre(textoNombreProyecto.getText());
			proyecto.setDescripcion(textoDescripcionProyecto.getText());

			sistema.notificarObservadores();
		}

	}

	class EliminarProyecto implements ActionListener {

		public void actionPerformed(ActionEvent e) {
				//elimina el proyecto seleccionado
			listaProyectosVector.remove(listaProyectosJList.getSelectedValue());
			textoNombreProyecto.setText("");
			textoDescripcionProyecto.setText("");
			sistema.notificarObservadores();
		}
	}

	class CerrarProyecto implements ActionListener {
		//cierra el proyecto seleccionado siempre y cuando no haya sido 
		//cerrado con anterioridad
		public void actionPerformed(ActionEvent e) {
			if (!listaProyectosJList.isSelectionEmpty()) {
				Proyecto proyecto = ((Proyecto) listaProyectosJList
						.getSelectedValue());
				proyecto.cerrarProyecto(textoNotaCierreProyecto.getText());
				sistema.notificarObservadores();
				textoDescripcionProyecto.setText(proyecto.getDescripcion());
				textoNotaCierreProyecto.setText("");
				modificarProyecto.setEnabled(false);
			}
		}
	}

	class ReabrirProyecto implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			//reabre el proyecto seleccionado siempre y cuando no haya sido 
			//reabierto con anterioridad
			if (!listaProyectosJList.isSelectionEmpty()) {

				Proyecto pro = ((Proyecto) listaProyectosJList
						.getSelectedValue());

				pro.reabrirProyecto(textoNotaCierreProyecto.getText());

				sistema.notificarObservadores();
				textoDescripcionProyecto.setText(pro.getDescripcion());
				textoNotaCierreProyecto.setText("");
			}

		}
	}
/**
 * Se actualiza esta ventana con el sistema pasado x parametro.
 */
	public void actualizarObservadores(Sistema sistema) {

		listaProyectosJList.setListData(sistema.getProyectos());

	}

}
