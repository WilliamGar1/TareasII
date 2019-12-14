package clases;

import java.io.Serializable;

public class SistemaOperativo implements Serializable {
	
	private String nombre;
	private String version;
	private String year;
	
	public SistemaOperativo(String nombre, String version, String year) {
		super();
		this.nombre = nombre;
		this.version = version;
		this.year = year;
	}
	
	public SistemaOperativo() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return nombre + " (" + version + ")";
	}
	
	

}
