package ventanaTarea;
import javax.swing.JFrame;


	import java.awt.Color;
	import java.awt.FlowLayout;
	import java.awt.Font;
	import java.awt.GridLayout;
	import java.awt.TextArea;
	import java.awt.TextField;
	import java.awt.event.ActionEvent;
	import java.util.List;
	import javax.swing.AbstractAction;
	import javax.swing.BoxLayout;
	import javax.swing.JButton;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import estados.NoPuedeCambiarseElEstadoExcepccion;
import tareas.AdministradorTarea;
import tareas.TareaSimple;
	import usuarioMiembroYFecha.Fecha;
	import usuarioMiembroYFecha.Miembro;
import usuarioMiembroYFecha.Usuario;

	public class VentanaCerrarTarea extends JFrame{


		private AdministradorTarea tarea;
		private List<VentanaTareaObserver> observadores;

        private JLabel lMotivo = new JLabel("Motivo:");
		private TextArea elMotivo = new TextArea("");
		private JPanel motivo_elMotivo= new JPanel(); 
		

		public VentanaCerrarTarea(AdministradorTarea at, List<VentanaTareaObserver> observadores) {
			this.tarea = at;
			this.observadores=observadores;
			this.init();
		}
		
	
		
		 
		 private void init() {
		    
			
			
			this.setBackground(Color.BLACK);
			this.setForeground(Color.WHITE);
			
			this.setTitle("Cierre de la tarea: " + this.tarea.getNombre()+ ".");
			this.elMotivo=new TextArea("",0,0, 1);
			this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
			
			 
			
			//this.motivo_elMotivo.setLayout(new GridLayout(2, 1));
			this.motivo_elMotivo.add(lMotivo);
			lMotivo.setForeground(Color.LIGHT_GRAY);
			this.motivo_elMotivo.add(elMotivo);
			motivo_elMotivo.setBackground(Color.BLACK);
			
			
			this.setSize(400, 200);	
			this.setResizable(false);
			
		//	this.pack();
			this.add(motivo_elMotivo);
			
			this.setVisible(true);
		}




		public static void main(String[] args) {
			TareaSimple ts=new TareaSimple("Hola","lero lero",new Fecha());
			Usuario u= new Usuario("Ezequiel","n@unq");
			Miembro m= new Miembro(u, "Barrendero");
			ts.setMiembroAsignado(m);
			//	new VentanaTareas(ts,null);
				new VentanaCerrarTarea(ts, null);
		}
	}

