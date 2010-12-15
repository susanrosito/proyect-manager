package ventanaTarea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import proyectoYSistema.Proyecto;
import proyectoYSistema.UsuarioYaTieneRolExepcion;
import tareas.AdministradorTarea;
import tareas.TareaSimple;
import usuarioMiembroYFecha.Fecha;
import usuarioMiembroYFecha.Usuario;

public class AdministradorDeTareas extends JFrame implements
		VentanaTareaObserver {

	protected ArrayList<VentanaTareaObserver> listaObservadores;
	// modelo
	protected Proyecto proyectoActual;
	protected List<AdministradorTarea> listaTareas = new ArrayList<AdministradorTarea>();
	// Paneles
	private JPanel pConTabla = new JPanel();
	private JPanel pContenedor = new JPanel();
	private JPanel pPrimerasAcciones = new JPanel();
	private JPanel pSegundasAcciones = new JPanel();
	// Botones
	private JButton bVolver = new JButton("  Atras   ");
	private JButton bCrear = new JButton("  Crear Tarea  ");
	private JButton bBorrar = new JButton("     Borrar    ");
	private JButton bVer = new JButton("   Ver    ");
	private JButton bAsignarMiembro = new JButton("Asignar Miembro");
	// label,Scroll, table y tableModel
	private JLabel lTareasActuales = new JLabel("Tareas Actuales:");
	private JTable tableTareas = new JTable();
	private TareaTablaModel tableModelo = new TareaTablaModel();
	private JScrollPane scroll = new JScrollPane();

	/**
	 * Constructor de la ventana AdministradorDeTareas.
	 * 
	 * @param proyecto
	 */
	public AdministradorDeTareas(Proyecto proyecto,
			ArrayList<VentanaTareaObserver> observadores) {
		this.proyectoActual = proyecto;
		this.listaTareas = proyectoActual.getListaTareas();
		this.listaObservadores = observadores;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.init();
	}

	/**
	 * Inicializa la ventana,con sus propiedades especificas, crea sus
	 * componentes, y hasta sus acciones.
	 */
	public void init() {

		// seteo propiedades a la ventana
		this.setTitle("Administrador de Tareas");
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		// tabla de tareas Actuales en el proyecto
		tableModelo.setData(listaTareas);
		tableTareas.setModel(tableModelo);

		// seteoColores
		tableTareas.setBackground(Color.ORANGE);
		tableTareas.setForeground(Color.BLACK);

		// al scroll le seteo la tabla!!
		scroll.setViewportView(tableTareas);

		// seteo valores de dimencion
		tableTareas.setMaximumSize(new Dimension(100, 200));
		tableTareas.setMinimumSize(new Dimension(100, 100));

		// le digo que seleccione solamente un elemento de la lista
		tableTareas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// propiedades del label
		lTareasActuales.setFont(new Font("Arial", Font.BOLD, 20));
		lTareasActuales.setAlignmentX(JLabel.EAST);

		// panel con la tabla, propidades
		pConTabla.setLayout(new BorderLayout());
		pConTabla.setOpaque(false);
		pConTabla.add(scroll, BorderLayout.CENTER);
		pConTabla.add(Box.createHorizontalStrut(50), BorderLayout.EAST);
		pConTabla.add(Box.createHorizontalStrut(50), BorderLayout.WEST);

		// otras propiedades de la ventana
		this.setSize(400, 400);
		this.setResizable(false);

		// aca agrego los componentes a la ventana
		this.add(Box.createVerticalStrut(20));
		this.add(lTareasActuales);
		this.add(Box.createVerticalStrut(20));
		this.add(pConTabla);

		// compacta todo!!
		this.pack();

		// otro Panel que va a tener a dentro 2 paneles
		pContenedor.setLayout(new GridLayout(1, 2));

		// primerPanel sus propiedades
		pPrimerasAcciones.setLayout(new BoxLayout(pPrimerasAcciones,
				BoxLayout.Y_AXIS));

		pPrimerasAcciones.setBorder(BorderFactory
				.createTitledBorder("Acciones:"));

		pPrimerasAcciones.add(Box.createVerticalStrut(10));
		pPrimerasAcciones.add(bCrear);
		pPrimerasAcciones.add(Box.createVerticalStrut(10));
		pPrimerasAcciones.add(bBorrar);
		pPrimerasAcciones.add(Box.createVerticalStrut(10));

		// segundo panel sus propiedades
		pSegundasAcciones.setLayout(new BoxLayout(pSegundasAcciones,
				BoxLayout.Y_AXIS));

		pSegundasAcciones.setBorder(BorderFactory
				.createTitledBorder("Otras Acciones:"));

		pSegundasAcciones.add(Box.createVerticalStrut(10));
		pSegundasAcciones.add(bVer);
		pSegundasAcciones.add(Box.createVerticalStrut(10));
		pSegundasAcciones.add(bAsignarMiembro);
		pSegundasAcciones.add(Box.createVerticalStrut(10));
		pSegundasAcciones.add(bVolver);
		pSegundasAcciones.add(Box.createVerticalStrut(10));

		// aca agrego los 2 paneles al panelContenedor
		pContenedor.add(pPrimerasAcciones);
		pContenedor.add(pSegundasAcciones);

		// aca agrego el panel a la ventana
		this.add(pContenedor);

		// declaro de que manera se veran los botones cuando se inicia la
		// ventana
		bBorrar.setEnabled(false);
		bVer.setEnabled(false);
		bAsignarMiembro.setEnabled(false);

		// agrego las acciones de los botones y demas
		this.addAction();

		// le digo donde tiene que aparecer la ventana!!
		this.setLocation(250, 150);
		this.setVisible(true);

	}

	/**
	 * Declara las acciones de los botones y otros componentes.
	 */
	public void addAction() {
		// la accion cuando seleccionas un elemento de la Tabla
		tableTareas.getSelectionModel().addListSelectionListener(
				new SelectionTablaListener());
		// la Accion del boton Crear
		bCrear.addActionListener(new ActionCrearListener());
		// la Accion del boton Ver
		bVer.addActionListener(new ActionAbrirListener());
		// la Accion del boton Borrar
		bBorrar.addActionListener(new ActionBorrarListener());
		// la Accion del boton AsignarMiembro
		bAsignarMiembro.addActionListener(new ActionAsignarListener());
		// la Accion del boton Volver
		bVolver.addActionListener(new ActionVolverListener());
	}

	/**
	 * Mensaje especifico para la view, aviso de que se agregoTarea.
	 */
	public void seAgregoTarea() {
		tableModelo.setData(this.proyectoActual.getListaTareas());
		tableTareas.updateUI();
	}

	/**
	 * Mensaje especifico para la view, aviso de que se borroTarea.
	 */
	public void seBorroTarea() {
		tableTareas.clearSelection();
		tableTareas.updateUI();
	}

	/**
	 * Clase que implementa ListSelectionListener para la tabla
	 * 
	 * @author susy
	 * 
	 */
	class SelectionTablaListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			int row = tableTareas.getSelectedRow();
			if (row >= 0
					&& (AdministradorDeTareas.this.tableModelo.getSelected(row)
							.sosTareaSimple() && !AdministradorDeTareas.this.tableModelo
							.getSelected(row).tieneOrden())) {

				bAsignarMiembro.setEnabled(true);
				bVer.setEnabled(true);
				bBorrar.setEnabled(true);
			} else {

				bAsignarMiembro.setEnabled(false);
				bVer.setEnabled(false);
			}

		}

	}

	/**
	 * Clase que implementa actionListener para los botones
	 * 
	 * @author susy
	 * 
	 */
	class ActionAsignarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			AdministradorTarea tareaSeleccionada = AdministradorDeTareas.this.tableModelo
					.getSelected(tableTareas.getSelectedRow());
			new AsignarMiembro(AdministradorDeTareas.this, tareaSeleccionada);
		}
	}

	/**
	 * Clase que implementa actionListener para los botones
	 * 
	 * @author susy
	 * 
	 */
	class ActionAbrirListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			AdministradorTarea tarea = (AdministradorTarea) tableModelo
					.getSelected(tableTareas.getSelectedRow());

			new VentanaTareas(tarea, AdministradorDeTareas.this);

		}

	}

	/**
	 * Clase que implementa actionListener para los botones
	 * 
	 * @author susy
	 * 
	 */
	class ActionCrearListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			new CrearTarea(AdministradorDeTareas.this);

		}

	}

	/**
	 * Clase que implementa actionListener para los botones
	 * 
	 * @author susy
	 * 
	 */
	class ActionBorrarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (tableTareas.getSelectedRow() >= 0) {
				AdministradorTarea tareaSeleccionada = (AdministradorTarea) tableModelo
						.getSelected(tableTareas.getSelectedRow());
				tableModelo.remove(tareaSeleccionada);
				proyectoActual.eliminarTarea(tareaSeleccionada);
				AdministradorDeTareas.this.seBorroTarea();
			}
		}
	}

	/**
	 * Clase que implementa actionListener para los botones
	 * 
	 * @author susy
	 * 
	 */
	class ActionVolverListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			dispose();

		}
	}

	public Proyecto getProyectoActual() {
		return proyectoActual;
	}

	public void setProyectoActual(Proyecto proyectoActual) {
		this.proyectoActual = proyectoActual;
	}

	/**
	 * Mensaje especifico para la view, aviso de que se modifico una tarea.
	 */
	public void modificoTarea(AdministradorTarea unaTarea) {
		int indice = tableTareas.getSelectedRow();
		AdministradorTarea tareActial = (AdministradorTarea) tableModelo
				.getSelected(tableTareas.getSelectedRow());
		proyectoActual.eliminarTarea(tareActial);
		proyectoActual.getListaTareas().add(indice, unaTarea);

	}

	/**
	 * Mensaje especifico para la view, aviso de que una Tarea cambio su estado.
	 */
	public void cambioElEstadoLaTarea(AdministradorTarea unaTarea) {
		AdministradorDeTareas.this.modificoTarea(unaTarea);
		AdministradorDeTareas.this.seAgregoTarea();
	}

	/**
	 * Mensaje especifico para la view, aviso de que una tarea se Cerro.
	 */
	public void seCerroLaTarea(AdministradorTarea tarea) {
		AdministradorDeTareas.this.modificoTarea(tarea);
		AdministradorDeTareas.this.seAgregoTarea();

	}

	/**
	 * Mensaje especifico para la view, aviso de que una tarea cambio su
	 * porcetaje de realizacion.
	 */
	public void aumentoElPorcentageDeFinalizacion(AdministradorTarea tarea) {
		AdministradorDeTareas.this.modificoTarea(tarea);
		AdministradorDeTareas.this.seAgregoTarea();

	}

	/**
	 * Mensaje especifico para la view, aviso de que una tarea se reabrio.
	 */
	public void seReabrioLaTarea(AdministradorTarea tarea) {
		AdministradorDeTareas.this.modificoTarea(tarea);
		AdministradorDeTareas.this.seAgregoTarea();

	}
}
