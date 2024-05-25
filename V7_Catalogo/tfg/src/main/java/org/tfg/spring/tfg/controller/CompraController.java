package org.tfg.spring.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.tfg.spring.tfg.domain.Zapatilla;
import org.tfg.spring.tfg.service.ZapatillaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class CompraController {

    @Autowired
    private ZapatillaService zapatillaService;

    @GetMapping("/mostrarProducto")
    public String mostrarProducto(@RequestParam("id") Long idZapatilla, ModelMap m) {
        Zapatilla zapatilla = zapatillaService.findById(idZapatilla);
        m.put("view", "home/mostrarProducto");
        m.addAttribute(zapatilla);
        return "_t/frame";
    }

    @PostMapping("/pay")
    public String procesarPago(
        @RequestParam("id") Long idZapatilla,
        @RequestParam("nombre") String nombre,
        @RequestParam("precio") Integer precio,
        @RequestParam("color") String color,
        @RequestParam("talla") String talla,
        @RequestParam("stock") Integer stock,
        @RequestParam("idMarca") Long idMarca,
        @RequestParam("idModelo") Long idModelo,
        ModelMap m) {
        // Aquí puedes procesar el pago y realizar cualquier otra lógica necesaria
        m.addAttribute("id", idZapatilla);
        m.addAttribute("nombre", nombre);
        m.addAttribute("precio", precio);
        m.addAttribute("color", color);
        m.addAttribute("talla", talla);
        m.addAttribute("stock", stock);
        m.addAttribute("idMarca", idMarca);
        m.addAttribute("idModelo", idModelo);
        return "checkout"; // Página de confirmación de pago
    }
}
