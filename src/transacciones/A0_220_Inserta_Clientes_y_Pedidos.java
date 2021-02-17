package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class A0_220_Inserta_Clientes_y_Pedidos {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		
		Connection miConexion=null;
		
		try{					
			
			//ESTABLECEMOS LA CONEXIÓN
			miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");				
			
			//CON setAutoCommit(false) HACEMOS QUE LAS INSTRUCCIONES SQL FUNCIONEN EN BLOQUE Y NO POR SEPARADO. 
			//... O SEA LAS EJECUTAMOS TODAS O NINGUNA.
			miConexion.setAutoCommit(false);
			
			//CREAMOS EL OBJ DE TIPO STATEMENT
			Statement miStatement =miConexion.createStatement();
			
			//INSTRUCCIÓN SQL: INSERTA AL CLIENTE CON SUS DATOS EN LA TABLA CLIENTE
		    String instruccionSql_1="INSERT INTO CLIENTES (CÓDIGOCLIENTE, EMPRESA, DIRECCIÓN) VALUES ('CT84', 'EJEMPLO', 'P BOTANICO')";
			    
		    miStatement.executeUpdate(instruccionSql_1);
			
		    //INSTRUCCIÓN SQL: INSERTA EL PEDIDO DEL CLIENTE INGRESADO
		    String instruccionSql_2="INSERT INTO PEDIDOS (NÚMERODEPEDIDO, CÓDIGOCLIENTE,FECHADEPEDIDO) VALUES('82', 'CT84', '11/03/2000')";
			    
		    miStatement.executeUpdate(instruccionSql_2);
		    				    
		    //LE DAMOS EL "OK" A TODO EL BLOQUE DE INSTRUCCIONES SQL CON COMMIT()
		    miConexion.commit();
		    
		    System.out.println("Datos INSERTADOS correctamente");
				
		    
		    
		}catch(Exception e){
				
			System.out.println("ERROR EN LA INSERCIÓN DE DATOS!!");
			
			try {
				//SI ALGO VA MAL, DEJANOS LA BBDD COMO ESTABA ORIGINALMENTE.
				miConexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();	
				
			}
		}
}
