package ventanaTarea;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class TablaAdaptada extends JPanel {


	private JScrollPane scroll = new JScrollPane();
	private JTable tabla;


	public TablaAdaptada(TableModel modelo) {
		super(new GridBagLayout());
		this.tabla = new JTable();
		this.tabla.setModel(modelo);
		construyeVentana();

	}

	/**
	 * Crea los componentes de este panel. Un JScrollPane, el JTable que va
	 * dentro y dos JButton para añadir y quitar elementos del JTable.
	 */
	private void construyeVentana() {
		// Para poner las restricciones a los componentes (posicioes dentro
		// del panel)
		GridBagConstraints restricciones = new GridBagConstraints();

		// Un JScrollPane con el JTable dentro.
		// Las restricciones...
		restricciones.gridx = 1;
		restricciones.gridy = 1;
		restricciones.gridwidth = GridBagConstraints.REMAINDER;
		restricciones.fill = GridBagConstraints.BOTH;
		restricciones.weightx = 2.0;
		restricciones.weighty = 2.0;
		// restricciones.anchor = GridBagConstraints.EAST;

		// Se crea el JScrollPane, el JTable y se pone la cabecera...

		scroll.setOpaque(false);
		scroll.setViewportView(tabla);
		scroll.setColumnHeaderView(tabla.getTableHeader());

		// ... y se añade todo al panel
		this.add(scroll, restricciones);
	}

	public JScrollPane getScroll() {
		return scroll;
	}


}
