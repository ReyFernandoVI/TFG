package org.tfg.spring.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfg.spring.tfg.domain.Usuario; // Asegúrate de tener importada esta clase
import org.tfg.spring.tfg.exception.DangerException;
import org.tfg.spring.tfg.helper.PRG;
import org.tfg.spring.tfg.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/usuario")
@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("r")
    public String r(ModelMap m, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !Boolean.TRUE.equals(usuario.getAdmin())) {
            return "redirect:/login";
        }
        m.put("usuarios", usuarioService.findAll());
        m.put("view", "usuario/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(ModelMap m, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !Boolean.TRUE.equals(usuario.getAdmin())) {
            return "redirect:/login";
        }
        m.put("view", "usuario/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(@RequestParam("nombre") String nombre,
                        @RequestParam("dni") String dni,
                        @RequestParam("mail") String mail,
                        @RequestParam("contraseña") String contraseña,
                        HttpSession s) {
        Usuario usuario = (Usuario) s.getAttribute("usuario");
        if (usuario == null || !Boolean.TRUE.equals(usuario.getAdmin())) {
            return "redirect:/login";
        }
        try {
            usuarioService.save(nombre, dni, mail, contraseña);
        } catch (Exception e) {
            try {
                PRG.error("El usuario " + nombre + " ya existe", "/usuario/c");
            } catch (DangerException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/usuario/r";
    }

    @GetMapping("u")
    public String update(@RequestParam("id") Long idUsuario, ModelMap m, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !Boolean.TRUE.equals(usuario.getAdmin())) {
            return "redirect:/login";
        }
        m.put("usuario", usuarioService.findById(idUsuario));
        m.put("view", "usuario/u");
        return "_t/frame";
    }

    @PostMapping("u")
    public String updatePost(@RequestParam("id") Long id,
                             @RequestParam("nombre") String nombre,
                             @RequestParam("dni") String dni,
                             @RequestParam("mail") String mail,
                             HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !Boolean.TRUE.equals(usuario.getAdmin())) {
            return "redirect:/login";
        }
        try {
            usuarioService.update(id, nombre, dni, mail);
        } catch (Exception e) {
            try {
                PRG.error("El usuario no pudo ser actualizado", "/usuario/r");
            } catch (DangerException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/usuario/r";
    }

    @PostMapping("d")
    public String delete(@RequestParam("id") Long idUsuario, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !Boolean.TRUE.equals(usuario.getAdmin())) {
            return "redirect:/login";
        }
        try {
            usuarioService.delete(idUsuario);
        } catch (Exception e) {
            try {
                PRG.error("No se puede borrar el usuario", "/usuario/r");
            } catch (DangerException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/usuario/r";
    }

    @PostMapping("setAdmin")
    public String setAdmin(@RequestParam("nombre") String nombre, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !Boolean.TRUE.equals(usuario.getAdmin())) {
            return "redirect:/login";
        }
        try {
            usuarioService.setAdmin(nombre);
        } catch (Exception e) {
            try {
                PRG.error("Error al establecer como administrador", "/usuario/r");
            } catch (DangerException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/usuario/r";
    }
}
