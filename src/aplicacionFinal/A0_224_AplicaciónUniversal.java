package aplicacionFinal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class A0_224_AplicaciónUniversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MarcoBBDD mimarco=new MarcoBBDD();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mimarco.setVisible(true);

	}

}


class MarcoBBDD extends JFrame{

	public MarcoBBDD(){
		
		setBounds(300,300,700,700);
		
		LaminaBBDD milamina=new LaminaBBDD();
		
		add(milamina);
		
	}	
	
}

class LaminaBBDD extends JPanel{
	
	public LaminaBBDD(){
		
		setLayout(new BorderLayout());
		
		comboTablas=new JComboBox();
		
		areaInformacion=new JTextArea();
		
		add(areaInformacion,BorderLayout.CENTER);	
		
		
		
		conectarBBDD();
		obtenerTablas();
		
		
		
		//HACER QUE COMBOTABLAS RESPONDA A EVENTOS
		comboTablas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
			
				//PARA PODER LEER LO QUE HAY DENTRO DE CADA UNA DE LAS TABLAS.
				//ESTA VARIABLE LA PASAREMOS POR PARÁMETRO A UN MÉTODO, EL CUAL VA A HACER EL.. 
				//..TRABAJO DE LEER LA TABLA.
				String nombreTabla = (String) comboTablas.getSelectedItem();
				
				mostrarInfoTabla(nombreTabla);
			}
			
		});
		
		add(comboTablas, BorderLayout.NORTH);
		
		
		
		}
	
	//------------------------METODO QUE CONECTA CON BBDD CURSO.SQL--------------------------------------
	
	public void conectarBBDD() {
			
		miConexion=null;
	
		//CREAMOS UN ARRAY PARA ALMACENAR LAS 3 LÍNEAS DEL FICHERO DE TEXTO. "ruta BBDD/usuario/contraseña(3ra línea vacía)". 
		String datos[]=new String[3];
		
		
		try {
			
			//inicializamos la variable entrada y en el constructor de FileReader especificamos la ruta del archivo.
			entrada = new FileReader("C:/Users/Fantasma/OneDrive/1.-DOCUMENTOS/1.-CURSOS/14.-JAVA  PILDORAS INFORMÁTICAS/A0_226_DATOS_DE_CONEXION_BBDD.txt");
			
		
		//PARA ABRIR VENTANA DE BUSQUEDA DEL ARCHIVO DE CONEXIÓN.
		}catch(IOException e){
			
			//---------------------------------------------------------------------------
			
			
			JFileChooser chooser = new JFileChooser();
			 
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "Archivos de texto", "txt");
			 
			chooser.setFileFilter(filter);
			 
			int returnVal = chooser.showOpenDialog(this);
			 
			if(returnVal == JFileChooser.APPROVE_OPTION) {
			 
				try {
					entrada = new FileReader(chooser.getSelectedFile().getAbsolutePath());
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
			
		
			}
				
		}
			//---------------------------------------------------------------------------
			
		
		try {
			
			//creamos un flujo de datos que entrega la información del archivo a nuestro programa
			BufferedReader miBuffer = new BufferedReader(entrada);
			
			
			//recorremos el buffer, lo leemos y lo almacenamos en el array---------
			for(int i=0; i<=2;i++) {
				datos[i] = miBuffer.readLine();	
			}
			
			
			
			//jdbc:mysql://localhost:3306/pruebas
			//root
			//""
			                                                           //ó...  /curso_sql"
			miConexion=DriverManager.getConnection(datos[0], datos[1], datos[2]);
			entrada.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		}


	
	
	//----------------------MÉTODO QUE CARGA EL JCOMBOBOX-------------------------------------------------
	
	public void obtenerTablas() {
		
		ResultSet miResultset = null;
		
		try {
			//CONSTRUIMOS ESTE OBJ PARA OBTENER LOS METADATOS DE LA BBDD CURSO_SQL
			DatabaseMetaData datosBBDD = miConexion.getMetaData();
			
			//ALAMCENAMOS DENTRO DEL RESULTSET, LAS TABLAS QUE PUEDAN HABER DENTRO DE LA BBDD
			miResultset = datosBBDD.getTables(null, null, null, null);
			
			//RECORREMOS EL RESULTSET Y CARGAMOS EL JCOMBOBOX
			while(miResultset.next()) {
				
				//TABLE_NAME: es columna que contiene el nombre de las tablas en la BBDD.
				comboTablas.addItem(miResultset.getString("TABLE_NAME"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	//------------------------MÉTODO MOSTRARINFOTABLA----------------------------------------
	
	public void mostrarInfoTabla(String tabla) {
		
		//GUARDAREMOS LOS CAMPOS DE LAS TABLAS CONSULTADAS, DENTRO DE UN ARRAYLIST
		ArrayList <String> campos = new ArrayList <String>();
		
		//SERÁ LA SENTENCIA SQL QUE SERÁ CAPAZ DE CONSULTAR CUALQUIER TABLA
		String consulta = "SELECT * FROM " + tabla;
		
		
		
		//CONSULTAMOS LA INFORMACIÓN QUE HAY DENTRO DE LA TABLA
		try {
			
			areaInformacion.setText("");
			
			
			//ESTO CREA UNA CONSULTA PREPARADA
			Statement miStatement = miConexion.createStatement();
			
			
			//ALMACENAMOS LO QUE HAY DENTRO DE LA TABLA "tabla" PASADA POR PARÁMETRO.
			ResultSet miResultset = miStatement.executeQuery(consulta);
			
			
			//AHORA DEBEMOS OBTENER LOS METADATOS DEL RESULTSET. ESTO PARA SABER CUÁNTAS COLUMNAS TIENE..
			//..ESE RESULTSET Y CUAL ES EL NOMBRE DE ESTAS COLUMNAS.
			
			//almacenamos metadatos del resultset
			ResultSetMetaData rsBBDD = miResultset.getMetaData();
			
			
			// "i<=rsBBDD.getColumnCount()" = mientras i sea <= que la cantidad de columnas.
			//este for hace recorrer desde el primer campo hasta el último.
			for(int i=1;i<=rsBBDD.getColumnCount();i++) {
				
				//Almacenamos el nombre de los campos en el ArrayList
				//"i" será el n° de la columna.
				campos.add(rsBBDD.getColumnLabel(i));
			}
			
			//RECORREMOS EL RESULTSET, EXTRAYENDO VUELTA A VUELTA, EL NOMBRE DEL CAMPO 
			while(miResultset.next()) {
				
				//ESTE RECORRE EL ARRRAYLIST
				for(String nombreCampo : campos) {
					
					//este es el cuadro de texto de la app
					areaInformacion.append(miResultset.getString(nombreCampo) + "  ");
					
				}
				
				areaInformacion.append("\n");
				
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
	
	
	private JComboBox comboTablas;

	private JTextArea areaInformacion;
	
	private Connection miConexion;
	
	private FileReader entrada; //LA CREAMOS PARA PODER LEER EL FICHERO EXTERNO PARA COECTAR CON BBDD
	

}


		

