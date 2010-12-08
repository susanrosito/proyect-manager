package ventanaTarea;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import tareas.AdministradorTarea;
import tareas.OrganizadorTarea;
import tareas.TareaCompuesta;
import usuarioMiembroYFecha.Fecha;



public class PanelTareaC extends PanelTarea {

	private TareaCompuesta tarea;

	public PanelTareaC(AdministradorDeTareas adm,
			CrearTarea crearTarea) {
		super(adm, crearTarea);
		this.tarea = new TareaCompuesta();

	}

	@Override
	public void onAcept() {
		if (!(tNombre.getText().isEmpty())
				& !(tDescripcion.getText().isEmpty())
				& !(tFechaEFinalizacion.getDate() == null)) {
			if (JOptionPane.showConfirmDialog(baceptar, "Esta seguro?") == 0) {
				tarea.setNombre(tNombre.getText());
				tarea.setDescripcion(tDescripcion.getText());
				Fecha fecha = new Fecha();
				fecha.setFecha(tFechaEFinalizacion.getDate());
				if (padre.cBConOrden.isSelected()){
					AdministradorTarea tareaAnterior = padre.model.getSelected(padre.tabla.getSelectedRow());
					this.proyectoActual.agregarTarea(new OrganizadorTarea(tareaAnterior, tarea));
				}
				this.proyectoActual.agregarTarea(tarea);
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
		agregarTareas.setVisible(true);
		return agregarTareas;
	}

	@Override
	public void onAgregarT() {
		new AsignarTareas(padre, tarea);

	}

	@Override
	public void conOrden() {
		
		
	}

	
}
