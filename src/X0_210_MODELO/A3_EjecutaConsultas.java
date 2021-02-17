package X0_210_MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//CLASE QUE EJECUTA LAS CONSULTAS A LA BASE DE DATOS
public class A3_EjecutaConsultas {
	
	
	//CONSTRUCTOR
	public A3_EjecutaConsultas() {
	
		
		//ESTABLECEMOS UNA CONEXI�N A BBDD
		miConexion = new A0_Conexion();
		
	}

	
	//LE PASAMOS ESTOS PAR�METROS PQ LE PASAREMOS A ESTE M�TODO LA SECCI�N Y EL PA�S ESCOGIDO POR EL USUARIO.
	//ESTE M�TODO VERIFICA 3 OPCIONES: SI HA ELEGIDO SECCI�N, SI HA ELEGIDO PAIS, O AMBAS OPCIONES.
	public ResultSet filtraBBDD(String seccion, String pais) {
		
		
		//LLAMAMOS AL M�TODO dameConexion() DE LA CLASE A0_Conexion()
		Connection conecta = miConexion.dameConexion();
		
		rs=null;
	
		try {
			
		
			if(!seccion.equals("Todos") && pais.equals("Todos")) {
				
				//A LA VARIABLE LE PASAMOS LO QUE EL USUARIO HAYA ESCOGIDO EN EL DESPLEGABLE.
				enviaConsultaSeccion = conecta.prepareStatement(consultaSeccion);
				
				//LE DECIMOS QUE EN EL 1ER PAR�METRO(?) DE LA CONSULTA SQL, ALMACENE LO QUE HAY GUARDADO EN SECCI�N.
				enviaConsultaSeccion.setString(1, seccion);
				
				//EJECUTAMOS LA SENTENCIA Y LA ALMACENAMOS EN EL RESULSET
				rs=enviaConsultaSeccion.executeQuery();
				
				
				
			}else if(seccion.equals("Todos") && !pais.equals("Todos")) {
			
				enviaConsultaPais = conecta.prepareStatement(consultaPais);
				enviaConsultaPais.setString(1, pais);
				rs=enviaConsultaPais.executeQuery();
				
			}else {
			
				enviaConsultaTodos = conecta.prepareStatement(consultaTodos);
				enviaConsultaTodos.setString(1, seccion);
				enviaConsultaTodos.setString(2, pais);
				rs = enviaConsultaTodos.executeQuery();
			}
		
			
		}catch(Exception e){			
		}
		
		
		return rs;
	
	}

	
	//CAMPOS DE CLASE
	private A0_Conexion miConexion;
	private ResultSet rs;
	private PreparedStatement enviaConsultaSeccion;
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaTodos;
	private final String consultaSeccion = "SELECT NOMBREART�CULO, SECCI�N, PRECIO, PA�SDEORIGEN FROM PRODUCTOS WHERE SECCI�N=?";
	private final String consultaPais = "SELECT NOMBREART�CULO, SECCI�N, PRECIO, PA�SDEORIGEN FROM PRODUCTOS WHERE PA�SDEORIGEN=?";
	private final String consultaTodos= "SELECT NOMBREART�CULO, SECCI�N, PRECIO, PA�SDEORIGEN FROM PRODUCTOS WHERE SECCI�N=? AND PA�SDEORIGEN=?";
}
