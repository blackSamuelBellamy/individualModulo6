package cl.awakelab.miprimerspring0057.controller;

import cl.awakelab.miprimerspring0057.entity.Profesor;
import cl.awakelab.miprimerspring0057.entity.Usuario;
import cl.awakelab.miprimerspring0057.service.IProfesorService;
import cl.awakelab.miprimerspring0057.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService objUsuarioService;

    @Autowired
    IProfesorService objProfesorService;

    @GetMapping("/listar")
    public String listarUsuarios(Model model){
        List<Usuario> listaUsuarios = objUsuarioService.listarUsuario();
        model.addAttribute("atributoListaUsuarios", listaUsuarios);
        return "templateListarUsuarios";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable int id, Model model) {
        Usuario usuario = objUsuarioService.listarUsuarioId(id);
        List<Profesor> profesores = objProfesorService.listarProfesores();
        model.addAttribute("usuario", usuario);
        model.addAttribute("profesores",profesores);
        return "templateEditarUsuarios";
    }

    @PostMapping("/editar")
    public String editarUsuario(@ModelAttribute Usuario usuario) {
        objUsuarioService.actualizarUsuario(usuario);
        return "redirect:/usuario/listar";
    }


    @GetMapping("/listar/{id}")
    public String listarUsuarioId(@PathVariable int id, Model model){
        model.addAttribute("tituloUsuarioId", "Usuario encontrado por ID");
        Usuario usuarioEncontrado = objUsuarioService.listarUsuarioId(id);
        model.addAttribute("usuarioEncontrado",usuarioEncontrado);
        return "listarPorId";
    }


    @GetMapping("/agregar")
    public String formulario(Model model){
        model.addAttribute("titulo", "Formulario de agregación de usuario");
        return "formUsuario";
    }

    @PostMapping("/agregarUsuario")
    public String agregarUsuario(Model model, @RequestParam String nombre,
                                 @RequestParam String password, @RequestParam String rol){
        Usuario usuarioAgregar = new Usuario();
        usuarioAgregar.setNombre(nombre);
        usuarioAgregar.setPassword(password);
        usuarioAgregar.setRol(rol);

        objUsuarioService.crearUsuario(usuarioAgregar);

        model.addAttribute("mensaje","Usuario agregado correctamente");

        return "formUsuario";
    }

    @GetMapping("/crearUsuario")
    public String formCrearUsuario(){
        return "templateCrearUsuario";
    }

    @PostMapping("/crearUsuario")
    public String crearUsuario(@ModelAttribute Usuario usuario){
        objUsuarioService.crearUsuario(usuario);
        return "redirect:/usuario/listar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable int id){

        objUsuarioService.eliminarUsuario(id);
        return "redirect:/usuario/listar";
    }

}
