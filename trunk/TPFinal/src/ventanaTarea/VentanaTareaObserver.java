package ventanaTarea;

import tareas.AdministradorTarea;

public interface VentanaTareaObserver {

	void cambioElEstadoLaTarea(AdministradorTarea unaTarea);

	void seCerroLaTarea(AdministradorTarea tarea);

	void seReabrioLaTarea(AdministradorTarea tarea);
}
