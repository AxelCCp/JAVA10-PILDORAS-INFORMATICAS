package X0_210_MODELO;


public class A1_Productos {
	
	//CONSTRUCTOR 
	public A1_Productos() {
		
		//LE DAMOS UN ESTADO INICIAL A LAS VARIABLES
		
		nArticulo="";
		
		seccion="";
		
		precio="";
		
		paisOrigen="";
	
	}
	
	//CREAMOS LOS SETTER Y GETTER DE LOS CAMPOS DE CLASE
	
	public String getnArticulo() {
		return nArticulo;
	}
	public void setnArticulo(String nArticulo) {
		this.nArticulo = nArticulo;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getPaisOrigen() {
		return paisOrigen;
	}
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	
	//DECLARACIÓN DE VARIABLES (DATOS) ENCAPSULADAS QUE SE VAN A USAR
	private String nArticulo;
	private String seccion;
	private String precio;
	private String paisOrigen;
}
