package conectaBD;

import java.sql.DriverManager;
import java.sql.Statement;
import com.mysql.jdbc.Connection;

public class A10_204_ModificaBBDD_IngresaDatos {
	
	public static void main(String[]args) {
		
		//CREAMOS LA CONEXI�N CON LA BBDD
		//NECESITAMOS UN OBJ DE TIPO CONNECTION.
		//TAMBN USAMOS LA CLASE "DriveManager"
	
	
		try {
			
			
			//1. CREAR CONEXI�N
			Connection miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			//2. CREAR OBJ STATEMENT
			Statement miStatement = miConexion.createStatement();
		
			
			//3. INGRESAMOS INFORMACI�N EN LA TABLA:
			
			String instruccionSql = "INSERT INTO PRODUCTOS(C�DIGOART�CULO, NOMBREART�CULO, PRECIO) VALUES ('AR77','PANTAL�N','25.35')";  
		
			miStatement.executeUpdate(instruccionSql);
			
			System.out.println("Datos insertados correctamente");
			
		
		}catch(Exception e) {
		
			System.out.println("No Conecta!!!");
			
			e.printStackTrace();
		
		}
	}
}
