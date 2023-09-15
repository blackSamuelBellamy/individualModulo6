package cl.awakelab.miprimerspring0057.controller;

import cl.awakelab.miprimerspring0057.entity.Alumno;
import cl.awakelab.miprimerspring0057.entity.Curso;
import cl.awakelab.miprimerspring0057.entity.Profesor;
import cl.awakelab.miprimerspring0057.service.IAlumnoService;
import cl.awakelab.miprimerspring0057.service.ICursoService;
import cl.awakelab.miprimerspring0057.service.IProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private ICursoService objCursoService;

    @Autowired
    private IProfesorService objProfesorService;

    @Autowired
    private IAlumnoService objAlumnoService;

    @GetMapping("listar")
    public String listarCursos(Model model){
        List<Curso> listaCursos = objCursoService.listarCursos();
        model.addAttribute("listaCursos",listaCursos);
        return "templateCursos";
    }

    @GetMapping("/crearCurso")
    public String crearCurso(Model model){
        List<Profesor> listaProfesores = objProfesorService.listarProfesores();
        //List<Alumno> listaAlumnos = objAlumnoService.listarAlumno();
        Curso curso = new Curso();
        model.addAttribute("curso", curso);
        model.addAttribute("listaProfesores",listaProfesores);
        //model.addAttribute("listaAlumnos",listaAlumnos);
        return "templateCrearCurso";
    }

    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable int id, Model model){
        Curso curso = objCursoService.listarCursoId(id);
        List<Profesor> profesores = objProfesorService.listarProfesores();
        List<Alumno> alumnos = objAlumnoService.listarAlumno();
        model.addAttribute("curso", curso);
        model.addAttribute("profesores", profesores);
        model.addAttribute("alumnos", alumnos);
        return "templateEditarCurso";
    }

    @PostMapping("/editar")
    public String editarCurso(@ModelAttribute Curso curso){
        objCursoService.actualizarCurso(curso);
        return "redirect:/curso/listar";
    }

    @PostMapping("/crearCurso")
    public String crearCurso(@ModelAttribute("curso") Curso curso){
        objCursoService.crearCurso(curso);
        return "redirect:/curso/listar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Integer id){
        objCursoService.eliminarCurso(id);
        return "redirect:/curso/listar";
    }

}
