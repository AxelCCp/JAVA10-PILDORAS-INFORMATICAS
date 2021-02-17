package procedimientosAlmacenados;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;

//INTERFAZ CALLABLESTATEMENT : SE USA PARA EJECUTAR PROCEDIMIENTOS ALMACENADOS SQL


public class A0_218_ConsultaClientes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			//CREAMOS LA CONEXIÓN
			Connection miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			//LLAMAMOS AL PROCEDIMIENTO ALMACENADO 
			CallableStatement miSentencia = miConexion.prepareCall("{call MUESTRAS_CLIENTES}");
			
			//EJECUTAMOS LA QUERY
			ResultSet rs = miSentencia.executeQuery();		
			
			
			while(rs.next()) {
				
				//MOSTRAMOS CÓDIGO CLIENTE, EMPRESA Y DIRECCIÓN
	
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			}
			
			rs.close();
			
		}catch(Exception e){
			
		}
		
	}

}
