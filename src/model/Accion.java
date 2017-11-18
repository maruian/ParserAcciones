package model;

import java.util.ArrayList;

public class Accion {
	String nombre;
	ArrayList<Operacion> operaciones;
	
	public Accion(String nombre, ArrayList<Operacion> operaciones) {
		this.nombre=nombre;
		this.operaciones=operaciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Operacion> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(ArrayList<Operacion> operaciones) {
		this.operaciones = operaciones;
	}
	
	
}
