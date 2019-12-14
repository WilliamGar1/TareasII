package clases;


import java.io.Serializable;
import java.util.ArrayList;

public class Telefono extends Dispositivos implements Serializable{
	
	private String numero;
	private String comp;
	private String mp;
	private String cpu;
	private ArrayList<String> otros;
	

	/*public Telefono() {
		super();
		
	}*/
	
	public Telefono(Marca marca, String modelo, SistemaOperativo os, String memoria, String disco, String resolucion, String tampan,     
			String comp, String numero, String mp, String cpu, ArrayList<String> otros) {
		super(tampan, resolucion, os, memoria, marca, modelo, disco);
		this.numero = numero;
		this.comp = comp;
		this.mp = mp;
		this.cpu = cpu;
		this.otros = otros;
	}
	
	

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComp() {
		return comp;
	}

	public void setComp(String comp) {
		this.comp = comp;
	}

	public String getMp() {
		return mp;
	}

	public void setMp(String mp) {
		this.mp = mp;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public ArrayList<String> getOtros() {
		return otros;
	}

	public void setOtros(ArrayList<String> otros) {
		this.otros = otros;
	}
	
	@Override
	public String toString() {
		return "Telefono [numero=" + numero + ", comp=" + comp + ", mp=" + mp + ", cpu=" + cpu + ", otros=" + otros
				 + super.toString() + "]";
	}
	
}
