package org.tfg.spring.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfg.spring.tfg.exception.DangerException;
import org.tfg.spring.tfg.helper.PRG;
import org.tfg.spring.tfg.service.MarcaService;
import org.tfg.spring.tfg.service.ModeloService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/marca")
@Controller
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private ModeloService modeloService;

    @GetMapping("r")
    public String r(ModelMap m, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        m.put("marcas", marcaService.findAll());
        m.put("view", "marca/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(ModelMap m, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        m.put("marcas", marcaService.findAll());
        m.put("modelos", modeloService.findAll());
        m.put("view", "marca/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(@RequestParam("nombre") String nombre, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        try {
            marcaService.save(nombre);
        } catch (Exception e) {
            try {
                PRG.error("Error al guardar la Marca", "/marca/c");
            } catch (DangerException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/marca/r";
    }

    @GetMapping("u")
    public String update(@RequestParam("id") Long idMarca, ModelMap m, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        m.put("marca", marcaService.findById(idMarca));
        m.put("view", "marca/u");
        return "_t/frame";
    }

    @PostMapping("u")
    public String updatePost(@RequestParam("idMarca") Long idMarca, @RequestParam("nombre") String nombre,
            HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        try {
            marcaService.update(idMarca, nombre);
        } catch (Exception e) {
            try {
                PRG.error("Error al actualizar la Marca", "/marca/r");
            } catch (DangerException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/marca/r";
    }

    @PostMapping("d")
    public String delete(@RequestParam("id") Long idMarca, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        try {
            marcaService.delete(idMarca);
        } catch (Exception e) {
            try {
                PRG.error("Error al eliminar la Marca", "/marca/r");
            } catch (DangerException e1) {
                e1.printStackTrace();
            }
        }
        return "redirect:/marca/r";
    }

    @GetMapping("listaModelo")
    public String listaModelo(@RequestParam("id") Long idMarca, ModelMap m, HttpSession session) {
        if (!isAdminLoggedIn(session)) {
            return "redirect:/login";
        }
        m.put("marca", marcaService.findById(idMarca));
        m.put("view", "marca/listaModelo");
        return "_t/frame";
    }

    private boolean isAdminLoggedIn(HttpSession session) {
        Object usuario = session.getAttribute("usuario");
        return usuario != null && Boolean.TRUE.equals(((org.tfg.spring.tfg.domain.Usuario) usuario).getAdmin());
    }
}
