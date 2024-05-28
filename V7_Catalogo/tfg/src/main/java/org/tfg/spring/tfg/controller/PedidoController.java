package org.tfg.spring.tfg.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfg.spring.tfg.domain.Pedido;
import org.tfg.spring.tfg.exception.DangerException;
import org.tfg.spring.tfg.helper.PRG;
import org.tfg.spring.tfg.service.PedidoService;

@RequestMapping("/pedido")
@Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
   // Declaración de precioTotal como atributo de la clase

    @GetMapping("r")
    public String r(ModelMap m) {
        List<Pedido> pedidos = pedidoService.findAll();
        m.put("pedidos", pedidos);
        m.put("cantidadTotal", pedidos.size());
        m.put("view", "pedido/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(ModelMap m) {
        m.put("view", "pedido/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(
        @RequestParam("nombreZapatilla") String nombreZapatilla,
        @RequestParam("modeloZapatilla") String modeloZapatilla,
        @RequestParam("marcaZapatilla") String marcaZapatilla,
        @RequestParam("precioUnidad") double precioUnidad,
        @RequestParam("fechCompra") LocalDate fechCompra,
        @RequestParam("cantidadZapatilla") Integer cantidad
    ) throws DangerException {
        try {
            //List<Pedido> pedidos = pedidoService.findAll();
         double precioUnidadTotal=pedidoService.calcularCantidadPrecio(precioUnidad, cantidad);
    
            // Guardar el pedido con el precioTotal actualizado
            pedidoService.save(nombreZapatilla, modeloZapatilla, marcaZapatilla, precioUnidad, fechCompra, precioUnidadTotal,cantidad);
        } catch (Exception e) {
            PRG.error("Error al crear el pedido: " + e.getMessage(), "/pedido/c");
        }
        return "redirect:/pedido/r";
    }

    @GetMapping("u")
    public String update(
        @RequestParam("id") Long idPedido,
        ModelMap m
    ) {
        m.put("pedido", pedidoService.findById(idPedido));
        m.put("view", "pedido/u");
        return "_t/frame";
    }

    @PostMapping("u")
    public String updatePost(
        @RequestParam("id") Long idPedido
    ) throws DangerException {
        try {
            pedidoService.update(idPedido);
        } catch (Exception e) {
            PRG.error("Error al actualizar el pedido: " + e.getMessage(), "/pedido/r");
        }
        return "redirect:/pedido/r";
    }

    @PostMapping("d")
    public String delete(
        @RequestParam("id") Long idPedido
    ) throws DangerException {
        try {
            pedidoService.delete(idPedido);
        } catch (Exception e) {
            PRG.error("Error al eliminar el pedido: " + e.getMessage(), "/pedido/r");
        }
        return "redirect:/pedido/r";
    }
}
