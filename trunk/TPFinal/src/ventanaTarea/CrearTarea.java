package ventanaTarea;


import java.awt.Color;
import java.awt.FlowLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tareas.AdministradorTarea;

public class CrearTarea extends JFrame {

	protected List<AdministradorTarea> listaTareasAselec = new ArrayList<AdministradorTarea>();
	private JTabbedPane pestaniaTabbedPane = new JTabbedPane();
	private JPanel panelcheckBox = new JPanel();
	protected JRadioButton rBConOrden = new JRadioButton("Si",false);
	protected JRadioButton rBSinOrden = new JRadioButton("No",true);
	
	private ButtonGroup groub = new ButtonGroup();
	
	private JLabel ltareasASeleccionar = new JLabel(
			"Elegir la anterior Tarea:");
	protected JTable tabla = new JTable();
	protected TareaTablaModel model = new TareaTablaModel();
	protected JScrollPane scroll = new JScrollPane();
	private List<AdministradorDeTareas> listaObservadores = new ArrayList<AdministradorDeTareas>();
	private JButton bAceptar = new JButton("  Aceptar  ");
	private JButton bCancelar = new JButton("  Cancelar  ");
	
	

	private PanelTarea panelCompuesta;
	private PanelTarea panelSimple;
	
	public CrearTarea(AdministradorDeTareas adm) {
		listaObservadores.add(adm);
		listaTareasAselec = adm.listaTareas;

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.init(adm);
	}

	public void init(AdministradorDeTareas adm) {
		this.setTitle("Crear Tarea");
		this.getContentPane().setLayout(new GridBagLayout());
		this.setSize(640, 400);
		this.setResizable(false);
		
		//lorden.setFont(new Font("Arial", Font.BOLD, 20));
		//lorden.setAlignmentX(JLabel.RIGHT_ALIGNMENT);
		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.gridx = 1;
		restricciones.gridy = 1;
		restricciones.gridheight = 1;
		restricciones.gridwidth = 1;
		this.add(Box.createVerticalStrut(20), restricciones);

		restricciones.gridx = 2;
		restricciones.gridy = 2;
		restricciones.gridwidth = 1;// GridBagConstraints.ABOVE_BASELINE;
		restricciones.gridheight = 1;
		// restricciones.fill = GridBagConstraints.NORTH;
		// restricciones.weightx = 1.0;
		// restricciones.weighty = 1.0;
		//this.add(lorden, restricciones);

		restricciones.gridx = 7;
		restricciones.gridy = 2;
		restricciones.gridwidth = 1;// GridBagConstraints.REMAINDER;
		restricciones.gridheight = 1;
		// restricciones.weighty = 1.0;
		restricciones.fill = GridBagConstraints.NORTH;

		this.add(ltareasASeleccionar, restricciones);

		this.panelcheckBox.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		this.panelcheckBox.setLayout(new FlowLayout());
		this.panelcheckBox.setBorder(BorderFactory.createTitledBorder("Desea que la tarea tenga orden?"));
		this.groub.add(rBConOrden);
		this.groub.add(rBSinOrden);
		this.panelcheckBox.add(rBConOrden);
		this.panelcheckBox.add(rBSinOrden);
		restricciones.gridx = 2;
		restricciones.gridy = 3;
		restricciones.fill = GridBagConstraints.HORIZONTAL;
		this.add(panelcheckBox, restricciones);
		//restricciones.weighty = 0.0;
		restricciones.gridx = 1;
		restricciones.gridy = 3;
		restricciones.gridheight = 1;
		restricciones.gridwidth = 1;
		this.add(Box.createVerticalStrut(40), restricciones);

		GridBagConstraints component1 = new GridBagConstraints();
		component1.gridx = 0;
		component1.gridy = 4;
		component1.gridwidth = 3;
		component1.gridheight = 3;
		// component1.fill = GridBagConstraints.SOUTH;
		//component1.weightx = 1.0;
		// component1.weighty = 2.0;
		// component1.weightx = 1.0;
		this.panelCompuesta = new PanelTareaC(adm, this);
		this.panelSimple = new PanelTareaS(adm, this);
		
		this.pestaniaTabbedPane.add("Simple", panelSimple );
		this.pestaniaTabbedPane.add("Compuesta", panelCompuesta);
		this.add(pestaniaTabbedPane, component1);

		model.setData(listaTareasAselec);
		tabla.setModel(model);
		tabla.setBackground(Color.ORANGE);
		tabla.setForeground(Color.BLACK);
		scroll.setViewportView(tabla);
		scroll.setSize(200,200);
		restricciones.gridx = 7;
		restricciones.gridy = 3;
		restricciones.gridwidth = 5;// GridBagConstraints.ABOVE_BASELINE;
		restricciones.gridheight = 5;
		// restricciones.fill = GridBagConstraints.EAST;
		// restricciones.weightx = 0.0;
		// restricciones.weighty = 1.0;
		this.add(scroll, restricciones);
		
		this.addAction();

		// this.setResizable(false);
		
		this.tabla.setVisible(false);
		this.pack();
		this.setLocation(100, 150);
		this.setVisible(true);
	}
	
	
	public void addAction() {
		tabla.getSelectionModel().addListSelectionListener(
				new MiSelectionListener());
		rBConOrden.addActionListener(new SelectionRadioButton());
		rBSinOrden.addActionListener(new SelectionRadioButton());
	}
	
	
	class SelectionRadioButton implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == rBConOrden){
				tabla.setVisible(true);
				
			}
			
			if(e.getSource() == rBSinOrden){
				tabla.setVisible(false);
			}
		}
		
	}
	
	class MiSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (tabla.getSelectedRow() >= 0 ) {
				// cambiar el fondo!!
			} else {

					}
		}

	}

}
