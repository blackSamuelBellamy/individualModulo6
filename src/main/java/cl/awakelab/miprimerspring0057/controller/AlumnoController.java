package cl.awakelab.miprimerspring0057.controller;

import cl.awakelab.miprimerspring0057.entity.Alumno;
import cl.awakelab.miprimerspring0057.entity.Curso;
import cl.awakelab.miprimerspring0057.service.IAlumnoService;
import cl.awakelab.miprimerspring0057.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    IAlumnoService objAlumnoService;

    @Autowired
    ICursoService objCursoService;

    @GetMapping("/listar")
    public String listarAlumnos(Model model){
        List<Alumno> listaAlumnos = objAlumnoService.listarAlumno();
        model.addAttribute("listaAlumnos", listaAlumnos);
        return "templateAlumnos";
    }

    @GetMapping("/crearAlumno")
    public String formCrearAlumno(Model model){
        List<Curso> listaCursos = objCursoService.listarCursos();
        model.addAttribute("listaCursos", listaCursos);
        return "templateCrearAlumno";
    }

    @PostMapping("/crearAlumno")
    public String crearAlumno(@ModelAttribute Alumno alumno){
        objAlumnoService.crearAlumno(alumno);
        return "redirect:/alumno/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarAlumno(@PathVariable int id, Model model){
        Alumno alumno = objAlumnoService.listarAlumnoId(id);
        List<Curso> curso = objCursoService.listarCursos();
        model.addAttribute("alumno", alumno);
        model.addAttribute("cursos", curso);
        return "templateEditarAlumno";
    }

    @PostMapping("/editar")
    public String editarAlumno(@ModelAttribute Alumno alumno){
        objAlumnoService.actualizarAlumno(alumno.getId(), alumno);
        return "redirect:/alumno/listar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Integer id){
        objAlumnoService.eliminarAlumno(id);
        return "redirect:/alumno/listar";
    }

}
