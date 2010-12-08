package ventanaTarea;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public abstract class VentanaConfirmacion extends JFrame{

protected	JPanel panelDatos;
protected	JLabel labelTexto1;
protected	JTextField campoTexto1;
protected	JLabel labelTexto2;
protected	JTextField campoTexto2;
protected	JButton aceptar;
protected	JButton cancelar;

	public VentanaConfirmacion() {
	
		
	}


	public void init() {
	
		panelDatos.setLayout(new GridLayout(4, 2));
		panelDatos.add(labelTexto1);
		panelDatos.add(campoTexto1);
		panelDatos.add(labelTexto2);
		panelDatos.add(campoTexto2);
		aceptar = new JButton("aceptar");
		cancelar= new JButton("cancelar");
		 
				 
		JPanel botones = new JPanel();
		botones.add(aceptar);
		botones.add(cancelar);
				
		this.add(panelDatos);
		this.add(botones);

		this.dispose();
		this.pack();
		this.setVisible(true);
		
		actionsDefault();
	}

	public void actionsDefault() {
		cancelar.addActionListener(new cancelarAccion());
		aceptar.addActionListener(new AceptarAccion());
	}
	
	public abstract void onAceptar();	
	
	class AceptarAccion implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
			onAceptar();
		}
		
		
	}
	class cancelarAccion implements ActionListener
	{

		public void actionPerformed(ActionEvent e) {
		dispose();
		}
		
		
	}
	
	
}
