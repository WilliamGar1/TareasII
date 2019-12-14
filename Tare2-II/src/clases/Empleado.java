package clases;

import java.io.Serializable;

public abstract class Empleado implements Serializable{
	
	private String nombre;
	private String apellido;
	private String edad;
	private String genero;
	
	public Empleado(String nombre, String apellido, String edad, String genero) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
	}
	
	public Empleado() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Empleado nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", genero=" + genero;
	}
	
	
}
