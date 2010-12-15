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
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tareas.AdministradorTarea;
import tareas.TareaSimple;
import usuarioMiembroYFecha.Miembro;

public class AsignarMiembro extends JFrame {
	// Modelo
	private List<AdministradorDeTareas> listaObservadores = new ArrayList<AdministradorDeTareas>();
	private TareaSimple tareaParaAsignar;
	private List<Miembro> listaMiembros;
	// componentes
	private JLabel lNombreTarea = new JLabel();
	private JTable tablaMiembros = new JTable();
	private MiembroTablaModel modeloMiembro = new MiembroTablaModel();
	private JPanel panelInfo = new JPanel();
	private JScrollPane scroll = new JScrollPane();
	private JPanel panelDAcciones = new JPanel();
	private JButton bAceptar = new JButton("Aceptar");
	private JButton bVolver = new JButton("Atras");
	private JPanel panelList = new JPanel();

	public AsignarMiembro(AdministradorDeTareas adm, AdministradorTarea tarea) {
		this.listaObservadores.add(adm);
		this.listaMiembros = adm.getProyectoActual().getListaDeMiembros();
		this.tareaParaAsignar = (TareaSimple) tarea;
		this.init(adm);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/**
	 * Inicializa la ventana,con sus propiedades especificas, crea sus
	 * componentes, y hasta sus acciones.
	 */
	public void init(AdministradorDeTareas adm) {
		this.setTitle("Seleccionar un miembro para la Tarea");
		// defino la tabla y el modelo que voy a usar.. junto con sus
		// propiedades
		modeloMiembro.setData(listaMiembros);
		tablaMiembros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaMiembros.setModel(modeloMiembro);
		// aca le agrego colores a la tabla
		tablaMiembros.setBackground(Color.ORANGE);
		tablaMiembros.setForeground(Color.BLACK);

		scroll.setViewportView(tablaMiembros);
		// armo un panel para el nombre de la tarea a asignarle un miembro.
		panelInfo.setLayout(new FlowLayout());
		lNombreTarea.setText("Nombre de Tarea: " + "   "
				+ tareaParaAsignar.getNombre());
		panelInfo.add(lNombreTarea);

		// declaro el panel de la lista con sus propiedades
		panelList.setLayout(new BorderLayout());
		panelList.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		panelList.add(scroll);
		panelList.add(Box.createHorizontalStrut(20), BorderLayout.WEST);

		// declaro el panel de acciones junto con sus propiedades.
		panelDAcciones.setLayout(new FlowLayout());
		panelDAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
		panelDAcciones.add(bAceptar);
		panelDAcciones.add(bVolver);

		// para diceñar esta ventana utilize el layout group, es por eso que se
		// ve tan
		// armado. fue para colocar los componentes donde yo queria.
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup().addGroup(
				layout.createParallelGroup().addComponent(lNombreTarea, -1, 5,
						5).addGap(11, 11, 11)
						.addComponent(scroll, -1, 250, 250))

		.addGroup(
				layout.createParallelGroup().addGap(21, 21, 21).addComponent(
						panelDAcciones, -1, 50, 50)));
		layout.setVerticalGroup(layout.createSequentialGroup().addGroup(
				layout.createSequentialGroup().addComponent(lNombreTarea, -1,
						5, 5).addGap(11, 11, 11).addComponent(scroll, -1, 250,
						250)

				.addGroup(
						layout.createSequentialGroup().addGap(21, 21, 21)
								.addComponent(panelDAcciones, -1, 50, 50)

				)));
		// especifico que este boton va a estar inhabilitado cuando aparesca la
		// ventana.
		bAceptar.setEnabled(false);
		// le agrego las acciones,de los componentes
		this.addAction();
		// propiedades de la ventana
		this.setSize(200, 200);
		pack();
		this.setLocation(380, 200);
		this.setVisible(true);
	}

	/**
	 * Declara las acciones de los botones y otros componentes.
	 */
	public void addAction() {
		// la accion cuando seleccionas un elemento de la Tabla
		tablaMiembros.getSelectionModel().addListSelectionListener(
				new MiSelectionAListener());
		// la Accion del boton Aceptar
		bAceptar.addActionListener(new MiAceptarListener());
		// la Accion del boton Volver
		bVolver.addActionListener(new MiVolverListener());
	}

	/**
	 * Notifica a sus observer de los cambios que ocurren.
	 */
	public void notificarObserver() {
		for (AdministradorDeTareas observer : listaObservadores) {
			observer.seAgregoTarea();
		}
	}

	/**
	 * Clase que implementa ActionListener para el Boton Volver
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
	 * Clase que implementa ActionListener para el Boton Aceptar
	 * 
	 * @author susy
	 * 
	 */
	class MiAceptarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Miembro miembro = modeloMiembro.getSelected(tablaMiembros
					.getSelectedRow());
			if (!(tareaParaAsignar.getMiembroAsignado() == null)) {
				if (tareaParaAsignar.getMiembroAsignado().getUsuario().equals(
						miembro.getUsuario())) {
					JOptionPane
							.showMessageDialog(bAceptar,
									"Este miembro esta asignado a esta Tarea. Eliga otro por favor.");
				} else {
					if (JOptionPane
							.showConfirmDialog(bAceptar, "¿Esta seguro?") == 0) {
						tareaParaAsignar.modificarMiembroAsignado(miembro);
						AsignarMiembro.this.notificarObserver();
						dispose();
					}

				}
			} else {
				if (JOptionPane.showConfirmDialog(bAceptar, "¿Esta seguro?") == 0) {
					tareaParaAsignar.modificarMiembroAsignado(miembro);
					AsignarMiembro.this.notificarObserver();
					dispose();
				}
			}
		}
	}

	/**
	 * Clase que implementa ListSelectionListener para la tabla
	 * 
	 * @author susy
	 * 
	 */
	class MiSelectionAListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			if (tablaMiembros.getSelectedRow() >= 0) {

				bAceptar.setEnabled(true);

			} else {

				bAceptar.setEnabled(false);

			}

		}

	}

}
