package ventanaTarea;

import java.awt.BorderLayout;
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

	private TareaCompuesta tareaParaAsignar;
	private List<AdministradorTarea> listaTareas = new ArrayList<AdministradorTarea>();
	private List<AdministradorTarea> listaTareasEnTarea= new ArrayList<AdministradorTarea>();
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

	public AsignarTareas(CrearTarea crearT,
			AdministradorTarea tarea) {
		this.listaObservadores.add(crearT);
		this.listaTareas.addAll(crearT.listaTareasAselec);
		this.tareaParaAsignar = (TareaCompuesta) tarea;
		this.listaTareasEnTarea = tareaParaAsignar.getTareasQueLaComponenen();
		this.listaTareas.removeAll(listaTareasEnTarea);
		this.init(crearT);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void init(CrearTarea adm) {

		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		modeloTareaEnTarea.setData(listaTareasEnTarea);
		tablaTareasEnTarea.setModel(modeloTareaEnTarea);
		tablaTareasEnTarea.setSize(80, 80);
		scrollEnTarea.setViewportView(tablaTareasEnTarea);

		modelotarea.setData(listaTareas);
		tablaTareas.setModel(modelotarea);
		tablaTareas.setSize(90, 90);
		scroll.setViewportView(tablaTareas);

		panelInfo.setLayout(new FlowLayout());
		lNombreTarea.setText("Nombre de Tarea: " + "   "
				+ tareaParaAsignar.getNombre());
		panelInfo.add(lNombreTarea);
		panelList.setLayout(new BorderLayout());
		panelList.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		panelList.add(scroll);
		panelList.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		panelDAcciones
				.setLayout(new BoxLayout(panelDAcciones, BoxLayout.Y_AXIS));
		panelDAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
		panelDAcciones.add(bAgregar);
		panelDAcciones.add(Box.createVerticalStrut(20));
		panelDAcciones.add(bCancelar);
		panelDAcciones.add(Box.createVerticalStrut(20));
		panelDAcciones.add(bVolver);
		panelConDosListas.setLayout(new BoxLayout(panelConDosListas,
				BoxLayout.X_AXIS));

		panelConDosListas.add(panelList);
		panelConDosListas.add(panelDAcciones);
		panelConDosListas.add(Box.createHorizontalStrut(20));
		panelConDosListas.add(scrollEnTarea);
		panelConDosListas.add(Box.createHorizontalStrut(20));
		this.add(panelInfo);
		// this.add(Box.createHorizontalStrut(20));
		// this.add(panelList);
		// this.add(panelDAcciones);
		this.add(panelConDosListas);
		bAgregar.setEnabled(false);
		bCancelar.setEnabled(false);
		this.addAction();
		this.setSize(200, 200);
		pack();
		this.setVisible(true);
	}

	public void addAction() {
		tablaTareas.getSelectionModel().addListSelectionListener(
				new MiSelectionAListener());
		tablaTareasEnTarea.getSelectionModel().addListSelectionListener(
				new MiSelectionCListener());
		bAgregar.addActionListener(new MiAceptarListener());
		bCancelar.addActionListener(new MiCancelarListener());
		bVolver.addActionListener(new MiVolverListener());

	}

	class MiCancelarListener implements ActionListener {

		@Override
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

	class MiVolverListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();

		}

	}

	class MiAceptarListener implements ActionListener {

		@Override
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

	class MiSelectionCListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (tablaTareasEnTarea.getSelectedRow() >= 0) {

				bCancelar.setEnabled(true);

			} else {

				bCancelar.setEnabled(false);

			}

		}

	}

	class MiSelectionAListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (tablaTareas.getSelectedRow() >= 0) {

				bAgregar.setEnabled(true);

			} else {

				bAgregar.setEnabled(false);

			}

		}

	}

}
