package ventanaTarea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tareas.AdministradorTarea;
import tareas.TareaCompuesta;

public class AsignarTareas extends JFrame {
	// modelo
	private TareaCompuesta tareaParaAsignar;
	private List<AdministradorTarea> listaTareas = new ArrayList<AdministradorTarea>();
	private List<AdministradorTarea> listaTareasEnTarea = new ArrayList<AdministradorTarea>();
	// componentes
	private JLabel lNombreTarea = new JLabel();
	private JTable tablaTareas = new JTable();
	private JTable tablaTareasEnTarea = new JTable();
	private TareaTablaModel modelotarea = new TareaTablaModel();
	private TareaTablaModel modeloTareaEnTarea = new TareaTablaModel();
	private JPanel panelInfo = new JPanel();
	private JScrollPane scrollEnTarea = new JScrollPane();
	private JScrollPane scroll = new JScrollPane();
	private JPanel panelDAcciones = new JPanel();
	private JButton bAgregar = new JButton("  >>  ");
	private JButton bCancelar = new JButton("  <<  ");
	private JPanel panelList = new JPanel();
	private JButton bVolver = new JButton("  Volver ");
	private List<CrearTarea> listaObservadores = new ArrayList<CrearTarea>();
	private JPanel panelConDosListas = new JPanel();

	public AsignarTareas(CrearTarea crearT, AdministradorTarea tarea) {

		// agrego como observador a crearTarea
		this.listaObservadores.add(crearT);
		this.listaTareas.addAll(crearT.listaTareasAselec);

		// trabajo con una tarea compuesta
		this.tareaParaAsignar = (TareaCompuesta) tarea;

		// obtengo la lista de las tareas que las componen
		this.listaTareasEnTarea = tareaParaAsignar.getTareasQueLaComponenen();

		// esta es la lista de las tareas del sistema,
		// uso una copia ya que quiero reflejar las que he asignado a la tarea.
		this.listaTareas.removeAll(listaTareasEnTarea);
		this.init(crearT);

		// esto es cuando la quiero cerrar. quiero que se valla pero que no
		// muera el proceso.
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Inicializa la ventana,con sus propiedades especificas, crea sus
	 * componentes, y hasta sus acciones.
	 */
	public void init(CrearTarea adm) {

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		// declaro los modelos y las tablas que voy a usar, junto con sus
		// propiedades
		modeloTareaEnTarea.setData(listaTareasEnTarea);
		tablaTareasEnTarea.setModel(modeloTareaEnTarea);
		// las propiedades como tamaño y color de fondo y fuente
		tablaTareasEnTarea.setSize(80, 80);
		tablaTareasEnTarea.setBackground(Color.ORANGE);
		tablaTareasEnTarea.setForeground(Color.BLACK);

		scrollEnTarea.setViewportView(tablaTareasEnTarea);

		modelotarea.setData(listaTareas);
		tablaTareas.setModel(modelotarea);
		tablaTareas.setSize(90, 90);
		scroll.setViewportView(tablaTareas);
		// defino el panelinfo junto con sus propiedades
		panelInfo.setLayout(new FlowLayout());
		lNombreTarea.setText("Nombre de Tarea: " + "   "
				+ tareaParaAsignar.getNombre());
		panelInfo.add(lNombreTarea);
		// declaro el panel de la lista con sus propiedades
		panelList.setLayout(new BorderLayout());
		panelList.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		panelList.add(scroll);
		panelList.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		// panel cn primeras acciones con sus propiedades
		panelDAcciones
				.setLayout(new BoxLayout(panelDAcciones, BoxLayout.Y_AXIS));
		panelDAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
		panelDAcciones.add(bAgregar);
		panelDAcciones.add(Box.createVerticalStrut(20));
		panelDAcciones.add(bCancelar);
		panelDAcciones.add(Box.createVerticalStrut(20));
		panelDAcciones.add(bVolver);
		// panel con las dos Tablas con sus propiedades
		panelConDosListas.setLayout(new BoxLayout(panelConDosListas,
				BoxLayout.X_AXIS));

		panelConDosListas.add(panelList);
		panelConDosListas.add(panelDAcciones);
		panelConDosListas.add(Box.createHorizontalStrut(20));
		panelConDosListas.add(scrollEnTarea);
		panelConDosListas.add(Box.createHorizontalStrut(20));
		// agrego el panel info a la vista
		this.add(panelInfo);
		// agrego el panel con las dos tablas a la vista
		this.add(panelConDosListas);
		// especifico que estos botones va a estar inhabilitados cuando aparesca
		// la ventana.
		bAgregar.setEnabled(false);
		bCancelar.setEnabled(false);
		// agrego las acciones a la vista
		this.addAction();

		// defino propiedades de la vista
		this.setSize(200, 200);
		pack();
		this.setLocation(0, 150);
		this.setVisible(true);
	}

	/**
	 * Declara las acciones de los botones y otros componentes.
	 */
	public void addAction() {
		tablaTareas.getSelectionModel().addListSelectionListener(
				new MiSelectionAListener());
		tablaTareasEnTarea.getSelectionModel().addListSelectionListener(
				new MiSelectionCListener());
		bAgregar.addActionListener(new MiAceptarListener());
		bCancelar.addActionListener(new MiCancelarListener());
		bVolver.addActionListener(new MiVolverListener());

	}

	/**
	 * Clase que implementa ActionListener para el Boton Cancelar.
	 * 
	 * @author susy
	 * 
	 */
	class MiCancelarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (tablaTareasEnTarea.getSelectedRow() >= 0) {
				AdministradorTarea tarea = modeloTareaEnTarea
						.getSelected(tablaTareasEnTarea.getSelectedRow());
				modeloTareaEnTarea.remove(tarea);
				tablaTareasEnTarea.clearSelection();
				tablaTareasEnTarea.updateUI();

				modelotarea.add(tarea);
				tablaTareas.updateUI();

			}
		}

	}

	/**
	 * Clase que implementa ActionListener para el Boton Volver.
	 * 
	 * @author susy
	 * 
	 */
	class MiVolverListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			dispose();

		}

	}

	/**
	 * Clase que implementa ActionListener para el Boton Aceptar.
	 * 
	 * @author susy
	 * 
	 */
	class MiAceptarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (tablaTareas.getSelectedRow() >= 0) {
				AdministradorTarea tarea = modelotarea.getSelected(tablaTareas
						.getSelectedRow());
				modelotarea.remove(tarea);
				tablaTareas.clearSelection();
				tablaTareas.updateUI();
				tareaParaAsignar.agregarTarea(tarea);

				modeloTareaEnTarea.setData(tareaParaAsignar
						.getTareasQueLaComponenen());
				tablaTareasEnTarea.updateUI();

			}
		}
	}

	/**
	 * Clase que implementa ListSelectionListener para la tablaTareasEnTarea.
	 * 
	 * @author susy
	 * 
	 */
	class MiSelectionCListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (tablaTareasEnTarea.getSelectedRow() >= 0) {

				bCancelar.setEnabled(true);

			} else {

				bCancelar.setEnabled(false);

			}

		}

	}

	/**
	 * Clase que implementa ListSelectionListener para la tablaTareas.
	 * 
	 * @author susy
	 * 
	 */
	class MiSelectionAListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (tablaTareas.getSelectedRow() >= 0) {

				bAgregar.setEnabled(true);

			} else {

				bAgregar.setEnabled(false);

			}

		}

	}

}
