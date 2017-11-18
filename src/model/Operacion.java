package model;

public class Operacion {
	String nombre;
	String cantidad;
	String precio;
	
	public Operacion(String nombre, String cantidad, String precio) {
		this.nombre=nombre;
		this.cantidad=cantidad;
		this.precio=precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	public String getResultado() {
		StringBuffer str=new StringBuffer();
		str.append("Operacion: "+this.nombre+"\n");
		str.append("Cantidad: "+this.cantidad+"\n");
		str.append("Precio: "+this.precio+"\n");
		str.append("----------------------------------\n");
		return str.toString();
	}
	
}
