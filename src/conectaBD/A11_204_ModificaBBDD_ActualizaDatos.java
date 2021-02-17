package conectaBD;
import java.sql.DriverManager;
import java.sql.Statement;
import com.mysql.jdbc.Connection;

public class A11_204_ModificaBBDD_ActualizaDatos {
	
	public static void main(String[]args) {

	
		try {
			//1. CREAR CONEXIÓN
			Connection miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			//2. CREAR OBJ STATEMENT
			Statement miStatement = miConexion.createStatement();
		
			
			//3. ACTUALIZAMOS INFORMACIÓN EN LA TABLA:
			
			String instruccionSql = "UPDATE PRODUCTOS SET PRECIO = PRECIO*2 WHERE CÓDIGOARTÍCULO='AR77'";
		
			miStatement.executeUpdate(instruccionSql);
			
			System.out.println("Datos MODIFICADOS correctamente");
			
			
			
		
		}catch(Exception e) {
		
			System.out.println("No Conecta!!!");
			
			e.printStackTrace();
		
		}
	}
	
}
