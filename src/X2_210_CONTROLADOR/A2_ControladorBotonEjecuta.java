package X2_210_CONTROLADOR;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import X0_210_MODELO.A3_EjecutaConsultas;
import X1_210_VISTA.A0_MarcoApp;

public class A2_ControladorBotonEjecuta implements ActionListener  {

	// CONSTRUCTOR
	//ESTA CLASE DEBE RECIBIR POR PARÁMETRO, LA CLASE A0_MarcoApp, PARA IDENTIFICAR AL BOTÓN.
	public A2_ControladorBotonEjecuta(A0_MarcoApp elMarco) {
		
		this.elMarco=elMarco;
		
	}
	
	//DESDE AQUÍ DEBEMOS ACCEDER AL MÉTODO DE LA CLASE A3_EJECUTACONSULTAS 
	public void actionPerformed(ActionEvent e) {
		
		
		//ALMACENAMOS LO QUE EL USUARIO SELECCIONÓ EN LA APP.
		String seleccionSeccion = (String)elMarco.secciones.getSelectedItem();
		String seleccionPais= (String)elMarco.paises.getSelectedItem();
		
		//EN ESTE RESULTSET ALMACENAMOS LO QUE EL USUARIO HA ALMACENADO EN EL 1ER Y 2DO MENÚ.	
		resultadoConsulta=obj.filtraBBDD(seleccionSeccion, seleccionPais);
		
		
		try {
			
			//PONEMOS ESTO PARA QUE DEJE EN BLANCO EL ÁREA DE TEXTO, CADA VEZ QUE HACEMOS UNA CONSULTA DIFERENTE.
			elMarco.resultado.setText("");
			
			while(resultadoConsulta.next()) {
				
				//AGREGAMOS AL ÁREA DE TEXTO "RESULTADO", EL CONTENIDO DE LA CONSULTA.(1ra columna) 
				elMarco.resultado.append(resultadoConsulta.getString(1));
				//SALTO DE LÍNEA
				elMarco.resultado.append("\n");
				
				elMarco.resultado.append(resultadoConsulta.getString(2));
				elMarco.resultado.append("\n");
				
				elMarco.resultado.append(resultadoConsulta.getString(3));
				elMarco.resultado.append("\n");
				
				elMarco.resultado.append(resultadoConsulta.getString(4));
				elMarco.resultado.append("\n");
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	
	private A0_MarcoApp elMarco;
	A3_EjecutaConsultas obj = new A3_EjecutaConsultas();
	private ResultSet resultadoConsulta;
}
