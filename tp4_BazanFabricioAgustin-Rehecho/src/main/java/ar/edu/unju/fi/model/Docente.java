package ar.edu.unju.fi.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Docente {
	private int legajo;
	private String nombre_docente;
	private String apellido_docente;
	private String email_docente;
	private long num_telefono;
	
}
