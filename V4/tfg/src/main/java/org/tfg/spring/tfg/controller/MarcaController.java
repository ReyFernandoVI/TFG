package org.tfg.spring.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.tfg.spring.tfg.exception.DangerException;
import org.tfg.spring.tfg.helper.PRG;
import org.tfg.spring.tfg.service.MarcaService;
import org.tfg.spring.tfg.service.ModeloService;
import org.tfg.spring.tfg.domain.Marca;
import org.tfg.spring.tfg.domain.Modelo;

@Controller
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private ModeloService modeloService;

    @GetMapping("r")
    public String r(ModelMap m) {
        m.put("marcas", marcaService.findAll());
        m.put("modelos", modeloService.findAll());
        m.put("view", "marca/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(ModelMap m) {
        m.put("marcas", marcaService.findAll());
        m.put("modelos", modeloService.findAll());
        m.put("view", "marca/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(@RequestParam("nombre") String nombre,
            @RequestParam("modeloId") Modelo modeloId) throws DangerException {
        try {

            marcaService.save(nombre, modeloId);
        } catch (Exception e) {
            PRG.error("Error al crear la Marca: " + e.getMessage(), "/marca/c");
        }
        return "redirect:/marca/r";
    }

    @GetMapping("u")
    public String update(@RequestParam("id") Long idMarca,
            ModelMap m) {
        m.put("marca", marcaService.findById(idMarca)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada")));
        m.put("view", "marca/u");
        return "_t/frame";
    }

    @PostMapping("u")
    public String updatePost(@RequestParam("id") Long idMarca,
            @RequestParam("nombre") String nombre) throws DangerException {
        try {
            marcaService.update(idMarca, nombre);
        } catch (Exception e) {
            PRG.error("Error al actualizar la Marca: " + e.getMessage(), "/marca/r");
        }
        return "redirect:/marca/r";
    }

    @PostMapping("d")
    public String delete(@RequestParam("id") Long idMarca) throws DangerException {
        try {
            marcaService.delete(idMarca);
        } catch (Exception e) {
            PRG.error("Error al eliminar la Marca: " + e.getMessage(), "/marca/r");
        }
        return "redirect:/marca/r";
    }
}
