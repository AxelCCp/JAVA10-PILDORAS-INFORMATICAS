package X0_210_MODELO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



//------------------------CÓDIGO PARA CARGAR LAS SECCIONES DEL JCOMBOBOX----------------------------------

public class A2_CargaMenu {
	
	//CONSTRUCTOR
	public A2_CargaMenu() {
		
		miConexion = new A0_Conexion();
	
	}
	
	
	//MÉTODO QUE EJECUTE LA CONSULTA NECESARIA PARA OBTENER LAS DIFERENTES SECCIONES PARA..
	//CARGAR EL JCOMBOBOX.
	
	public String ejecutaConsultas() {
		
		
		A1_Productos miProducto=null;
		
		
		//CONECTAMOS CON LA BASE DE DATOS
		Connection accesoBBDD=miConexion.dameConexion();
		
		
		//CREAMOS LA CONSULTA
		try {
			
			
			//CREAMOS UN OBJ STATEMENT PARA HACER UNA CONSULTA NORMAL, SIN PARAMETRO. ...//POR ESO NO SE USA CREATESTATEMENT 
			Statement secciones = accesoBBDD.createStatement();
			Statement paises = accesoBBDD.createStatement();
			
			//rs DEBE ALMACENAR EL RESULTADO DE LA CONSULTA A LAS DIFERENTES SECCIONES
			rs=secciones.executeQuery("SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS");
			rs2=paises.executeQuery("SELECT DISTINCTROW PAÍSDEORIGEN FROM PRODUCTOS");
			
			//HACEMOS Q EL RESULTSET NO SE SALTE EL PRIMER ELEMENTO DE LAS SECCIONES.
			//ESTO PARA QUE NOS SALGA "FERRETERÍA" EN EL JCOMBOBOX.
			//rs.previous();
				
				
			//INSTANCIAMOS OBJ DE LA CLASE PRODUCTOS, QUE CONSTRUYE PRODUCTOS
			miProducto=new A1_Productos();
				
			//"CON EL OBJ.USAMOS EL SETTER SECCION (CON RS ME DEVUELVES LA COLUMNA 1)". 
			miProducto.setSeccion(rs.getString(1));	
			miProducto.setPaisOrigen(rs2.getString(1));		
			
			rs.close();
			rs2.close();
			accesoBBDD.close();
			
		}catch(Exception e){
			
		}
		
		////DEVUELVE LO QUE HEMOS ALMACENADO EN EL OBJ
		return miProducto.getSeccion();
	
	}
	
	//DECLARAMOS VARIABLE DE TIPO CONEXION
	A0_Conexion miConexion;
	
	//DECLARAMOS UNA VARIABLE ResultSet. SERÁ PUBLIC PQ HAY QUE ACCEDER A ELLA DESDE CLASS CONEXION
	public ResultSet rs;
	
	//DECLARAMOS OTRO RESULTSET
	public ResultSet rs2; 
}
