package ar.edu.unju.fi.collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import ar.edu.unju.fi.model.Docente;

@Component

public class CollectionDocente {
	private static List<Docente> docentes=new ArrayList<Docente>();
	
	public static List<Docente> getDocentes(){
		if(docentes.isEmpty()) {
			docentes.add(new Docente(1, "Alejandro", "Vega", "vegaalejandro@gmail.com", 1125789321));
			docentes.add(new Docente(2, "Carolina", "Apaza", "carolinaapaza@gmail.com", 1234567891));
			docentes.add(new Docente(3, "Gustavo", "Sosa", "gustavososao@gmail.com", 1458932578));
		}
		return docentes;
	}
	
	/**
	 * 
	 * Para agregar docente @param docente
	 */
	public static boolean agregarDocente(Docente docente) {
		return docentes.add(docente) ? true:false;
	}
	
	/**
	 * Para mostrar los docentes cargados
	 */
	public static void mostrarDocente() {
		if(!docentes.isEmpty())
		{
			docentes.forEach(c->System.out.println(c));
		}else
			System.out.println("LA LISTA ESTÁ VACIA");
	}
	
	/**
	 * Para eliminar docente siguiendo el criterio
	 * @param leg
	 */
	public static void eliminarDocente(int leg) {
		Iterator<Docente> iterator=docentes.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getLegajo()==leg) {
				iterator.remove();
			}
		}
	}
	/**
	 * Para modificar los datos del objeto Docente
	 * @param docente
	 */
	
	public static void modificarDocente(Docente docente) throws Exception  {
		boolean encontrado=true;
		try {
			for(Docente d: docentes)
			{
				if(d.getLegajo()==docente.getLegajo())
				{
					d.setApellido_docente(docente.getApellido_docente());
					d.setNombre_docente(docente.getNombre_docente());
					d.setEmail_docente(docente.getEmail_docente());
					d.setNum_telefono(docente.getNum_telefono());
					encontrado=true;
				}if(!encontrado) {
					throw new Exception("La materia con  código: "+docente.getLegajo()+" no existe");
				}
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw e;
		}
	}
	/**
	 * Para buscar Docente
	 * @param legajo
	 * @return
	 */
	
	public static Docente buscarDocentes(int leg)
	{
		Predicate<Docente> filterCod=c->c.getLegajo()==leg;
		Optional<Docente> doc=docentes.stream().filter(filterCod).findFirst();
		if(doc.isPresent()) {
			return doc.get();
		}else {
			return null;
		}
	}
	
}
