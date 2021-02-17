package conectaBD;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class A2_205_ConsultaPreparada {
	
	public static void main(String []args) {
		
		
		try {
			
			//1. CREAR CONEXI�N...USAMOS LA INTERFAZ CONNECTION.	
			Connection miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
		
			
			
			//2.PREPARAR LA CONSULTA
			PreparedStatement miSentencia = (PreparedStatement) miConexion.prepareStatement("SELECT NOMBREART�CULO, SECCI�N, PA�SDEORIGEN FROM PRODUCTOS WHERE SECCI�N=? AND PA�SDEORIGEN=?");
	
			
			
			//3. ESTABLECER LOS PARAMETROS (?) DE CONSULTA.............//(1= de la interrogante 1ra. // texto propiamente dicho).
			miSentencia.setString(1,"deportes"); 	
			miSentencia.setString(2, "USA");
			
			
			
			//4. EJECUTAR Y RECORRER CONSULTA
			ResultSet rs = miSentencia.executeQuery();
			
			
			while (rs.next()) {
				
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			
			}
			
			rs.close();
			
			
			
			
	//------REUTILIZACI�N DE CONSULTA SQL---------------------------------------------------
			
			
			
			System.out.println("EJECUCI�N DE SEGUNDA CONSULTA");
			System.out.println("");
			
			
			//3. ESTABLECER LOS PARAMETROS (?) DE CONSULTA
			miSentencia.setString(1,"cer�mica"); 	
			miSentencia.setString(2, "China");
			
			
			//EJECUTAR Y RECORRER CONSULTA
			rs = miSentencia.executeQuery();
			
			
			while (rs.next()) {
				
				System.out.print(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
			
			}
			
			rs.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
	}
}//18.30min cap205
