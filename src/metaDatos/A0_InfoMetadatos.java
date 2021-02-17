package metaDatos;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class A0_InfoMetadatos {
	
	public static void main(String[]args) {
		
		mostrarInfoBBDD();
		mostrarInfoTablas();
		
	}

	static void mostrarInfoBBDD() {
		
		Connection miConexion = null;
		
		try {
			miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
		
			//OBTENCIÓN DE METADATOS
			DatabaseMetaData datosBBDD = miConexion.getMetaData();
		
			//MOSTRAR INFORMACIÓN DE BASE DE DATOS
			System.out.println("Gestor de BBDD " + datosBBDD.getDatabaseProductName());
			
			System.out.println("Versión del gestor: " + datosBBDD.getDatabaseProductVersion());
		
			System.out.println("Nombre del driver: " + datosBBDD.getDriverName());
			
			System.out.println("Versión del driver: " + datosBBDD.getDriverVersion());
			
		} catch (Exception e) {
			System.out.println("No Conecta!!!");
			e.printStackTrace();
		
		
		}finally {
			
			try {
				miConexion.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	//MÉTODO QUE MUESTRA INFORMACIÓN DE TABLAS
	static void mostrarInfoTablas() {
	
		Connection miConexion = null;
		ResultSet miResultset = null;
		
		//---------------------------CONEXIÓN-------------------------------------------------
		
		
		try {
			
			miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			//OBTENCIÓN DE METADATOS
			DatabaseMetaData datosBBDD = miConexion.getMetaData();
			
			
		//------------------------LISTA DE TABLAS----------------------------------------------
			
			 
			//(%)caracter comodín de SQL
			//("p%")que nos muestre todas las tablas q empiecen con "p"
			
			System.out.println("LISTA DE TABLAS: ");
			miResultset = datosBBDD.getTables(null, null, null,null);
			//miResultset = datosBBDD.getTables(null, null, "p%",null);
			
			
		//--------------------RECORREMOS EL RESULTSET-----------------------------------------	
			
		
			//.....TABLE_NAME ES EL NOMBRE DE UNA DE LAS COLUMNAS, QUE CONTIENE EL NOMBRE DE LAS TABLAS.
			while(miResultset.next()) {
				System.out.println(miResultset.getString("TABLE_NAME"));
			}
		
			
		//------------------LISTA DE COLUMNAS DE PRODUCTOS-------------------------------------
			
			
			System.out.println("");
			System.out.println("CAMPOS DE TABLA PRODUCTOS:");
			miResultset = datosBBDD.getColumns(null, null, "productos",null);
			
			
		//--------------------RECORREMOS EL RESULTSET-----------------------------------------	
			
			
			while(miResultset.next()) {
				System.out.println(miResultset.getString("COLUMN_NAME"));
			}
			
		//--------------------
			
		}catch(Exception e) {
			System.out.println("No Conecta!!!");
			e.printStackTrace();
			
		}finally {
			
			try {
				miConexion.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
