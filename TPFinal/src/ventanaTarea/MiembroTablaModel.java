package ventanaTarea;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import usuarioMiembroYFecha.Miembro;

public class MiembroTablaModel extends AbstractTableModel {
	
	private List<Miembro> listMiembros;
	private int rowCount = 0;

	
	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int col) {
		if (col == 0)
			return "Nombre";
		else
			return "Rol Actual";
		
	}

	public void setData(List<Miembro> list) {
		this.listMiembros = list;
		this.rowCount = this.listMiembros.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (col == 0)
			return ((Miembro) this.listMiembros.get(row)).getUsuario().getNombre();
		else
			return ((Miembro) this.listMiembros.get(row)).getRol();
	}


	public int getRowCount() {
		return this.rowCount;
	}

	public void add(Miembro a) {
		this.listMiembros.add(a);
		this.rowCount++;
	}

	public Miembro getSelected(int row) {
		return (Miembro) this.listMiembros.get(row);
	}

	public void remove(Miembro miembroSeleccionado) {
		this.listMiembros.remove(miembroSeleccionado);
		this.rowCount--;
	}

}
