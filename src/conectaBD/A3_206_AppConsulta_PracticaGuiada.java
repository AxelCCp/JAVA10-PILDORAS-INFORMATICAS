package conectaBD;
import javax.swing.*;
import com.mysql.jdbc.Connection;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class A3_206_AppConsulta_PracticaGuiada {
	
	public static void main(String[]args) {
		
		JFrame mimarco=new Marco_Aplicacion();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mimarco.setVisible(true);
	}
}

class Marco_Aplicacion extends JFrame{
	
	public Marco_Aplicacion(){
		
		setTitle ("Consulta BBDD");
		
		setBounds(500,300,400,400);
		
		setLayout(new BorderLayout());
		
		JPanel menus=new JPanel();
		
		menus.setLayout(new FlowLayout());
		
		secciones=new JComboBox();
		
		secciones.setEditable(false);
		
		secciones.addItem("Todos");
		
		paises=new JComboBox();
		
		paises.setEditable(false);
		
		paises.addItem("Todos");
		
		resultado= new JTextArea(4,50);
		
		resultado.setEditable(false);
		
		add(resultado);
		
		menus.add(secciones);
		
		menus.add(paises);	
		
		add(menus, BorderLayout.NORTH);
		
		add(resultado, BorderLayout.CENTER);
		
		JButton botonConsulta=new JButton("Consulta");	
		
		
		
		//-------------------PONEMOS EL BOT�N A LA ESCUCHA ... USAMOS CLASE INTERNA AN�NIMA--------- 
		
		botonConsulta.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {ejecutaConsulta();}});
		
		add(botonConsulta, BorderLayout.SOUTH);
		
		
		
		
	
		
		
		//1-------------------CONEXI�N CON BASE DE DATOS --------------------------------------
		
		try {
			
			//1. CREAR CONEXI�N
			miConexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas","root","");
			
			
			//2. CREAR OBJ STATEMENT
			Statement sentencia = miConexion.createStatement();
			
			
		//------
			
			
			//3. CREAR SENTENCIA / CARGA DE COMBOBOX SECCIONES -------------
			String consulta = "SELECT DISTINCTROW SECCI�N FROM PRODUCTOS";									//con DISTINCTROW, le decimos que cargue solo una l�nea por secci�n.
			
			
			//4. CREAMOS EL RESULTSET PARA ALMACENAR ESTA CONSULTA
			ResultSet rs = sentencia.executeQuery(consulta);
			
			
			//5. RECORREMOS EL RESULTSET
			while(rs.next()) {
			
				secciones.addItem(rs.getString(1)); 												    	//secciones es el 1er ComboBox. //(1) de la �nica columna del comboBox. 
			
			}
			
			
			rs.close();
			
			
		//------
			
			//3.- CREAMOS OTRA CONSULTA PARA  CARGAR 2DO COMBOBOX PAISES 
			consulta = "SELECT DISTINCTROW PA�SDEORIGEN FROM PRODUCTOS";									//con DISTINCTROW, le decimos que cargue solo una l�nea por secci�n.
			
			
			//4.-
			rs = sentencia.executeQuery(consulta);
			
			
			//5.-
			while(rs.next()) {
			
				paises.addItem(rs.getString(1)); 															//paises es el nombre del 2do comboBox	
			
			}
			
			rs.close();
			
			
		//------
			
			
		} catch(Exception e) {
		
			System.out.println("NO CONECTA");
		
		}
	
	}
	
	
	
	//------------------ FIN CONSTRUCTOR MARCO_APLICACION -----------------------------
	
	
	
	
	//------------------ CREAMOS M�TODO QUE ACTIVA AL BOT�N "CONSULTA" ---------------
	
	private void ejecutaConsulta() {
	
		
		ResultSet rs = null;
		
	
		//BLOQUE TRY-CATCH
	
		try {
		
			//ALMACENAR EN UNA VARIABLE, LO QUE EL USUARIO HAYA ELEGIDO EN LOS JCOMBOBOX
			//MEN� 1:
			String seccion = (String) secciones.getSelectedItem();
			//MEN� 2:
			String pais = (String)paises.getSelectedItem();
			
			
			
			//SI EL 1ER MEN� ES != A TODOS, QUIERE DECIR QUE EL USUARIO HA SELECCIONADO EN EL 1ER MEN� ("!"CON ESTO LE DAMOS LA VUELTA A AL C�DIGO)
			if(!seccion.equals("Todos") && pais.equals("Todos")) {
				
				
				//C�DIGO QUE ELAVORA LA CONSULTA PREPARADA
				enviaConsultaSeccion = miConexion.prepareStatement(consultaSeccion);
				
				
				//USAMOS M�TODO SETSTRING() DE LA INTERFAZ PREPAREDSTATEMENT PARA PASARLE EL VALOR POR PAR�METRO  
				enviaConsultaSeccion.setString(1,seccion);    													//(lugar del par�metro "?" est� en la 1ra posici�n,,, seccion) 
				
				
				//EJECUTAMOS LA CONSULTA
				rs = enviaConsultaSeccion.executeQuery();
				
				
				//PARA QUE SE RESETEE EL JTEXTAREA, ENTRE LAS DISTINTAS CONSULTAS SQL
				resultado.setText("");	
			
				
				
			}else if(seccion.equals("Todos") && !pais.equals("Todos")) {
			
				enviaConsultaPais = miConexion.prepareStatement(consultaPais);
				
				enviaConsultaPais.setString(1,pais);    													
				
				rs = enviaConsultaPais.executeQuery();
				
				resultado.setText("");	
				
				
			}else if(!seccion.equals("Todos") && !pais.equals("Todos")) {
				
				enviaConsultaTodos = miConexion.prepareStatement(consultaTodos);
				
				enviaConsultaTodos.setString(1,seccion); 
				
				enviaConsultaTodos.setString(2,pais);  													
				
				rs = enviaConsultaTodos.executeQuery();
				
				resultado.setText("");	
			
			}
			
			
			
			//RECORREMOS EL RESULTSET
			while(rs.next()) {
			
				//AGREGAMOS EL RESULTSET AL OBJ JTEXTAREA LLAMADO "RESULTADO"
				
				resultado.append(rs.getString(1));//(1)COLUMNA NOMBREART�CULO
				
				resultado.append(", "); 											//cremos un espacio entre los resultados.
				
				resultado.append(rs.getString(2));//(2)COLUMNA SECCI�N
				
				resultado.append(", "); 
				
				resultado.append(rs.getString(3));//(3)COLUMNA PRECIO
				
				resultado.append(", "); 
				
				resultado.append(rs.getString(4));//(4)COLUMNA PA�SDEORIGEN
				
				resultado.append(", "); 
				
				//AGREGAMOS UN SALTO DE L�NEA, PARA QUE QUEDE ORDENADO EL JTEXTAREA
				
				resultado.append("\n");
			
			}
			
			
			
		}catch(Exception e){
			
		}
		
			
	}
	
	//CAMPOS DE CLASE
	private Connection miConexion;
	private JComboBox secciones;
	private JComboBox paises;
	private JTextArea resultado;
	
	//CREAMOS VARIABLE OBJ, PARA ALMACENAR LA CONSULTA PREPARADA
	private PreparedStatement enviaConsultaSeccion;
	private PreparedStatement enviaConsultaPais;
	private PreparedStatement enviaConsultaTodos;
	
	//ALMACENAMOS LA SENTENCIA SQL EN LA VARIABLE ...
	private final String consultaSeccion = "SELECT NOMBREART�CULO, SECCI�N, PRECIO, PA�SDEORIGEN FROM PRODUCTOS WHERE SECCI�N=?";
	private final String consultaPais = "SELECT NOMBREART�CULO, SECCI�N, PRECIO, PA�SDEORIGEN FROM PRODUCTOS WHERE PA�SDEORIGEN=?";
	private final String consultaTodos = "SELECT NOMBREART�CULO, SECCI�N, PRECIO, PA�SDEORIGEN FROM PRODUCTOS WHERE SECCI�N=? AND PA�SDEORIGEN=?";
	
}

