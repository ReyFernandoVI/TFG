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
import org.tfg.spring.tfg.service.PedidoService;

@RequestMapping("/pedido")
@Controller
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;
    

    @GetMapping("r")
    public String r(
            ModelMap m) {
        m.put("pedidos", pedidoService.findAll());
        m.put("view", "pedido/r");
        return "_t/frame";
    }

    @GetMapping("c")
    public String c(
            ModelMap m) {
        m.put("pedidos", pedidoService.findAll());
        m.put("view", "pedido/c");
        return "_t/frame";
    }

    @PostMapping("c")
    public String cPost(  
        @RequestParam("numPedido") Integer numPedido
         ) 
         throws DangerException {
        try {
            pedidoService.save(numPedido);
        } catch (Exception e) {
            PRG.error("El Pedido " + numPedido + " ya existe", "/pedido/c");
        }
        return "redirect:/pedido/r";
    }

    @GetMapping("u")
    public String update(
            @RequestParam("id") Long idPedido,
            ModelMap m) {
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
            PRG.error("El Pedido" + idPedido + " ya está registrado", "/marca/r");
        }
        return "redirect:/pedido/r";
    }

    @PostMapping("d")
    public String delete(
        @RequestParam("id") Long idPedido
    ) throws DangerException {
        try {
            pedidoService.delete(idPedido);
        }
        catch (Exception e) {
            PRG.error(e.getMessage(),"/pedido/r");
        }
        return "redirect:/pedido/r";
    }
    
}
