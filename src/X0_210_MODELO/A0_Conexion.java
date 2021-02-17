package X0_210_MODELO;

import java.sql.Connection;
import java.sql.DriverManager;

public class A0_Conexion {

	Connection miConexion=null;

	public A0_Conexion(){}
	
	
	//CONECCIÓN CON BASE DE DATOS
	public Connection dameConexion() {
		
		try {
	
			//1. CREAR CONEXIÓN
			miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
		
		}catch(Exception e) {
			System.out.println("NO CONECTA!");
			
		}
		
		//NOS DEVUELVE EL OBJ DE TIPO CONNECTION
		return miConexion;
	
	}

}

	
	