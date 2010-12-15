package ventanaTarea;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
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
	// modelo
	protected List<AdministradorDeTareas> obser = new ArrayList<AdministradorDeTareas>();
	protected CrearTarea padre;
	protected Proyecto proyectoActual;
	// componentes
	protected JPanel pform = new JPanel();
	protected JLabel lNombre = new JLabel("Nombre:");
	protected JTextField tNombre = new JTextField();
	protected JLabel lDescripcion = new JLabel("Descripcion:");
	protected JTextArea tDescripcion = new JTextArea(); 
	protected JLabel lFechaEFinalizacion = new JLabel("Fecha E. Finalizacion:");
	protected JDateChooser tFechaEFinalizacion = new JDateChooser();
	protected JButton bcancelar = new JButton("Cancelar");
	protected JButton baceptar = new JButton("Aceptar");
	protected JButton bVolver = new JButton("Atras");
	protected JScrollPane scroll = new JScrollPane();
	protected JPanel panelDeAccion = new JPanel();
	protected JButton agregarTareas = new JButton("Agregar Tarea");

	public PanelTarea(AdministradorDeTareas adm, CrearTarea crearTarea) {
		this.proyectoActual = adm.getProyectoActual();
		this.padre = crearTarea;
		obser.add(adm);
		this.init();

	}
	/**
	 * Inicializa la ventana,con sus propiedades especificas, crea sus
	 * componentes, y hasta sus acciones.
	 */
	public void init() {
		// seteo propiedades tanto del Panel, como los demas paneles.
		this.setLayout(new GridLayout(3, 1));
		pform.setLayout(new GridLayout(6, 2));
		panelDeAccion.setLayout(new FlowLayout());
		
		scroll.setViewportView(tDescripcion);
		scroll.setAutoscrolls(false);
		// agrego los componentes al panel de formato
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
		// agrego el panel de formato al panel
		this.add(pform);
		// inicializo el boton
		this.agregarTareas = this.inicializarBoton();
		// agrego los botones al panel de acciones
		panelDeAccion.add(baceptar);
		panelDeAccion.add(agregarTareas);
		panelDeAccion.add(bcancelar);
		panelDeAccion.add(bVolver);
		// agrego el panel de acciones al Panel
		this.add(panelDeAccion);
		this.add(Box.createVerticalStrut(100));
		// agrego las acciones al Panel
		this.addAction();
		// seteo su tamaño
		this.setSize(400, 400);
	}

	
	/**
	 * Metodo abstracto.
	 */
	public abstract JButton inicializarBoton();
	
	/**
	 * Metodo abstracto.
	 */
	public abstract void onAcept();
	/**
	 * Metodo abstracto.
	 */
	public abstract void onAgregarT();
	
	/**
	 * Declara las acciones de los botones y otros componentes.
	 */
	
	public void addAction() {

		baceptar.addActionListener(new ActionAgregarListener());
		bcancelar.addActionListener(new ActionCancelarListener());
		agregarTareas.addActionListener(new ActionAgregarTareaListener());
		bVolver.addActionListener(new ActionVolverListener());
	}
	/**
	 * Notifica de que se a agregado una tarea a los observer.
	 */
	protected void notifyObserver() {
		for (AdministradorDeTareas adm : this.obser) {
			adm.seAgregoTarea();
		}

	}
	/**
	 * Clase que implementa ActionListener para el boton cancelar.
	 * 
	 * @author susy
	 * 
	 */
	class ActionVolverListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			padre.dispose();
		}
	}
	/**
	 * Clase que implementa ActionListener para el boton AgregarTareas.
	 * 
	 * @author susy
	 * 
	 */
	class ActionAgregarTareaListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			onAgregarT();
		}
	}
	/**
	 * Clase que implementa ActionListener para el boton Aceptar.
	 * 
	 * @author susy
	 * 
	 */
	class ActionAgregarListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			onAcept();
		}

	}

	
	/**
	 *  Clase que implementa ActionListener para el boton cancelar.
	 * 
	 * @author susy
	 */
	class ActionCancelarListener implements ActionListener {
		
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
