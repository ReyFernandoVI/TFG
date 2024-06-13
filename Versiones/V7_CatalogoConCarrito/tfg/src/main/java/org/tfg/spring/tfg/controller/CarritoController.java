package org.tfg.spring.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tfg.spring.tfg.domain.Carrito;
import org.tfg.spring.tfg.domain.Zapatilla;
import org.tfg.spring.tfg.service.CarritoService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @GetMapping("/carritos/find-last")
    public Carrito getCarritroUsuario(HttpSession s) {
        return carritoService.findCarritoByUsuarioId(s);
    }

    @GetMapping("/carritos/item-count")
    public int getCarritoItemCount(HttpSession s) {
        Carrito carrito = carritoService.findCarritoByUsuarioId(s);
        if (carrito != null) {
            return carrito.getZapatillas().size();
        } else {
            return 0; // O cualquier otro valor que desees retornar cuando el carrito sea null
        }
    }
    
    @PostMapping("/carritos/update")
    public Carrito postMethodName(@RequestBody Zapatilla zapatilla, HttpSession s) {
        return carritoService.updateSaveCrarito(zapatilla, s);
    }

    @GetMapping("/carritos/finalize")
    public void finalizarCompra(HttpSession s) {
        carritoService.finalizarCompra(s);
    }

    @GetMapping("/carritos/cancel")
    public void cancelarCompra(HttpSession s) {
        carritoService.cancelarCompra(s);
    }
}
