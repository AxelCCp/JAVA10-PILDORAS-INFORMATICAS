package X2_210_CONTROLADOR;

import java.sql.Connection;
import java.sql.DriverManager;

public class A0_Conexion {

	Connection miConexion=null;
	public A0_Conexion(){}
	
	//CONECCI�N CON BASE DE DATOS 
	public Connection dameConexion() {
		
		try {
			//1. CREAR CONEXI�N
			miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
		}catch(Exception e) {}
		
		return miConexion;
	}
}

	
	