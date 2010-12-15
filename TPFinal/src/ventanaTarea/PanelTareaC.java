package ventanaTarea;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tareas.AdministradorTarea;
import tareas.OrganizadorTarea;
import tareas.TareaCompuesta;
import usuarioMiembroYFecha.Fecha;

public class PanelTareaC extends PanelTarea {

	private TareaCompuesta tarea;

	public PanelTareaC(AdministradorDeTareas adm, CrearTarea crearTarea) {
		super(adm, crearTarea);
		this.tarea = new TareaCompuesta();

	}
	/**
	 * Declaro la accion del boton aceptar en el panel de Compuesta.
	 */
	public void onAcept() {
		if (!(tNombre.getText().isEmpty())
				& !(tDescripcion.getText().isEmpty())
				& !(tFechaEFinalizacion.getDate() == null)) {
			if (JOptionPane.showConfirmDialog(baceptar, "Esta seguro?") == 0) {
				tarea.setNombre(tNombre.getText());
				tarea.setDescripcion(tDescripcion.getText());
				Fecha fecha = new Fecha();
				fecha.setFecha(tFechaEFinalizacion.getDate());
				if (padre.rBConOrden.isSelected()) {
					if (padre.tabla.getSelectedRow() < 0) {
						JOptionPane
								.showMessageDialog(null,
										"Tiene que seleccionar la siguiente tarea Para poder completar la operacion.");
					} else {
						AdministradorTarea tareaAnterior = padre.model
								.getSelected(padre.tabla.getSelectedRow());
						this.proyectoActual.agregarTarea(new OrganizadorTarea(
								tareaAnterior, tarea));
						this.notifyObserver();
						this.padre.dispose();
					}
				} else {
					this.proyectoActual.agregarTarea(tarea);
					this.notifyObserver();
					this.padre.dispose();
				}

			}
		} else {

			JOptionPane.showMessageDialog(baceptar,
					"Tiene que todos completar los campos");
		}

	}
	/**
	 *  inicializo el boton agregar, en este caso lo quiero visible.
	 */
	public JButton inicializarBoton() {
		agregarTareas.setVisible(true);
		return agregarTareas;
	}
	/**
	 * le agrego la accion al boton agregar.
	 */
	public void onAgregarT() {
		new AsignarTareas(padre, tarea);

	}
	/**
	 * 
	 */
	public void conOrden() {

	}

}
