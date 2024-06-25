package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
public class Carrera {
	private int cod_carrera;
	private String nombre_carrera;
	private int cant_anios;
	private boolean estado;
	
	public Carrera() {
		
	}

	public Carrera(int cod_carrera, String nombre_carrera, int cant_anios, boolean estado) {
		this.cod_carrera = cod_carrera;
		this.nombre_carrera = nombre_carrera;
		this.cant_anios = cant_anios;
		this.estado = estado;
	}

	public int getCod_carrera() {
		return cod_carrera;
	}

	public void setCod_carrera(int cod_carrera) {
		this.cod_carrera = cod_carrera;
	}

	public String getNombre_carrera() {
		return nombre_carrera;
	}

	public void setNombre_carrera(String nombre_carrera) {
		this.nombre_carrera = nombre_carrera;
	}

	public int getCant_anios() {
		return cant_anios;
	}

	public void setCant_anios(int cant_anios) {
		this.cant_anios = cant_anios;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
	
}
