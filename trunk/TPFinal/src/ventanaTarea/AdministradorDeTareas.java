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

public class AdministradorDeTareas extends JFrame implements VentanaTareaObserver {

	protected ArrayList <VentanaTareaObserver>listaObservadores;
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
	public AdministradorDeTareas(Proyecto proyecto, ArrayList<VentanaTareaObserver> observadores) {
		this.proyectoActual = proyecto;
		this.listaTareas = proyectoActual.getListaTareas();
        this.listaObservadores= observadores;
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

		// this.setSize(400, 400);
		// this.setResizable(true);
		// this.pack();

		// le digo donde tiene que aparecer la ventana!!
		this.setLocation(250, 150);
		this.setVisible(true);

	}

	/**
	 * Declara las acciones de los botones y otros componentes.
	 */
	public void addAction() {
		;// la accion cuando seleccionas un elemento de la Tabla
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
	 * Mensaje especifico para la view, aviso de agregoTarea.
	 */
	public void seAgregoTarea() {
		tableModelo.setData(this.proyectoActual.getListaTareas());
		tableTareas.updateUI();
	}

	public void seBorroTarea() {
		tableTareas.clearSelection();
		tableTareas.updateUI();
	}

	/**
	 * 
	 * @author susy
	 * 
	 */
	class SelectionTablaListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) { 
			int row = tableTareas.getSelectedRow();
			if (row >= 0 &&  (AdministradorDeTareas.this.tableModelo
					.getSelected(row).sosTareaSimple() && !AdministradorDeTareas.this.tableModelo
					.getSelected(row).tieneOrden()) ) {

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
	 * 
	 * @author susy
	 * 
	 */
	class ActionAsignarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AdministradorTarea tareaSeleccionada = AdministradorDeTareas.this.tableModelo
					.getSelected(tableTareas.getSelectedRow());
			new AsignarMiembro(AdministradorDeTareas.this,
						tareaSeleccionada);
			  }
		}

	
	/**
	 * 
	 * @author susy
	 * 
	 */
	class ActionAbrirListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AdministradorTarea tarea = (AdministradorTarea) tableModelo
					.getSelected(tableTareas.getSelectedRow());
			 
			 new VentanaTareas(tarea, AdministradorDeTareas.this);

		}

	}

	/**
	 * 
	 * @author susy
	 * 
	 */
	class ActionCrearListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new CrearTarea(AdministradorDeTareas.this);

		}

	}

	/**
	 * 
	 * @author susy
	 * 
	 */
	class ActionBorrarListener implements ActionListener {

		@Override
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
	 * 
	 * @author susy
	 * 
	 */
	class ActionVolverListener implements ActionListener {

		@Override
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


	public void cambioElEstadoLaTarea(AdministradorTarea unaTarea) {
		int indice = tableTareas.getSelectedRow();
		AdministradorTarea tareActial = (AdministradorTarea) tableModelo
		.getSelected(tableTareas.getSelectedRow());
		proyectoActual.eliminarTarea(tareActial);
		proyectoActual.getListaTareas().add(indice, unaTarea);
		AdministradorDeTareas.this.seAgregoTarea();
	}

	
	public void seCerroLaTarea(AdministradorTarea tarea) {
		int indice = tableTareas.getSelectedRow();
		AdministradorTarea tareActial = (AdministradorTarea) tableModelo
		.getSelected(tableTareas.getSelectedRow());
		proyectoActual.eliminarTarea(tareActial);
		proyectoActual.getListaTareas().add(indice, tarea);
		AdministradorDeTareas.this.seAgregoTarea();
		
	}

	
	public void aumentoElPorcentageDeFinalizacion(AdministradorTarea tarea) {
		int indice = tableTareas.getSelectedRow();
		AdministradorTarea tareActial = (AdministradorTarea) tableModelo
		.getSelected(tableTareas.getSelectedRow());
		proyectoActual.eliminarTarea(tareActial);
		proyectoActual.getListaTareas().add(indice, tarea);
		AdministradorDeTareas.this.seAgregoTarea();
		
	}

	
	public void seReabrioLaTarea(AdministradorTarea tarea) {
		int indice = tableTareas.getSelectedRow();
		AdministradorTarea tareActial = (AdministradorTarea) tableModelo
		.getSelected(tableTareas.getSelectedRow());
		proyectoActual.eliminarTarea(tareActial);
		proyectoActual.getListaTareas().add(indice, tarea);
		AdministradorDeTareas.this.seAgregoTarea();
		
	}
	
	public static void main(String[] args) throws UsuarioYaTieneRolExepcion {
		Proyecto proyecto = new Proyecto("AAA", "BBB", null);
		Usuario usuario1 = new Usuario("Susana", "rosito.susana@gmail.com");
		Usuario usuario2 = new Usuario("Tatiana", "tati@gmail.com");
		Usuario usuario3 = new Usuario("Federico", "fede@gmail.com");
		TareaSimple tarea1 = new TareaSimple("Tarea1", "ahhhh", new Fecha(
		"12-03-2010"));
		TareaSimple tarea2 = new TareaSimple("Tarea2", "ehhh", new Fecha(
		"20-10-2010"));
		TareaSimple tarea3 = new TareaSimple("Tarea3", "ihhh", new Fecha(
		"04-01-2010"));
		
		proyecto.agregarTarea(tarea1);
		proyecto.agregarTarea(tarea2);
		proyecto.agregarTarea(tarea3);
		
		proyecto.agregarMiembro(usuario1, "Desarollador");
		proyecto.agregarMiembro(usuario3, "Testeador");
		proyecto.agregarMiembro(usuario2, "Diseñador");
		new AdministradorDeTareas(proyecto,null);
	}
}
