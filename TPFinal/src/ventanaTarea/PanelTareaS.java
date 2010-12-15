package ventanaTarea;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tareas.AdministradorTarea;
import tareas.OrganizadorTarea;
import tareas.TareaSimple;
import usuarioMiembroYFecha.Fecha;

public class PanelTareaS extends PanelTarea {

	public PanelTareaS(AdministradorDeTareas adm, CrearTarea crearTarea) {
		super(adm, crearTarea);

		this.agregarTareas.setVisible(false);

	}

	public void onAcept() {

		if (!(tNombre.getText().isEmpty())
				& !(tDescripcion.getText().isEmpty())
				& !(tFechaEFinalizacion.getDate() == null)) {
			if (JOptionPane.showConfirmDialog(baceptar, "¿Esta seguro?") == 0) {
				String nombre = tNombre.getText();
				String descr = tDescripcion.getText();
				Fecha fecha = new Fecha();
				fecha.setFecha(tFechaEFinalizacion.getDate());
				TareaSimple tarea = new TareaSimple(nombre, descr, fecha);
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

	public JButton inicializarBoton() {
		this.agregarTareas.setVisible(false);
		return agregarTareas;
	}

	public void onAgregarT() {

	}

	public void conOrden() {

	}

}