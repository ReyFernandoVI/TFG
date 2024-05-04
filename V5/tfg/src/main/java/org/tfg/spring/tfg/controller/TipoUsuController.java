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
import org.tfg.spring.tfg.service.TipoUsuService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/tipousu")
@Controller
public class TipoUsuController {

    @Autowired
    private TipoUsuService tipousuService;

    @GetMapping("r")
    public String r(
            ModelMap m) {
        m.put("tipousues", tipousuService.findAll());
        m.put("view", "tipousu/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(
            ModelMap m,
            HttpSession s) {

        m.put("view", "tipousu/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(
            @RequestParam("nombre") String nombre, HttpSession s) throws Exception {
 
        try {
            tipousuService.save(nombre);
        } catch (Exception e) {
            PRG.error("El tipousu " + nombre + " ya existe", "/tipousu/c");
        }
        return "redirect:/tipousu/r";
    }

    @GetMapping("u")
    public String update(
            @RequestParam("id") Long idtipousu,
            ModelMap m) {
        m.put("tipousu", tipousuService.findById(idtipousu));
        m.put("view", "tipousu/u");
        return "_t/frame";
    }

    @PostMapping("u")
    public String updatePost(
            @RequestParam("id") Long idtipousu,
            @RequestParam("nombre") String nombre) throws DangerException {
        try {
            tipousuService.update(idtipousu, nombre);
        } catch (Exception e) {
            PRG.error("El tipousu no pudo ser actualizado", "/tipousu/r");
        }
        return "redirect:/tipousu/r";
    }

    @PostMapping("d")
    public String delete(
            @RequestParam("id") Long idtipousu) throws DangerException {
        try {
            tipousuService.delete(idtipousu);
        } catch (Exception e) {
            PRG.error("No se puede borrar el tipousu", "/tipousu/r");
        }
        return "redirect:/tipousu/r";
    }
}
