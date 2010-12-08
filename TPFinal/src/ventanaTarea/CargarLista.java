package ventanaTarea;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

public class CargarLista extends JFrame {

	private DefaultTableModel musicTablaModel = new DefaultTableModel();
	private JTable musicTable = new JTable(musicTablaModel);
	private DefaultTableModel listModel = new DefaultTableModel();
	private JTable jList = new JTable(listModel);
	private JButton aceptar = new JButton("Aceptar");
	private JButton cancelar = new JButton("Cancelar");
	private FlowLayout layout = new FlowLayout();

	public CargarLista() {
		this.setLayout(layout);
		pack();
		this.addCopones();
		this.addActions();
		this.setSize(470, 350);
		setVisible(true);

	}

	private void addActions() {
		jList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	protected void addCopones() {
		JScrollPane scrollList = new JScrollPane(jList);
		JScrollPane scrollTable = new JScrollPane(musicTable);
		GroupLayout layout = new GroupLayout(this.getContentPane());
		this.setLayout(layout);
		
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addGroup(layout.createSequentialGroup()
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(scrollList,-1,200,200)
								.addGap(11, 11, 11)
								.addComponent(scrollTable,-1,300,300))
				.addGap(11, 11, 11)
				.addGroup(layout.createSequentialGroup()
								.addComponent(aceptar)
								.addGap(11, 11, 11)
								.addComponent(cancelar))
		);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
								.addComponent(scrollList,-1,200,200)
								.addGap(11, 11, 11)
								.addComponent(scrollTable,-1,200,200))
				.addGap(11, 11, 11)
				.addGroup(layout.createParallelGroup()
								.addComponent(aceptar)
								.addGap(11, 11, 11)
								.addComponent(cancelar)
				)
		);
		

	}


	public static void main(String[] args) {
		new CargarLista();
	}

}
