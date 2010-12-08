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

	@Override
	public void onAcept() {

		if (!(tNombre.getText().isEmpty())
				& !(tDescripcion.getText().isEmpty())
				& !(tFechaEFinalizacion.getDate() == null)) {
			if (JOptionPane.showConfirmDialog(baceptar, "�Esta seguro?") == 0) {
				String nombre = tNombre.getText();
				String descr = tDescripcion.getText();
				Fecha fecha = new Fecha();
				fecha.setFecha(tFechaEFinalizacion.getDate());
				TareaSimple tarea = new TareaSimple(nombre, descr, fecha);
				if (padre.cBConOrden.isSelected()) {
					AdministradorTarea tareaAnterior = padre.model
							.getSelected(padre.tabla.getSelectedRow());
					this.proyectoActual.agregarTarea(new OrganizadorTarea(
							tareaAnterior, tarea));
				} else {
					this.proyectoActual.agregarTarea(tarea);
				}
				this.notifyObserver();
				this.padre.dispose();

			}
		} else {

			JOptionPane.showMessageDialog(baceptar,
					"Tiene que todos completar los campos");
		}
	}

	@Override
	public JButton inicializarBoton() {
		this.agregarTareas.setVisible(false);
		return agregarTareas;
	}

	@Override
	public void onAgregarT() {

	}

	@Override
	public void conOrden() {
		// TODO Auto-generated method stub

	}

}