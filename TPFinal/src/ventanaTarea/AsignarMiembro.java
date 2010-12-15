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

	private TareaSimple tareaParaAsignar;
	private List<Miembro> listaMiembros;
	private JLabel lNombreTarea = new JLabel();
	private JTable tablaMiembros = new JTable();
	private MiembroTablaModel modeloMiembro = new MiembroTablaModel();
	private JPanel panelInfo = new JPanel();
	private JScrollPane scroll = new JScrollPane();
	private JPanel panelDAcciones = new JPanel();
	private JButton bAceptar = new JButton("Aceptar");
	private JButton bCancelar = new JButton("Cancelar");
	private JButton bVolver = new JButton("Volver");
	private JPanel panelList = new JPanel();
	private List<AdministradorDeTareas> listaObservadores = new ArrayList<AdministradorDeTareas>();
	
	public AsignarMiembro(AdministradorDeTareas adm, AdministradorTarea tarea) {
		this.listaObservadores.add(adm);
		this.listaMiembros = adm.getProyectoActual().getListaDeMiembros();
		this.tareaParaAsignar = (TareaSimple) tarea;
		this.init(adm);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void init(AdministradorDeTareas adm) {
		this.setTitle("Seleccionar un miembro para la Tarea");
		// this.setLayout(new BoxLayout(this.getContentPane(),
		// BoxLayout.Y_AXIS));
		modeloMiembro.setData(listaMiembros);
		tablaMiembros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaMiembros.setModel(modeloMiembro);
		scroll.setViewportView(tablaMiembros);

		panelInfo.setLayout(new FlowLayout());
		lNombreTarea.setText("Nombre de Tarea: " + "   "
				+ tareaParaAsignar.getNombre());
		panelInfo.add(lNombreTarea);
		
		panelList.setLayout(new BorderLayout());
		panelList.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		panelList.add(scroll);
		panelList.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		panelDAcciones.setLayout(new FlowLayout());
		panelDAcciones.setBorder(BorderFactory.createTitledBorder("Acciones"));
		panelDAcciones.add(bAceptar);
		panelDAcciones.add(bCancelar);
		panelDAcciones.add(bVolver);
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.setLayout(layout);

		layout.setHorizontalGroup(layout
				.createParallelGroup()
				.addGroup(
						layout.createParallelGroup()
								.addComponent(lNombreTarea, -1, 5, 5)
								.addGap(11, 11, 11)
								.addComponent(scroll, -1, 250, 250))
								
				.addGroup(
						layout.createParallelGroup()
						.addGap(21, 21,21)
								.addComponent(panelDAcciones,-1, 50, 50)
								));
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addComponent(lNombreTarea, -1, 5, 5)
						.addGap(11, 11, 11)
						.addComponent(scroll,-1, 250, 250)

						.addGroup(
								layout.createSequentialGroup()
										.addGap(21, 21, 21)
										.addComponent(panelDAcciones,-1, 50, 50)

						)));

		// this.add(panelInfo);
		// this.add(Box.createHorizontalStrut(20));
		// this.add(panelList);
		// this.add(panelDAcciones);
		bAceptar.setEnabled(false);

		this.addAction();
		this.setSize(200, 200);
		pack();
		this.setLocation(380, 200);
		this.setVisible(true);
	}

	public void addAction() {
		tablaMiembros.getSelectionModel().addListSelectionListener(
				new MiSelectionAListener());
		bAceptar.addActionListener(new MiAceptarListener());
		bCancelar.addActionListener(new MiCancelarListener());
		bVolver.addActionListener(new MiVolverListener());
	}

	class MiCancelarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();

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
			if (JOptionPane.showConfirmDialog(bAceptar, "¿Esta seguro?") == 0) {
				Miembro miembro = modeloMiembro.getSelected(tablaMiembros
						.getSelectedRow());
				tareaParaAsignar.modificarMiembroAsignado(miembro);
				AsignarMiembro.this.setVisible(false);
				dispose();

			}

		}
	}

	class MiSelectionAListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (tablaMiembros.getSelectedRow() >= 0) {

				bAceptar.setEnabled(true);

			} else {

				bAceptar.setEnabled(false);

			}

		}

	}

}