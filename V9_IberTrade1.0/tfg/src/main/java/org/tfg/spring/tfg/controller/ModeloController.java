package org.tfg.spring.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfg.spring.tfg.domain.Marca;
import org.tfg.spring.tfg.exception.DangerException;
import org.tfg.spring.tfg.helper.PRG;
import org.tfg.spring.tfg.service.MarcaService;
import org.tfg.spring.tfg.service.ModeloService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/modelo")
@Controller
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping("r")
    public String r(ModelMap m, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        m.put("modelos", modeloService.findAll());
        m.put("marcas", marcaService.findAll());
        m.put("view", "modelo/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(ModelMap m, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        m.put("modelos", modeloService.findAll());
        m.put("marcas", marcaService.findAll());
        m.put("view", "modelo/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(
            @RequestParam("nombre") String nombre,
            @RequestParam("marcaId") Marca marcaId,
            HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        try {
            modeloService.save(nombre, marcaId);
        } catch (Exception e) {
            try {
                PRG.error("Error al guardar el Modelo", "/modelo/c");
            } catch (DangerException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/modelo/r";
    }

    @GetMapping("u")
    public String update(
            @RequestParam("id") Long idModelo,
            ModelMap m,
            HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        m.put("modelo", modeloService.findById(idModelo));
        m.put("view", "modelo/u");
        return "_t/frame";
    }

    @PostMapping("u")
    public String updatePost(
            @RequestParam("id") Long idModelo,
            @RequestParam("nombre") String nombre,
            HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        try {
            modeloService.update(idModelo, nombre);
        } catch (Exception e) {
            try {
                PRG.error("Error al actualizar el Modelo", "/modelo/r");
            } catch (DangerException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/modelo/r";
    }

    @PostMapping("d")
    public String delete(
            @RequestParam("id") Long idModelo,
            HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        try {
            modeloService.delete(idModelo);
        } catch (Exception e) {
            try {
                PRG.error("Error al eliminar el Modelo", "/modelo/r");
            } catch (DangerException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/modelo/r";
    }

    private boolean isAdminLoggedIn(HttpSession session) {
        Object usuario = session.getAttribute("usuario");
        return usuario != null && Boolean.TRUE.equals(((org.tfg.spring.tfg.domain.Usuario) usuario).getAdmin());
    }
}
