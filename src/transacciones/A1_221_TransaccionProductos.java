package transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class A1_221_TransaccionProductos {
	public static void main(String[]args) {
	Connection miConexion=null;
		
		try{					
			
			//ESTABLECEMOS LA CONEXI�N
			miConexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "");				
			
			
			//CON setAutoCommit(false) HACEMOS QUE LAS INSTRUCCIONES SQL FUNCIONEN EN BLOQUE Y NO POR SEPARADO. 
			//... O SEA LAS EJECUTAMOS TODAS O NINGUNA.
			miConexion.setAutoCommit(false);
			
			
			//CREAMOS EL OBJ DE TIPO STATEMENT
			Statement miStatement =miConexion.createStatement();
			
			//INSTRUCCI�N 1 SQL: BORRAR ART�CULOS ITALIANOS
		    String instruccionSql_1="DELETE FROM PRODUCTOS WHERE PA�SDEORIGEN = 'ITALIA'";
		  
		    //INSTRUCCI�N 2 SQL: ELIMINAR LOS ART�CULOS QUE SUPEREN LOS 300 EUROS
		    String instruccionSql_2="DELETE FROM PRODUCTOS WHERE PRECIO>300";
		    
		    //INSTRUCCI�N 3 SQL: SUBIR EL PRECIO DE LOS PRODUCTOS EN UN 15%
		    String instruccionSql_3="UPDATE PRODUCTOS SET PRECIO=PRECIO*1.15";
		    
		    //EJECUTAMOS UN M�TODO QUE HACE EL TRABAJO DE PREGUNTAR.
		    //QUE EN LA VARIABLE EJECUTAR SE ALMACENE LO QUE NOS DEVUELVA LA LLAMADA AL M�TODO.
		    boolean ejecutar = ejecutarTransaccion(); 
		    
		    //PREGUNTAMOS QU� HAY DENTRO DE LA VARIABLE EJECUTAR
		    if(ejecutar = true) {
		    	miStatement.executeUpdate(instruccionSql_1);
			    miStatement.executeUpdate(instruccionSql_2);
			    miStatement.executeUpdate(instruccionSql_3);	
			    
			    //LE DAMOS EL OK A LA TRANSACCI�N
			    miConexion.commit();
			    System.out.println("Se ejecut� la transacci�n correctamente");
		    
		    }else {
		    	System.out.println("No se realizaron cambios en la BBDD");
		    }
		    
		    
		}catch(Exception e){
			
			//SI SE GENERA ALG�N ERROR EN LAS INSTRUCCIONES, APLICAMOS ROLLBACK PARA RETORNAR LA BBDD A SU ESTADO ORIGINAL.
			try {
				miConexion.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Algo sali� mal y no se realizaron los cambios en la BBDD");
			
		}
		
	}

	//ES STATIC PQ TODO EL C�DIGO ESTA EN EL MAIN, QUE TMBN ES STATIC.
	//"UNA LLAMADA DENTRO DE UN M�TODO STATIC, IMPLICA QUE EL M�TODO LLAMADO DEBA SER STATIC"
	static boolean ejecutarTransaccion() {
		
		String ejecucion = JOptionPane.showInputDialog("�Ejecutamos la transacci�n?");
		
		if(ejecucion.equals("si")) {
			return true;
		}else {
			return false;
		}
		
	}
	

}
