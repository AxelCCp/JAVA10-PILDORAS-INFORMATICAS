package X1_210_VISTA;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import X2_210_CONTROLADOR.A1_ControladorCargaMenu;
import X2_210_CONTROLADOR.A2_ControladorBotonEjecuta;

public class A0_MarcoApp extends JFrame{
	
	public A0_MarcoApp(){
		
		setTitle ("Consulta BBDD");
		setBounds(500,300,400,400);
		setLayout(new BorderLayout());
		
		JPanel menus=new JPanel();
		menus.setLayout(new FlowLayout());
		
		secciones=new JComboBox();
		secciones.setEditable(false);
		secciones.addItem("Todos");
		
		paises=new JComboBox();
		paises.setEditable(false);
		paises.addItem("Todos");
		
		resultado= new JTextArea(4,50);
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(secciones);
		menus.add(paises);	
		
		add(menus, BorderLayout.NORTH);
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta=new JButton("Consulta");	
		add(botonConsulta, BorderLayout.SOUTH);
		
		//PONEMOS EL BOTON A LA ESCUCHA
		//EL CONSTRUCTOR DE A2_ControladorBotonEjecuta, RECIBE POR PARÁMETRO A LA CLASE A0_MarcoApp, DE ESTA MANERA,..
		//LA CLASE A2_ControladorBotonEjecuta PUEDE IDENTIFICAR LO QUE HA SELECCIONADO EL USUARIO.
		botonConsulta.addActionListener(new A2_ControladorBotonEjecuta(this));
		
		
		
//------ PONEMOS EL MARCO A LA ESCUCHA DE LO PROGRAMADO EN CLASS A1_ControladorCargaSecciones --------
		
		//USAMOS ADDWINDOWLISTENER E INSTANCIAMOS DIRECTAMENTE LA CLASE INDICADA.
		//AL CONSTRUCTOR DE LA CLASE A1_ControladorCargaSecciones, LE PASAMOS "this",..
		//para decirle que estamos poniendo a la clase A0_MarcoApp, a la escucha.
		
		addWindowListener(new A1_ControladorCargaMenu(this));
		
//---------------------------------------------------------------------------	
		
	}
	
	//JCOMBOBOX SECCIONES ES PUBLIC PQ HAY QUE ACCEDER A ESTA DESDE OTRO PAQUETE. DESDE CLASS CONEXIÓN
	public JComboBox secciones;
	
	public JComboBox paises;
	
	public JTextArea resultado;
}
