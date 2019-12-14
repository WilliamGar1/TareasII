package clases;

import java.io.Serializable;

public class Supervisor extends Empleado implements Serializable{

	private String canSubordinados;
	private Area area;
	
	
	public Supervisor(String nombre, String apellido, String edad, String genero, String canSubordinados, Area area) {
		super(nombre, apellido, edad, genero);
		this.canSubordinados = canSubordinados;
		this.area = area;
	}
	
	public Supervisor() {
		super();
	}
	

	public String getCanSubordinados() {
		return canSubordinados;
	}

	public void setCanSubordinados(String canSubordinados) {
		this.canSubordinados = canSubordinados;
	}


	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return getNombre() + " " + getApellido() + " (" + area + ")";
	}
	
	
}
