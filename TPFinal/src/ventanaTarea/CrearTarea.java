package ventanaTarea;

import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tareas.AdministradorTarea;

public class CrearTarea extends JFrame {
	// modelo
	private List<AdministradorDeTareas> listaObservadores = new ArrayList<AdministradorDeTareas>();
	protected List<AdministradorTarea> listaTareasAselec = new ArrayList<AdministradorTarea>();
	// componentes
	private JTabbedPane pestaniaTabbedPane = new JTabbedPane();
	private JPanel panelcheckBox = new JPanel();
	protected JRadioButton rBConOrden = new JRadioButton("Si", false);
	protected JRadioButton rBSinOrden = new JRadioButton("No", true);
	private ButtonGroup groub = new ButtonGroup();
	private JLabel ltareasASeleccionar = new JLabel("Elegir la anterior Tarea:");
	protected JTable tabla = new JTable();
	protected TareaTablaModel model = new TareaTablaModel();
	protected JScrollPane scroll = new JScrollPane();
	private PanelTarea panelCompuesta;
	private PanelTarea panelSimple;
	/**
	 * Constructor de la ventana CrearTarea.
	 * 
	 * @param adm AdministradorDeTareas
	 */
	public CrearTarea(AdministradorDeTareas adm) {
		listaObservadores.add(adm);
		listaTareasAselec = adm.listaTareas;

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.init(adm);
	}

	/**
	 * Inicializa la ventana,con sus propiedades especificas, crea sus
	 * componentes, y hasta sus acciones.
	 */
	public void init(AdministradorDeTareas adm) {
		// propiedades de la view
		this.setTitle("Crear Tarea");
		this.getContentPane().setLayout(new GridBagLayout());
		this.setSize(640, 400);
		this.setResizable(false);
		// por usar GridBagLayout tengo que hacer contenedores con sus
		// propiedades para ubicar a los componentes
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.gridx = 1;
		restricciones.gridy = 1;
		restricciones.gridheight = 1;
		restricciones.gridwidth = 1;
		this.add(Box.createVerticalStrut(20), restricciones);
		// aca de claro otros valores para el siguiente componentes.
		restricciones.gridx = 2;
		restricciones.gridy = 2;
		restricciones.gridwidth = 1;
		restricciones.gridheight = 1;

		restricciones.gridx = 7;
		restricciones.gridy = 2;
		restricciones.gridwidth = 1;
		restricciones.gridheight = 1;

		restricciones.fill = GridBagConstraints.NORTH;
		// aca agrego el componente con sus restricciones
		this.add(ltareasASeleccionar, restricciones);

		// delaro un panel de radio button con sus propiedades
		this.panelcheckBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		this.panelcheckBox.setLayout(new FlowLayout());
		this.panelcheckBox.setBorder(BorderFactory
				.createTitledBorder("Desea que la tarea tenga orden?"));
		this.groub.add(rBConOrden);
		this.groub.add(rBSinOrden);
		this.panelcheckBox.add(rBConOrden);
		this.panelcheckBox.add(rBSinOrden);

		// aca de claro otros valores para el siguiente componentes.
		restricciones.gridx = 2;
		restricciones.gridy = 3;
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		// aca agrego el componente con sus restricciones
		this.add(panelcheckBox, restricciones);
		// aca de claro otros valores para el siguiente componentes.
		restricciones.gridx = 1;
		restricciones.gridy = 3;
		restricciones.gridheight = 1;
		restricciones.gridwidth = 1;
		this.add(Box.createVerticalStrut(40), restricciones);
		// aca creo otras restricciones aparte de las que estoy manejando antes
		GridBagConstraints component1 = new GridBagConstraints();
		component1.gridx = 0;
		component1.gridy = 4;
		component1.gridwidth = 3;
		component1.gridheight = 3;
		// declaro los paneles
		this.panelCompuesta = new PanelTareaC(adm, this);
		this.panelSimple = new PanelTareaS(adm, this);
		// aca agrego los paneles al componente que me crea pestañias
		this.pestaniaTabbedPane.add("Simple", panelSimple);
		this.pestaniaTabbedPane.add("Compuesta", panelCompuesta);
		// agrego el componente con sus restricciones
		this.add(pestaniaTabbedPane, component1);
		// declaro el modelo y la taba
		model.setData(listaTareasAselec);
		tabla.setModel(model);
		// le agrego colores
		tabla.setBackground(Color.ORANGE);
		tabla.setForeground(Color.BLACK);
		scroll.setViewportView(tabla);
		scroll.setSize(200, 200);
		// defino sus restricciones
		restricciones.gridx = 7;
		restricciones.gridy = 3;
		restricciones.gridwidth = 5;
		restricciones.gridheight = 5;
		// agrego la tabla
		this.add(scroll, restricciones);
		// agrego las acciones a la vista
		this.addAction();
		// declaro sus propiedades
		this.tabla.setVisible(false);
		this.pack();
		this.setLocation(100, 150);
		this.setVisible(true);
	}

	/**
	 * Declara las acciones de los botones y otros componentes.
	 */
	public void addAction() {

		tabla.getSelectionModel().addListSelectionListener(
				new MiSelectionListener());
		// la Accion del boton ConOrden
		rBConOrden.addActionListener(new SelectionRadioButton());
		// la Accion del boton SinOrden
		rBSinOrden.addActionListener(new SelectionRadioButton());
	}

	/**
	 * Clase que implementa ActionListener para los radioButton.
	 * 
	 * @author susy
	 * 
	 */
	class SelectionRadioButton implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == rBConOrden) {
				if (model.getRowCount() == 0) {
					JOptionPane
							.showMessageDialog(null,
									"No hay tareas para poder ordenar.Intentalo mas tarde.");
					rBConOrden.setSelected(false);
					rBSinOrden.setSelected(true);
				} else {
					tabla.setVisible(true);
				}
			}

			if (e.getSource() == rBSinOrden) {
				tabla.setVisible(false);
			}
		}

	}

	/**
	 * Clase que implementa ListSelectionListener para la tabla
	 * 
	 * @author susy
	 * 
	 */
	class MiSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent arg0) {
			if (tabla.getSelectedRow() >= 0) {
				

			}
		}

	}

}
