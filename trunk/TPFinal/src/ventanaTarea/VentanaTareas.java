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





public class VentanaTareas extends JFrame {

	//Variable para la tarea sobre la cual se abre la ventana
	private AdministradorTarea tarea;
	private VentanaTareaObserver observador;
	
	
	
	private JLabel lfechaCreacion = new JLabel("Fecha de creación:");
	
	private JLabel lafechaC;
	private JPanel JPpCreacion = new JPanel(new GridLayout(1, 1));
	
	
	private JLabel lfechaEstimadaFinalizacion = new JLabel("Fecha estimada de finalización:");
	private JLabel lafechaEstimada;
	private JPanel JPFinalizacion = new JPanel(new GridLayout(1, 1));
	
	
	private JLabel lMiembroAsignado = new JLabel("Miembro asignado:");
	private JLabel elMiembroAsignado;
	private JPanel JPMiembro = new JPanel(new GridLayout(1, 1));
	
	private JLabel lDescripccion = new JLabel("Descripccion:");
	private TextArea laDescripccion;
	private JPanel JPDesc = new JPanel(new FlowLayout());
	private JPanel JPDesc2 = new JPanel(new FlowLayout());
	
	
	private JLabel lPorcentaje = new JLabel("Porcentaje finalizaci�n:");
	private TextField elPorcentaje = new TextField();
	
	
	private JPanel JPContenedor_Estados_Porcentaje = new JPanel();
	private JPanel JPporcentage = new JPanel(new FlowLayout());
	
	private JLabel lEstadoActual = new JLabel("Estado actual:");
	private TextField elEstadoActual;
	
	private JPanel JPestado = new JPanel(new FlowLayout());
	
	private JLabel lMotivo = new JLabel("Motivo:");
	private TextField elMotivo = new TextField("");
	
	
	private JPanel JPreabrir_cerrar = new JPanel();
	private JPanel JPmotivo_descrippcion = new JPanel();
	private JPanel JPcerrar_reabrir_motivo = new JPanel();
	
	private JScrollPane scroll = new JScrollPane();
	
	private JButton aumentar = new JButton("Aumentar");
	private JButton enTrabajo = new JButton("En trabajo");
	private JButton pausar = new JButton("Pausar");
	private JButton finalizar = new JButton("Finalizar");
	private JButton iniciar = new JButton("Iniciar");
	private JButton reabrir = new JButton("Reabrir tarea");
	private JButton cerrar = new JButton("Cerrar tarea");
	private JButton volver = new JButton("Volver");
	
	
	
	public VentanaTareas(AdministradorTarea at, VentanaTareaObserver obs) {
		this.tarea = at;
		this.observador=obs;
		this.init();
	}
	
	public void init() {
		
		lfechaCreacion.setFont(new Font("Arial", 3,14));
		lfechaCreacion.setForeground(Color.LIGHT_GRAY);
		lfechaEstimadaFinalizacion.setFont(new Font("Arial", 3,14));
		lfechaEstimadaFinalizacion.setForeground(Color.LIGHT_GRAY);
		lMiembroAsignado.setFont(new Font("Arial", 3,14));
		lMiembroAsignado.setForeground(Color.LIGHT_GRAY);
		lDescripccion.setFont(new Font("Arial", 3,14));
		lDescripccion.setForeground(Color.LIGHT_GRAY);
		
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);
		
		
		this.setTitle("Tarea: " + this.tarea.getNombre()+ ".");
		elPorcentaje.setText(String.valueOf(tarea.getPorcentajeFinalizacion()));
		elPorcentaje.setBackground(Color.DARK_GRAY);
		if(tarea.getPorcentajeFinalizacion()<40)
		{elPorcentaje.setForeground(Color.RED);}
		else {
		if(tarea.getPorcentajeFinalizacion()>=40 && tarea.getPorcentajeFinalizacion()<75)
		{elPorcentaje.setForeground(Color.BLUE);}
		else{elPorcentaje.setForeground(Color.GREEN);}	}
		
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		

		this.laDescripccion=new TextArea(tarea.getDescripcion(),0,0, 1);
		
		
		scroll.setViewportView(laDescripccion);
		
		this.laDescripccion.setEditable(false);
		this.laDescripccion.setFont(new Font("Arial", Font.BOLD,14));
		//AJUSTAR EL TEX AREA AL TEXTO
		
		this.lafechaC=new JLabel(this.tarea.getFechaCreacion().toString());
		lafechaC.setForeground(Color.WHITE);
		this.JPpCreacion.add(lfechaCreacion);
		this.JPpCreacion.add(lafechaC);
		JPpCreacion.setBackground(Color.BLACK);
		
		
		this.lafechaEstimada=new JLabel(this.tarea.getFechaEstimadaFinalizacion().toString());
		lafechaEstimada.setForeground(Color.WHITE);
		this.JPFinalizacion.add(lfechaEstimadaFinalizacion);
		this.JPFinalizacion.add(lafechaEstimada);
		JPFinalizacion.setBackground(Color.BLACK);
		
		
		this.elMiembroAsignado=new JLabel("");	
		this.JPMiembro.add(lMiembroAsignado);
		this.JPMiembro.add(elMiembroAsignado);
		JPMiembro.setBackground(Color.BLACK);
		
		this.JPDesc.setLayout(new GridLayout(4, 3)); 
		this.JPDesc.add(JPpCreacion);
		this.JPDesc.add(JPFinalizacion);
		this.JPDesc.add(JPMiembro);
		this.JPDesc.add(lDescripccion);
		JPDesc.setBackground(Color.BLACK);
		
		this.JPDesc2.add(scroll);
		
		JPDesc2.setBackground(Color.BLACK);
		
		this.add(JPDesc);
		this.add(JPDesc2);
		this.elEstadoActual=new TextField(this.tarea.verEstado());
		lEstadoActual.setForeground(Color.LIGHT_GRAY);
		elEstadoActual.setBackground(Color.DARK_GRAY);
		elEstadoActual.setForeground(Color.WHITE);
		
		elEstadoActual.setEditable(false);
	
		
		this.JPContenedor_Estados_Porcentaje.setLayout(new GridLayout(2, 1));
		
		this.JPporcentage.add(this.lPorcentaje);
		lPorcentaje.setForeground(Color.LIGHT_GRAY);
		this.JPporcentage.add(this.elPorcentaje);
		this.JPporcentage.add(this.aumentar);
		JPporcentage.setBackground(Color.BLACK);
	
		this.JPContenedor_Estados_Porcentaje.add(JPporcentage);
		
		
		this.JPestado.add(this.lEstadoActual);
		this.JPestado.add(this.elEstadoActual);
		this.JPestado.add(this.pausar);
		this.JPestado.add(this.enTrabajo);
		this.JPestado.add(this.finalizar);
		this.JPestado.add(this.iniciar);
		JPestado.setBackground(Color.BLACK);
		
		this.JPContenedor_Estados_Porcentaje.add(JPestado);
		this.add(JPContenedor_Estados_Porcentaje);
			
	
		this.JPmotivo_descrippcion.setLayout(new GridLayout(2, 1));
		this.JPmotivo_descrippcion.add(lMotivo);
		lMotivo.setForeground(Color.LIGHT_GRAY);
		this.JPmotivo_descrippcion.add(elMotivo);
		JPmotivo_descrippcion.setBackground(Color.BLACK);
		
		this.JPreabrir_cerrar.setLayout(new GridLayout(2, 1));
		JPanel prueba2=new JPanel(new GridLayout(2, 1));
		prueba2.add(cerrar);
		prueba2.add(reabrir);
		prueba2.setBackground(Color.BLACK);
		this.JPreabrir_cerrar.add(prueba2);
		
		JPanel prueba3=new JPanel(new GridLayout(2, 1));
		
		prueba3.setBackground(Color.BLACK);
		this.JPreabrir_cerrar.add(prueba3);
		
		this.JPcerrar_reabrir_motivo.setLayout(new GridLayout(1, 3));
		this.JPcerrar_reabrir_motivo.add(JPmotivo_descrippcion);
		this.JPcerrar_reabrir_motivo.add(JPreabrir_cerrar);
		
		JPanel prueba=new JPanel();
		prueba.add(volver);
		prueba.setBackground(Color.BLACK);
		this.JPcerrar_reabrir_motivo.add(prueba);
		
		this.add(JPcerrar_reabrir_motivo);
		this.addActions();

		
		
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);//Mata el proceso

	    if(!(this.tarea.getMiembroAsignado()==null))
		{
		this.elMiembroAsignado.setText(this.tarea.getMiembroAsignado().toString());	
	
		if(this.tarea.verEstado()=="Cerrada")
	    {cerrar.setEnabled(false);}
		else {reabrir.setEnabled(false);}
		if(this.tarea.verEstado()!="En Trabajo")
	    {pausar.setEnabled(false);}
		if(this.tarea.verEstado()!="Pausada"){
			 enTrabajo.setEnabled(false);}
		if(this.tarea.verEstado()!="Creada"){
			iniciar.setEnabled(false);
		if(this.tarea.verEstado()=="Iniciada"){
			  enTrabajo.setEnabled(true);	
			}}
		}
		else{ 
		this.elMiembroAsignado.setText("No hay un miembro asigado actualmente.");
		cerrar.setEnabled(false);
	    reabrir.setEnabled(false);
	    pausar.setEnabled(false);
	    enTrabajo.setEnabled(false);
	    iniciar.setEnabled(false);
	    aumentar.setEnabled(false);
	    finalizar.setEnabled(false);
		}
	    elMiembroAsignado.setForeground(Color.WHITE);
			
		
		this.setSize(500, 650);
		this.setResizable(true);
		this.pack();
		this.setVisible(true);
	}
	
	private void addActions()
	{
		
		finalizar.addActionListener(new FinalizarTareaAction());
		enTrabajo.addActionListener(new EnTrabajoTareaAction());
    	iniciar.addActionListener(new IniciarTareaAction()); 
		pausar.addActionListener(new PausarTareaAction()); 
		aumentar.addActionListener(new AumentarPorcentageDeFinalizacionAction()); 
		cerrar.addActionListener(new CerrarTareaAction()); 
		reabrir.addActionListener(new ReabrirTareaAction());
		volver.addActionListener(new VolverAction());
		
		}

	 class  CerrarTareaAction extends AbstractAction{

	

		@Override
		public void actionPerformed(ActionEvent e)  {
					if (JOptionPane.showConfirmDialog(cerrar, "�Esta seguro que desea cerrar la tarea?") == 0) {
						if(elMotivo.getText().isEmpty()){
							
							JOptionPane.showMessageDialog(cerrar,
									"Por favor, ingrese un motivo para el cierre" +
									" de la tarea.", "Imposible cerrar la tarea.",
									JOptionPane.INFORMATION_MESSAGE);
						}
						else{
						tarea.cerrate(elMotivo.getText());
						elEstadoActual.setText(tarea.verEstado());
						elMotivo.setText("");
						reabrir.setEnabled(true);
						cerrar.setEnabled(false);
						enTrabajo.setEnabled(false);
						pausar.setEnabled(false);
						iniciar.setEnabled(false);
						finalizar.setEnabled(false);
						aumentar.setEnabled(false);
						laDescripccion.setText(tarea.getDescripcion());
						elEstadoActual.setForeground(Color.RED);
						
						observador.seCerroLaTarea(tarea);
						
						
						JOptionPane.showMessageDialog(cerrar,
								"Operacion exitosa", "Cierre de la tarea.",
								JOptionPane.INFORMATION_MESSAGE);}
					}
				}
			
			
		}
		 		 
	 class  ReabrirTareaAction extends AbstractAction{

			

			@Override
			public void actionPerformed(ActionEvent e)  {
				
						if (JOptionPane.showConfirmDialog(reabrir, "�Esta seguro que desea reabrir la tarea?") == 0) {
							if(elMotivo.getText().isEmpty()){
								
								JOptionPane.showMessageDialog(cerrar,
										"Por favor, ingrese el motivo por el cual se reabre" +
										" la tarea.", "Imposible cerrar la tarea.",
										JOptionPane.INFORMATION_MESSAGE);
							}
							else{
							tarea.reAbrite(elMotivo.getText());
							elEstadoActual.setText(tarea.verEstado());
							elMotivo.setText("");
							reabrir.setEnabled(false);
							cerrar.setEnabled(true);
							iniciar.setEnabled(false);
							finalizar.setEnabled(true);
							aumentar.setEnabled(true);
							enTrabajo.setEnabled(true);
							laDescripccion.setText(tarea.getDescripcion());
							elEstadoActual.setForeground(Color.WHITE);
							observador.seReabrioLaTarea(tarea);
							
							
							JOptionPane.showMessageDialog(reabrir,
									"Operacion exitosa", "Reapertura de la tarea.",
									JOptionPane.INFORMATION_MESSAGE);
						
					}}
				}
				
				
			}
			 	 
	 class  PausarTareaAction extends AbstractAction{

			

			@Override
			public void actionPerformed(ActionEvent e)  {
				
				{

				
						try {
							tarea.pausate();
							elEstadoActual.setText(tarea.verEstado());
						    pausar.setEnabled(false);
						    enTrabajo.setEnabled(true);
						   observador.cambioElEstadoLaTarea(tarea);
							
						} catch (NoPuedeCambiarseElEstadoExcepccion e1) {
							JOptionPane.showMessageDialog(cerrar,
									"No puede cambiar ese estado por Pausado.", "Error.",
									JOptionPane.INFORMATION_MESSAGE);
						}
						
						
					}
				}}

	 class  EnTrabajoTareaAction extends AbstractAction{

			public void actionPerformed(ActionEvent e) {
				try {
					tarea.poneteEnTrabajo();
					elEstadoActual.setText("Trabajo");
				    pausar.setEnabled(true);
				    enTrabajo.setEnabled(false);
				    observador.cambioElEstadoLaTarea(tarea);
					
				} catch (NoPuedeCambiarseElEstadoExcepccion e1) {
				
					JOptionPane.showMessageDialog(cerrar,
							"No puede cambiar ese estado por En trabajo.", "Error.",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
		
		
		
	
				}
			 
	 class  IniciarTareaAction extends AbstractAction{


			
			public void actionPerformed(ActionEvent e) {
				try {
					tarea.iniciate();
					elEstadoActual.setText(tarea.verEstado());
				  iniciar.setEnabled(false);
				   enTrabajo.setEnabled(true);
				   observador.cambioElEstadoLaTarea(tarea);
					
				} catch (NoPuedeCambiarseElEstadoExcepccion e1) {
					JOptionPane.showMessageDialog(cerrar,
							"No puede cambiar ese estado por Iniciado.", "Error.",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
				
			}

	 class  FinalizarTareaAction extends AbstractAction{

			public void actionPerformed(ActionEvent e)  {
				

					
						if(!(tarea.getPorcentajeFinalizacion()<100))
						{
						tarea.finalizate();
						elEstadoActual.setText(tarea.verEstado());
						cerrar.setEnabled(false);
						reabrir.setEnabled(false);
						enTrabajo.setEnabled(false);
						pausar.setEnabled(false);
						iniciar.setEnabled(false);
						finalizar.setEnabled(false);
						aumentar.setEnabled(false);
						elEstadoActual.setForeground(Color.GREEN);
						
						observador.cambioElEstadoLaTarea(tarea);
						
						}
						else {JOptionPane.showMessageDialog(cerrar,
						"Para dar por finalizada la tarea primero debe tener el 100% de porcentaje de finalizacion.", "No puede finalizarse la tarea.",
								JOptionPane.INFORMATION_MESSAGE);}
						
					}}
	 
	 class  AumentarPorcentageDeFinalizacionAction extends AbstractAction{

			public void actionPerformed(ActionEvent e)  {
				

					

						float porcentajeAnterior=tarea.getPorcentajeFinalizacion();
						float porcentajeDeceado = Float.parseFloat(elPorcentaje.getText());
						if (porcentajeAnterior<porcentajeDeceado && porcentajeDeceado<=100)
						{
							tarea.setPorcentajeFinalizacion(porcentajeDeceado);
							elPorcentaje.setText(String.valueOf(porcentajeDeceado));
							if(tarea.getPorcentajeFinalizacion()<40)
							{elPorcentaje.setForeground(Color.RED);}
							else {
							if(tarea.getPorcentajeFinalizacion()>=40 && tarea.getPorcentajeFinalizacion()<75)
							{elPorcentaje.setForeground(Color.BLUE);}
							else{elPorcentaje.setForeground(Color.GREEN);}	}
							JOptionPane.showMessageDialog(cerrar,
									"Operacion exitosa", "Aumento de porcentaje de finalizaci�n.",
									JOptionPane.INFORMATION_MESSAGE);
							
						} else {
						
						elPorcentaje.setText(String.valueOf(porcentajeAnterior));
						JOptionPane.showMessageDialog(cerrar,
								"No puede ingresar ese porcentaje de finalizaci�n.", "Error.",
								JOptionPane.INFORMATION_MESSAGE);}
						
					}}
			
			class  VolverAction extends AbstractAction{

				public void actionPerformed(ActionEvent e)  {
					
                           dispose();				

				}
	 } 
	
	 
	 public static void main(String[] args) {
		TareaSimple ts=new TareaSimple("Hola","lero lero",new Fecha("2009-11-13"));
		Usuario u= new Usuario("Ezequiel","n@unq");
		Miembro m= new Miembro(u, "Barrendero");
		ts.setMiembroAsignado(m);
			new VentanaTareas(ts,null);
	}
}