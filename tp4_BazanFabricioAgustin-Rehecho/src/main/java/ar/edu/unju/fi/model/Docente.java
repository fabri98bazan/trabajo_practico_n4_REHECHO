package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;



@Component

public class Docente {
	private int legajo;
	private String nombre_docente;
	private String apellido_docente;
	private String email_docente;
	private long num_telefono;
	
	public Docente() {
	}

	public Docente(int legajo, String nombre_docente, String apellido_docente, String email_docente,
			long num_telefono) {
		this.legajo = legajo;
		this.nombre_docente = nombre_docente;
		this.apellido_docente = apellido_docente;
		this.email_docente = email_docente;
		this.num_telefono = num_telefono;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) {
		this.legajo = legajo;
	}

	public String getNombre_docente() {
		return nombre_docente;
	}

	public void setNombre_docente(String nombre_docente) {
		this.nombre_docente = nombre_docente;
	}

	public String getApellido_docente() {
		return apellido_docente;
	}

	public void setApellido_docente(String apellido_docente) {
		this.apellido_docente = apellido_docente;
	}

	public String getEmail_docente() {
		return email_docente;
	}

	public void setEmail_docente(String email_docente) {
		this.email_docente = email_docente;
	}

	public long getNum_telefono() {
		return num_telefono;
	}

	public void setNum_telefono(long num_telefono) {
		this.num_telefono = num_telefono;
	}
	
	
	
}
