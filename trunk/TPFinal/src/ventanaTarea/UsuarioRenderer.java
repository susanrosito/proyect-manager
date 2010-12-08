package ventanaTarea;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import usuarioMiembroYFecha.Usuario;



public class UsuarioRenderer extends JLabel implements ListCellRenderer {

	public UsuarioRenderer() {
		setOpaque(true);
	}

	public Component getListCellRendererComponent(JList lista, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		

		if (isSelected) {
		      setBackground(Color.GRAY);
		      setForeground(Color.GREEN);
		}
		else {
		      setBackground(lista.getBackground());
		      setForeground(lista.getForeground());
		    }
		this.setText(((Usuario) value).getNombre());
		
		
		
		return this;
	}

}
