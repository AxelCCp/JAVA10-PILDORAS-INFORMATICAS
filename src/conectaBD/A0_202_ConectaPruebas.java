package conectaBD;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import java.sql.*;

public class A0_202_ConectaPruebas {
	public static void main(String[]args) {
		
		//CREAMOS LA CONEXIÓN CON LA BBDD
			//NECESITAMOS UN OBJ DE TIPO CONNECTION.
			//TAMBN USAMOS LA CLASE "DriveManager" con el método getConection() ..
			//..este método nos devuelve un obj de tipo Conection. Por lo tanto hay que ..
			//..crear una variable de tipo interfaz Conection.
		
		
		try {
			//1. CREAR CONEXIÓN
			//VERIFICACIÓN DEL PUERTO EN PHPMYADMIN:
			//SHOW VARIABLES WHERE VARIABLE_NAME IN('hostname', 'port') 
			
			Connection miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			
			
			//2. CREAR OBJ STATEMENT
			
			Statement miStatement = miConexion.createStatement();
			
			
			
			//3.EJECUTAR SENTENCIA SQL
			
			ResultSet miResultset = miStatement.executeQuery("SELECT * FROM PRODUCTOS");
			
			
			
			//4.RECORRER EL RESULSET
			
			while(miResultset.next()) {
				
				System.out.println(miResultset.getString("NOMBREARTÍCULO") + " " + miResultset.getString("CÓDIGOARTÍCULO") + " " + miResultset.getString("PRECIO"));
			}
			
		}catch(Exception e) {
			System.out.println("No Conecta!!!");
			e.printStackTrace();
		}
	}

}
