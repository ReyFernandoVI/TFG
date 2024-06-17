package org.tfg.spring.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.tfg.spring.tfg.domain.Carrito;
import org.tfg.spring.tfg.domain.Zapatilla;
import org.tfg.spring.tfg.service.CarritoService;
import org.tfg.spring.tfg.service.ZapatillaService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CompraController {

    @Autowired
    private ZapatillaService zapatillaService;

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/mostrarProducto")
    public String mostrarProducto(@RequestParam("id") Long idZapatilla, ModelMap m, HttpSession session) {
        if (verificarSesion(session)) {
            Zapatilla zapatilla = zapatillaService.findById(idZapatilla);
            m.put("view", "home/mostrarProducto");
            m.put("zapatilla", zapatilla);
            return "_t/frame";
        } else {
            return "redirect:/login";
        }
    }

    @PostMapping("/pay")
    public String procesarPago(
            @RequestParam("idZapatilla") Long idZapatilla,
            @RequestParam("cantidad") int cantidad,
            ModelMap m, HttpSession session) {
        if (verificarSesion(session)) {
            Zapatilla zapatilla = zapatillaService.findById(idZapatilla);
            m.put("zapatilla", zapatilla);
            m.put("cantidad", cantidad);

            double total = zapatilla.getPrecio() * cantidad;
            m.put("total", total);
            m.put("view", "home/pay");
            return "_t/frame";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/comprar")
    public String getResumen(HttpSession s, ModelMap m) {
        m.put("view", "carrito/resumen");
        Carrito carrito = carritoService.findCarritoByUsuarioId(s);
        m.put("carritoZapatillas", carrito != null && !carrito.getCarritoZapatillas().isEmpty() ? carrito.getCarritoZapatillas() : null);
        return "_t/frame";
    }

    // Método para verificar la sesión
    private boolean verificarSesion(HttpSession session) {
        return session.getAttribute("usuario") != null;
    }
}
