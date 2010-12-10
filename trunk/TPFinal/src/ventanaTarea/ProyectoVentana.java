package ventanaTarea;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;

import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


import proyectoYSistema.Proyecto;
import proyectoYSistema.Sistema;
import usuarioMiembroYFecha.Usuario;

public class ProyectoVentana extends JPanel{
	

	//las variables que se encargan de manejar la lista
	private Vector<Proyecto> listaProyectosVector = new Vector<Proyecto>();
	private JList listaProyectosJList;
	private JScrollPane scrollProyectos = new JScrollPane();
	//el panel de datos de proyecto seleccionado
	private JPanel panelDatos = new JPanel();
	private TextField textoNombreProyecto = new TextField();
	private TextField textoDescripcionProyecto = new TextField();
	private JLabel labelNombreProyecto = new JLabel("NombreProyecto");
	private JLabel labelDescripcionProyecto = new JLabel("DescripcionProyecto");

	private Sistema sistema;
	//botones de la pesta�a proyecto 
	private JPanel panelBotones = new JPanel();
	private JButton crear = new JButton("crear");
	private JButton modificar = new JButton("modificar");
	private JButton eliminar = new JButton("eliminar");
//	private JButton reabrir = new JButton("reabrir");
//	private JButton cerrar = new JButton("cerrar");
	
	
	public ProyectoVentana() {
	
		Vector<Proyecto> losProyectos = new Vector<Proyecto>();
		// los proyectos para probar 
		losProyectos.add(new Proyecto("pro1", "y nose...", new Usuario("shrek", "ogro@hotmail.com")));
		losProyectos.add(new Proyecto("pro2", "algo", new Usuario("el cuervo", "witre@hotmail.com")));
		losProyectos.add(new Proyecto("pro3", "termineitor", new Usuario("nokia", "celular@hotmail.com")));
		losProyectos.add(new Proyecto("pro4", "homero", new Usuario("Simpon", "tv@hotmail.com")));
		
		this.listaProyectosVector = losProyectos;
	
		inicializarVentana();
	}
	
	public ProyectoVentana(Sistema sis){
		sistema = sis;
		this.listaProyectosVector = sis.getProyectos();
		inicializarVentana();
		
	}
	
	private void inicializarVentana() {

		//le creo una Jlist con el vector a mostrar
		listaProyectosJList = new JList(listaProyectosVector);
		//seteo el scroll para la Jlist
		scrollProyectos.setViewportView(listaProyectosJList);
		listaProyectosJList.setVisibleRowCount(4);
		
		
		this.listaProyectosJList.setBackground(Color.ORANGE);
		
				// aca compongo y seteo todos los paneles
		
		//el panel de los botones 
		panelBotones.setLayout(new BoxLayout(panelBotones,BoxLayout.Y_AXIS));
		panelBotones.add(crear);
		panelBotones.add(modificar);
		panelBotones.add(eliminar);
		
		modificar.setEnabled(false);
		eliminar.setEnabled(false);
		
		//el panel de datos
		panelDatos.setLayout(new GridLayout(4, 2));
		panelDatos.add(labelNombreProyecto);
		panelDatos.add(textoNombreProyecto);
		panelDatos.add(labelDescripcionProyecto);
		panelDatos.add(textoDescripcionProyecto);
		
		//agrego los renderers
		listaProyectosJList.setCellRenderer(new ProyectoRenderer());
		
		///  componer panels 
		this.setLayout(new GridLayout(2,2));
		this.add(scrollProyectos);
		this.add(panelBotones);
		this.add(panelDatos);
		
	
		//agrego las acciones de la lista y de los botones de proyecto
		this.addAcions();

	}

		protected void 	addAcions() {
			listaProyectosJList.addListSelectionListener(new SeleccionarElemento());
		
			listaProyectosJList.addMouseListener(new DeseleccionarElemento() );
				
			crear.addActionListener(new CrearProyecto() );
		
			modificar.addActionListener(new ModificarProyecto());
			
			eliminar.addActionListener(new EliminarProyecto());
		}

		class SeleccionarElemento implements ListSelectionListener{
			
				
				public void valueChanged(ListSelectionEvent e) {
					try {
							
						if (listaProyectosJList.isSelectionEmpty()) {
							modificar.setEnabled(false);
							eliminar.setEnabled(false);
						} else {
							Proyecto proyecto = (Proyecto) listaProyectosJList.getSelectedValue();
								textoNombreProyecto.setText(proyecto.getNombre());
								textoDescripcionProyecto.setText(proyecto.getDescripcion());
								modificar.setEnabled(true);
								eliminar.setEnabled(true);
							
						}
							
					} catch (NullPointerException ex) {	
					}
					
				}
			}
		class DeseleccionarElemento implements MouseListener{
		
		public void mouseClicked(MouseEvent e) {
			
	        if (e.getClickCount() == 2) {
	           listaProyectosJList.clearSelection();
	           textoNombreProyecto.setText("");
	            textoDescripcionProyecto.setText("");
	         }
		}

		public void mouseEntered(MouseEvent arg0) {
		}

		public void mouseExited(MouseEvent arg0) {		
		}
		
		public void mousePressed(MouseEvent arg0) {
		}
		
		public void mouseReleased(MouseEvent arg0) {
		}
		
		}
		
		class CrearProyecto implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				//	JFrame ventanaAux = new JFrame("ventana para crear");
					// JDialog ventanaAux = new Dialog(arg0)
					
					//el usuario es null porque no creamos la interfaz visual
					//con la diferencia de estar "logeado" con un usuario server el cual
					//seria el creador.
					Proyecto proyecto = new Proyecto(textoNombreProyecto.getText(), textoDescripcionProyecto.getText(), null);
					listaProyectosVector.add(proyecto);
					listaProyectosJList.setListData(listaProyectosVector);
				}
			
		}
		
		class ModificarProyecto implements ActionListener{

			public void actionPerformed(ActionEvent e) {
			// tiene que abrir otra ventana
				
				
				new ModificarProyectoVentana((Proyecto) listaProyectosJList.getSelectedValue());
				
						
			}
		}
		class EliminarProyecto implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				
				
				
			listaProyectosVector.remove(listaProyectosJList.getSelectedValue());
			listaProyectosJList.setListData(listaProyectosVector);	
			}
		}

}
