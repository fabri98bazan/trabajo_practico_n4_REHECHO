package ar.edu.unju.fi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.collection.CollectionCarrera;
import ar.edu.unju.fi.collection.CollectionDocente;
import ar.edu.unju.fi.collection.CollectionMateria;
import ar.edu.unju.fi.model.Carrera;
import ar.edu.unju.fi.model.Docente;
import ar.edu.unju.fi.model.Materia;

@Controller
@RequestMapping("/materia")
public class MateriaController {
	@Autowired
	private Materia materia;
	@Autowired
	private Carrera carrera;
	@Autowired
	private Docente docente;
	
	@GetMapping("/listado")
	public String getMateriasPage(Model model) {
		model.addAttribute("titulo","Materias");
		model.addAttribute("exito",false);
		model.addAttribute("mensaje","");
		model.addAttribute("materias", CollectionMateria.getMaterias());
		return "materias";
	}
	
	@GetMapping("/nuevo")
	public String getNuevaMateriaPage(Model model) {
		model.addAttribute("materia", materia);
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo", "Nueva Materia");
		return "materia";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarMateria(@ModelAttribute("materia") Materia materia) {
		ModelAndView modelView=new ModelAndView("materias");
		String mensaje;
		carrera=CollectionCarrera.buscarCarrera(materia.getCarrera().getCod_carrera());
		docente=CollectionDocente.buscarDocentes(materia.getDocente().getLegajo());
		materia.setCarrera(carrera);
		materia.setDocente(docente);
		materia.setModalidad(true);
		boolean exito=CollectionMateria.agregarMateria(materia);
		if(exito)
		{
			mensaje="Materia guardada con exito!";
		}else {
			mensaje="Materia no se pudo guardar";
		}
		modelView.addObject("exito", exito);
		modelView.addObject("mensaje", mensaje);
		modelView.addObject("materias",CollectionMateria.getMaterias());
		return modelView;
	}
	
	@GetMapping("/modificar/{cod}")
	public String getModificarMateriaPage(Model model, @PathVariable(value="cod") int codigo) {
		Materia materiaEncontrada= new Materia();
		boolean edicion=true;
		materiaEncontrada=CollectionMateria.buscarMaterias(codigo);
		model.addAttribute("edicion", edicion);
		model.addAttribute("materia", materiaEncontrada);
		model.addAttribute("titulo","Modificar Materia");
		return "materia";
	}
	
	@PostMapping("/modificar")
	public String modificarMateria(@ModelAttribute("materia") Materia materia, Model model) {
		boolean exito=false;
		String mensaje="";
		carrera=CollectionCarrera.buscarCarrera(materia.getCarrera().getCod_carrera());
		docente=CollectionDocente.buscarDocentes(materia.getDocente().getLegajo());
		materia.setCarrera(carrera);
		materia.setDocente(docente);
		try {
			CollectionMateria.modificarMateria(materia);
			mensaje="La materia con codigo "+materia.getCod()+" fue modificada";
			exito=true;
		}catch(Exception e) {
			mensaje=e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("materias", CollectionMateria.getMaterias());
		model.addAttribute("titulo","Materias");
		return "materias";
	}
	
	@GetMapping("/eliminar/{cod}")
	public String eliminarMateria(@PathVariable(value="cod") int codigo) {
            CollectionMateria.eliminarMateria(codigo);
            return "redirect:/materia/listado";
	}
	
	
}
