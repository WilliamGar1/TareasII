package clases;

import java.io.Serializable;

public class Televisor extends Dispositivos implements Serializable{
	
	private String entradas;
	private String usb;
	

	public Televisor(String tampan, String resolucion, SistemaOperativo os, String memoria, Marca marca, String modelo,
			String disco, String entradas, String usb) {
		super(tampan, resolucion, os, memoria, marca, modelo, disco);
		this.entradas = entradas;
		this.usb = usb;
	}

	public String getEntradas() {
		return entradas;
	}

	public void setEntradas(String entradas) {
		this.entradas = entradas;
	}

	public String getUsb() {
		return usb;
	}

	public void setUsb(String usb) {
		this.usb = usb;
	}

	@Override
	public String toString() {
		return "Televisor [entradas=" + entradas + ", usb=" + usb + ", toString()=" + super.toString() + "]";
	}
	
	
	

}
