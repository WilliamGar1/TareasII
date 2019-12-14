package clases;

import java.io.Serializable;

public class Profesion implements Serializable{
	private String codigo;
	private String titulo;
	
	public Profesion(String codigo, String titulo) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
	}
	
	public Profesion() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		return "(" + codigo + ") " + titulo;
	}
	
	
	
}
