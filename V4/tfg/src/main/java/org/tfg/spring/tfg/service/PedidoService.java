package org.tfg.spring.tfg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.tfg.spring.tfg.domain.Pedido;
import org.tfg.spring.tfg.repository.PedidoRepository;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido save(Integer numPedido) {
        Pedido pedido = new Pedido(numPedido);
        return pedidoRepository.save(pedido); // Modificado
        //return pedidoRepository.save(pedido);
    }

    public Pedido findById(Long idPedido) {
        return pedidoRepository.findById(idPedido).orElse(null);
    }

    public void update(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElse(null);
        if (pedido != null) {
            pedidoRepository.save(pedido);
        }
    }

    public void delete(Long idpedido) {
        pedidoRepository.deleteById(idpedido);
    }

    public List<Pedido> findByNumPedido(Integer numPedido) {
        return pedidoRepository.findByNumPedido(numPedido);
    }
}
