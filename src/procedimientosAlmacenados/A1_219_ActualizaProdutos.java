package procedimientosAlmacenados;

import java.sql.CallableStatement;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

//ACTUALIZAMOS PRECIOS DE PRODUCTOS:
//EL (20) ES LA LONGITUD M�XIMA DEL PAR�METRO VARCHAR.

//CREATE PROCEDURE ACTUALIZA_PROD (N_PRECIO VARCHAR(20), N_ART VARCHAR(20))
//UPDATE PRODUCTOS SET PRECIO=N_PRECIO WHERE NOMBREART�CULO=N_ART

public class A1_219_ActualizaProdutos {
	
	public static void main(String[]args) {
		
		String nPrecio = JOptionPane.showInputDialog("Ingresa el precio: ");
		
		String nArticulo = JOptionPane.showInputDialog("Ingresa el nombre del art�culo: ");
		
		try {
			
			//CREAMOS LA CONEXI�N
			Connection miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			
			//ESPECIFICACI�N DE LLAMADA AL PROCEDIMIENTO ALMACENADO... 
			//LOS ?,? SON LOS PAR�METROS QUE LE ENVIAREMOS PARA ACTUALIZAR DATOS. 
			CallableStatement miSentencia = miConexion.prepareCall("{call ACTUALIZA_PROD(?, ?)}");
		
			
			//AHORA LE PASAMOS LOS PAR�METROS 
			miSentencia.setString(1, nPrecio);
			miSentencia.setString(2, nArticulo);
			
			
			//EJECUTAMOS SENTENCIA... ahora no necesitamos un RESULTSET y con este recorrer un while, ya q no vamos a leer nada.
			//...POR LO MISMO USAREMOS SOLO execute(),  Y NO executeQuery();
			miSentencia.execute();
			
			System.out.println("Actualizaci�n OK!!!");
			
			
		}catch(Exception e) {
			
			System.out.println("No Conecta!!!");
			
		}
	}
	
	

}
