package ar.edu.unju.fi.collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Materia;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
@Component
public class CollectionMateria {
	private static List<Materia> materias=new ArrayList<Materia>();
	
	public static List<Materia> getMaterias(){
		if(materias.isEmpty()) {
			materias.add(new Materia(1, "Programacion Estructurada","Primero", 45, false, new Docente(1, "Alejandro", "Vega", "vegaalejandro@gmail.com", 1125789321) , new Carrera(1,"A.P.U",3,true)));
			materias.add(new Materia(2, "Algebra Lineal","Segundo", 45, true, new Docente(2, "Carolina", "Apaza", "carolinaapaza@gmail.com", 1234567891), new Carrera(2,"Lic. Sistemas",5,true)));
			materias.add(new Materia(3, "Matematica Discreta","Segundo", 45, true, new Docente(3, "Gustavo", "Sosa", "gustavososao@gmail.com", 1458932578), new Carrera(3,"Ing. Informatica",5,false)));
		}
		return materias;
	}
	
	/**
	 * Para agregar Materia
	 * @param materia
	 */
	public static boolean agregarMateria(Materia materia) {
		return materias.add(materia) ? true: false;
	}
	
	
	/**
	 * Para mostrar las materias cargadas
	 */
	
	public static void mostrarMaterias() {
		if(!materias.isEmpty())
		{
			materias.forEach(c->System.out.println(c));
		}else
			System.out.println("LA LISTA ESTÁ VACIA");
	}
	
	/**
	 * Para eliminar un objeto materia
	 * @param cod
	 */
	public static void eliminarMateria(int cod) {
		Iterator<Materia> iterator=materias.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().getCod()==cod)
				iterator.remove();
		}
	}
	/**
	 * Para modificar los datos del objeto Materia
	 * @param materia
	 */
	public static void modificarMateria(Materia materia)throws Exception {
		boolean encontrado=false;
		try {
			for(Materia m:materias) {
				if(m.getCod()==materia.getCod())
				{
					m.setNombre_materia(materia.getNombre_materia());
					m.setModalidad(materia.isModalidad());
					m.setCurso(materia.getCurso());
					m.setDocente(materia.getDocente());
					m.setCarrera(materia.getCarrera());
					m.setCant_horas(materia.getCant_horas());
					encontrado=true;
				}
			}
			if(!encontrado) {
				throw new Exception("La materia con  código: "+materia.getCod()+" no existe");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw e;
		}
	}
	/**
	 * Para buscar Materia
	 * @param cod
	 * @return
	 */
	
	public static Materia buscarMaterias(int cod) {
		Predicate<Materia> filterCod=c->c.getCod()==cod;
		Optional<Materia> mat=materias.stream().filter(filterCod).findFirst();
		if(mat.isPresent()) {
			return mat.get();
		}else {
			return null;
		}
	}
}
