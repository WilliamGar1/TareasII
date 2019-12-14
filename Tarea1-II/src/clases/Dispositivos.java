package clases;

import java.io.Serializable;

public abstract class Dispositivos implements Serializable{
	
	private String tampan;
	private String resolucion;
	private SistemaOperativo os;
	private String memoria;
	private Marca marca;
	private String modelo;
	private String disco;


	public Dispositivos(String tampan, String resolucion, SistemaOperativo os, String memoria, Marca marca,
			String modelo, String disco) {
		super();
		this.tampan = tampan;
		this.resolucion = resolucion;
		this.os = os;
		this.memoria = memoria;
		this.marca = marca;
		this.modelo = modelo;
		this.disco = disco;
	}
	
	public Dispositivos() {
		super();
	}


	public String getTampan() {
		return tampan;
	}


	public void setTampan(String tampan) {
		this.tampan = tampan;
	}


	public String getResolucion() {
		return resolucion;
	}


	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}


	public SistemaOperativo getOs() {
		return os;
	}


	public void setOs(SistemaOperativo os) {
		this.os = os;
	}


	public String getMemoria() {
		return memoria;
	}


	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}


	public Marca getMarca() {
		return marca;
	}


	public void setMarca(Marca marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	


	public String getDisco() {
		return disco;
	}


	public void setDisco(String disco) {
		this.disco = disco;
	}


	@Override
	public String toString() {
		return ", tampan=" + tampan + ", resolucion=" + resolucion + ", os=" + os + ", memoria=" + memoria
				+ ", marca=" + marca + ", modelo=" + modelo + ", disco=" + disco;
	}	

}
