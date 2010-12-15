package ventanaTarea;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import tareas.AdministradorTarea;

public class TareaTablaModel extends AbstractTableModel {

	private List<AdministradorTarea> listTareas;
	private int rowCount = 0;

	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int col) {
		if (col == 0)
			return "Nombre";
		else
			return "Estado Actual";

	}

	public void setData(List<AdministradorTarea> list) {
		this.listTareas = list;
		this.rowCount = this.listTareas.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (col == 0)
			return ((AdministradorTarea) this.listTareas.get(row)).getNombre();
		else
			return ((AdministradorTarea) this.listTareas.get(row))
					.verEstado();
	}

	public int getRowCount() {
		return this.rowCount;
	}

	public void add(AdministradorTarea a) {
		this.listTareas.add(a);
		this.rowCount++;
	}

	public AdministradorTarea getSelected(int row) {
		return (AdministradorTarea) this.listTareas.get(row);
	}

	public void remove(AdministradorTarea tareaSeleccionada) {
		this.listTareas.remove(tareaSeleccionada);
		this.rowCount--;
	}

}
