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
import org.tfg.spring.tfg.service.ZapatillaService;

@RequestMapping("/zapatilla")
@Controller
public class ZapatillaController {

    @Autowired
    private ZapatillaService zapatillaService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping("r")
    public String r(
            ModelMap m) {
        m.put("zapatillas", zapatillaService.findAll());
        m.put("view", "zapatilla/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(
            ModelMap m) {
        m.put("zapatillas", zapatillaService.findAll());
        m.put("marcas", marcaService.findAll());
        m.put("view", "zapatilla/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(
            @RequestParam("nombre") String nombre,
            @RequestParam("precio") Integer precio,
            @RequestParam("color") String color,
            @RequestParam("talla") String talla)
            throws DangerException {
        try {
            zapatillaService.save(nombre, precio, color, talla);
        } catch (Exception e) {
            PRG.error("El Producto " + nombre + " ya existe", "/zapatilla/c");
        }
        return "redirect:/zapatilla/r";
    }

    @GetMapping("u")
    public String update(
            @RequestParam("id") Long idZapatilla,
            ModelMap m) {
        m.put("zapatilla", zapatillaService.findById(idZapatilla));
        m.put("view", "zapatilla/u");
        return "_t/frame";
    }

    @PostMapping("u")
    public String updatePost(
            @RequestParam("idZapatilla") Long idZapatilla,
            @RequestParam("nombre") String nombre,
            @RequestParam("precio") Integer precio,
            @RequestParam("color") String color,
            @RequestParam("talla") String talla) throws DangerException {
        try {
            zapatillaService.update(idZapatilla, nombre, precio, color, talla);
        } catch (Exception e) {
            PRG.error("El Producto" + nombre + " ya está registrado", "/zapatilla/r");
        }
        return "redirect:/zapatilla/r";
    }

    @PostMapping("d")
    public String delete(
            @RequestParam("id") Long idZapatilla) throws DangerException {
        try {
            zapatillaService.delete(idZapatilla);
        } catch (Exception e) {
            PRG.error(e.getMessage(), "/zapatilla/r");
        }
        return "redirect:/zapatilla/r";
    }

    @GetMapping("asignarMarcaZapatilla")
    public String asignarMarcaZapatilla(
            @RequestParam("id") Long id,
            ModelMap m) {
        m.put("zapatilla", zapatillaService.findById(id));
        m.put("marcas", marcaService.findAll());
        m.put("view", "zapatilla/asignarMarcaZapatilla");
        return "_t/frame";
    }

    @PostMapping("asignarMarcaZapatilla")
    public String asignarMarcaZapatillaPost(
            @RequestParam("idZapatilla") Long idZapatilla,
            @RequestParam("idMarca") Long idMarca) {
        zapatillaService.asignarMarcaZapatilla(idZapatilla, idMarca);
        return "redirect:/zapatilla/r";
    }

}
