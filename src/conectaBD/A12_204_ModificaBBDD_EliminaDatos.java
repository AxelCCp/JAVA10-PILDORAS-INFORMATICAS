package conectaBD;
import java.sql.DriverManager;
import java.sql.Statement;
import com.mysql.jdbc.Connection;

public class A12_204_ModificaBBDD_EliminaDatos {
	
	public static void main(String[]args) {

		
		try {
			
			
			//1. CREAR CONEXIÓN
			Connection miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			//2. CREAR OBJ STATEMENT
			Statement miStatement = miConexion.createStatement();
		
			
			//3. ELIMINAMOS INFORMACIÓN EN LA TABLA:
			
			String instruccionSql = "DELETE FROM PRODUCTOS WHERE CÓDIGOARTÍCULO = 'AR77'";
		
			miStatement.executeUpdate(instruccionSql);
			
			System.out.println("Datos ELIMINADOS correctamente");
			
			
		
		}catch(Exception e) {
		
			System.out.println("No Conecta!!!");
			
			e.printStackTrace();
		
		}
	}
}
