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

import ar.edu.unju.fi.collection.CollectionDocente;
import ar.edu.unju.fi.model.Docente;



@Controller
@RequestMapping("/docente")
public class DocenteController {
	@Autowired
	private Docente docente;
	
	@GetMapping("/listado")
	public String getDocentesPage(Model model) {
		model.addAttribute("docentes", CollectionDocente.getDocentes());
		model.addAttribute("titulo","Docentes");
		return "docentes";
	}
	
	@GetMapping("/nuevo")
	public String getNuevaDocentePage(Model model) {
		boolean edicion=false;
		model.addAttribute("docente", docente);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nuevo docente");
		return "docente";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarDocente(@ModelAttribute("docente") Docente docente) {
		ModelAndView modelView=new ModelAndView("docentes");
		String mensaje;
		boolean exito=CollectionDocente.agregarDocente(docente);
		if(exito)
		{
			mensaje="Docente guardad con exito!";
		}else {
			mensaje="Docente no se pudo guardar";
		}
		modelView.addObject("exito", exito);
		modelView.addObject("mensaje", mensaje);
		modelView.addObject("docentes",CollectionDocente.getDocentes());
		return modelView;
	}
	
	@GetMapping("/modificar/{legajo}")
	public String getModificarDocentePage(Model model, @PathVariable(value="legajo") int leg) {
		Docente docenteEncontrada= new Docente();
		boolean edicion=true;
		docenteEncontrada=CollectionDocente.buscarDocentes(leg);
		model.addAttribute("edicion", edicion);
		model.addAttribute("docente", docenteEncontrada);
		model.addAttribute("titulo","Modificar Docente");
		return "docente";
	}
	
	@PostMapping("/modificar")
	public String modificarDocente(@ModelAttribute("docente") Docente docente, Model model) {
		boolean exito=false;
		String mensaje="";
		try {
			CollectionDocente.modificarDocente(docente);
			mensaje="La materia con codigo "+docente.getLegajo()+" fue modificada";
			exito=true;
		}catch(Exception e) {
			mensaje=e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("docentes",CollectionDocente.getDocentes());
		model.addAttribute("titulo","Docentes");
		return "docentes";
	}
	
	@GetMapping("/eliminar/{legajo}")
	public String eliminarDocente(@PathVariable(value="legajo") int leg) {
		CollectionDocente.eliminarDocente(leg);
		return "redirect:/docente/listado";
	}
	
	
}
