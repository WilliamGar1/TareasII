package clases;

import java.io.Serializable;

public class Normal extends Empleado implements Serializable{
	
	private Profesion profesion;
	private Supervisor supervisor;
	private String salario;
	private String estado;
	

	public Normal(String nombre, String apellido, String edad, String genero, Profesion profesion,
			Supervisor supervisor, String sueldo, String estado) {
		super(nombre, apellido, edad, genero);
		this.profesion = profesion;
		this.supervisor = supervisor;
		this.salario = sueldo;
		this.estado = estado;
	}

	public Normal() {
		super();
	}
	

	public Profesion getProfesion() {
		return profesion;
	}



	public void setProfesion(Profesion profesion) {
		this.profesion = profesion;
	}



	public Supervisor getSupervisor() {
		return supervisor;
	}



	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public String getSalario() {
		return salario;
	}



	public void setSalario(String salario) {
		this.salario = salario;
	}



	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



	@Override
	public String toString() {
		return super.toString() + " profesion=" + profesion + ", supervisor=" + supervisor + ", sueldo=" + salario + ", estado="
				+ estado;
	}

	
	

	
	

}
