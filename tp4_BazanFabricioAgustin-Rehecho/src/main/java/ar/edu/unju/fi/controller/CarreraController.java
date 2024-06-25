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
import ar.edu.unju.fi.model.Carrera;

@Controller
@RequestMapping("/carrera")
public class CarreraController {
	@Autowired
	private Carrera carrera;
	

	@GetMapping("/listado")
	public String getCarrerasPage(Model model) {
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo","Carreras");
		model.addAttribute("exito",false);
		model.addAttribute("mensaje","");
		return "carreras";
	}
	
	@GetMapping("/nuevo")
	public String getNuevaCarreraPage(Model model) {
		boolean edicion=false;
		model.addAttribute("carrera", carrera);
		model.addAttribute("edicion", edicion);
		model.addAttribute("titulo", "Nueva carrera");
		return "carrera";
	}
	
	@PostMapping("/guardar")
	public ModelAndView guardarCarrera(@ModelAttribute("carrera") Carrera carrera) {
		ModelAndView modelView=new ModelAndView("carreras");
		String mensaje;
		carrera.setEstado(true);
		boolean exito = CollectionCarrera.agregarCarrera(carrera);
		if(exito)
		{
			mensaje="Carrera guardada con exito!";
		}else {
			mensaje="Carrera no se pudo guardar";
		}
		modelView.addObject("exito", exito);
		modelView.addObject("mensaje", mensaje);
		modelView.addObject("carreras",CollectionCarrera.getCarreras());
		return modelView;
	}
	
	@GetMapping("/modificar/{cod_carrera}")
	public String getModificarCarreraPage(Model model, @PathVariable(value="cod_carrera") int codigo) {
		Carrera carreraEncontradaDTO= CollectionCarrera.buscarCarrera(codigo);
		boolean edicion=true;
		model.addAttribute("edicion", edicion);
		model.addAttribute("carrera", carreraEncontradaDTO);
		model.addAttribute("titulo","Modificar Carrera");
		return "carrera";
	}
	
	@PostMapping("/modificar")
	public String modificarCarrera(@ModelAttribute("carrera") Carrera carrera, Model model) {
		boolean exito=false;
		String mensaje="";
		try {
			CollectionCarrera.modificarCarrera(carrera);
			mensaje="La carrera "+carrera.getNombre_carrera()+" fue modificada";
			exito=true;
		}catch(Exception e) {
			mensaje=e.getMessage();
			e.printStackTrace();
		}
		model.addAttribute("exito", exito);
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("carreras", CollectionCarrera.getCarreras());
		model.addAttribute("titulo","Carreras");
		return "carreras";
	}
	
	@GetMapping("/eliminar/{cod_carrera}")
	public String eliminarCarrera(@PathVariable(value="cod_carrera") int codigo) {
		CollectionCarrera.eliminarCarrera(codigo);
		return "redirect:/carrera/listado";
	}
}
