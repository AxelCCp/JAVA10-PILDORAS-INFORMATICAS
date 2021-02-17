package X2_210_CONTROLADOR;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import X0_210_MODELO.A2_CargaMenu;
import X1_210_VISTA.A0_MarcoApp;

//WINDOWADAPTER, IMPLEMENTA LA INTERFAZ WINDOWLISTENER.
//SOBREESCRIBIMOS EL METODO WINDOWOPENED(),  QUE VIENE CON LA CLASE WINDOWADAPTER. 
//ESTE MÉTODO RECIVE UN PARÁMETRO DE TIPO WINDOWEVENT.

public class A1_ControladorCargaMenu extends WindowAdapter{
	
	
	//CONSTRUCTOR
	//PARA CARGAR EL JCOMBOBOX QUE ESTÁ EN LA CLASE A0_MarcoApp, 
	//SE NECESITA PASAR A ESTA CLASE, UN ARGUMENTO DE TIPO A0_MarcoApp.
	
	
	public A1_ControladorCargaMenu(A0_MarcoApp elMarco) {
	
		this.elMarco = elMarco;
		
	}
	
	
	
	//ESTE MÉTODO EJECUTA SU CÓDIGO AL ABRIR UNA VENTANA,  UN JFRAME.
	//ESTE METODO TIENE QUE EJECUTAR LA CONSULTA SQL.
	public void windowOpened(WindowEvent e) {
		
		//LLAMAMOS AL MÉTODO QUE EJECUTA LA CONSULTA, PARA CARGAR EL JCOMBOBOX
		obj.ejecutaConsultas();
		
		
		//RECORREMOS EL RESULTSET DE LA CLASE A1_CARGASECCIONES
		try {
			
			//rs VARIABLE QUE ALMACENA EL RESULTADO DE LA CONSULTA A LAS DIFERENTES SECCIONES..
			//EN LA CLASE A1_CARGASECCIONES. rs ES PUBLIC.
			
			while(obj.rs.next()) {
				//"A CADA VUELTA DE BUCLE, AGREGAMOS AL JCOMBOBOX DEL MARCO, LOS ITEMS" ..
				//"(CON OBJ USAMOS LA VARIABLE rs Y OBTENEMOS EL STRING DE LA COLUMNA 1)".
				elMarco.secciones.addItem(obj.rs.getString(1));
			}
			
			while(obj.rs2.next()) {
				elMarco.paises.addItem(obj.rs2.getString(1));
			}
			
			
		}catch(Exception e2){
			
			e2.printStackTrace();
			
		}
		
	}

	
	
	//--------------CAMPOS DE CLASE----------------
	
	//DECLARAMOS E INICIAMOS
	A2_CargaMenu obj = new A2_CargaMenu();
	//DECLARAMOS
	private A0_MarcoApp elMarco; 
}
