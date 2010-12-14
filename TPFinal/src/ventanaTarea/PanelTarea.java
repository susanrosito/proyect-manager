package ventanaTarea;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import proyectoYSistema.Proyecto;
import tareas.OrganizadorTarea;

import com.toedter.calendar.JDateChooser;

public abstract class PanelTarea extends JPanel {

	protected JPanel pform = new JPanel();
	protected JLabel lNombre = new JLabel("Nombre:");
	protected JTextField tNombre = new JTextField();
	protected JLabel lDescripcion = new JLabel("Descripcion:");
	protected JTextArea tDescripcion = new JTextArea();
	protected JLabel lFechaEFinalizacion = new JLabel("Fecha E. Finalizacion:");
	protected JDateChooser tFechaEFinalizacion = new JDateChooser();
	protected JButton bcancelar = new JButton("Cancelar");
	protected JButton baceptar = new JButton("Aceptar");
	protected JScrollPane scroll = new JScrollPane();
	protected JPanel panelDeAccion = new JPanel();
	protected List<AdministradorDeTareas> obser = new ArrayList<AdministradorDeTareas>();
	protected CrearTarea padre;
	protected Proyecto proyectoActual;
	protected JButton agregarTareas = new JButton("Agregar Tarea");

	public PanelTarea(AdministradorDeTareas adm, CrearTarea crearTarea) {
		this.proyectoActual = adm.getProyectoActual();
		this.padre = crearTarea;
		obser.add(adm);
		this.init();

	}

	public void init() {

		this.setLayout(new GridLayout(3, 1));
		pform.setLayout(new GridLayout(6, 2));
		panelDeAccion.setLayout(new FlowLayout());

		scroll.setViewportView(tDescripcion);
		scroll.setAutoscrolls(false);

		pform.add(lNombre);
		pform.add(tNombre);
		pform.add(Box.createVerticalStrut(20));
		pform.add(Box.createVerticalStrut(20));
		pform.add(lDescripcion);
		pform.add(scroll);
		pform.add(Box.createVerticalStrut(20));
		pform.add(Box.createVerticalStrut(20));
		pform.add(lFechaEFinalizacion);
		pform.add(tFechaEFinalizacion);
		this.add(pform);
		this.agregarTareas = this.inicializarBoton();
		panelDeAccion.add(baceptar);
		panelDeAccion.add(agregarTareas);
		panelDeAccion.add(bcancelar);
		this.add(panelDeAccion);
		this.add(Box.createVerticalStrut(100));
		this.addAction();
		this.setSize(400, 400);
	}

	public abstract void conOrden();

	public abstract JButton inicializarBoton();

	public void addAction() {

		baceptar.addActionListener(new MiActionAgregarListener());
		bcancelar.addActionListener(new MiActionCancelarListener());
		agregarTareas.addActionListener(new MiActionAgregarTareaListener());

	}

	class MiActionAgregarTareaListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			onAgregarT();
		}
	}

	class MiActionAgregarListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			onAcept();
		}

	}

	public abstract void onAgregarT();

	protected void notifyObserver() {
		for (AdministradorDeTareas adm : this.obser) {
			adm.seAgregoTarea();
		}

	}

	public abstract void onAcept();

	class MiActionCancelarListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if (JOptionPane.showConfirmDialog(bcancelar,
					"Desea cancelar la operacion?") == 0) {
				tDescripcion.setText("");
				tNombre.setText("");
				tFechaEFinalizacion.setDate(null);

			}
		}
	}

	

}
