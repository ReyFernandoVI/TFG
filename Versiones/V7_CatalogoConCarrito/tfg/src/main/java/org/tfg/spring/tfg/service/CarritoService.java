package org.tfg.spring.tfg.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tfg.spring.tfg.domain.Carrito;
import org.tfg.spring.tfg.domain.Usuario;
import org.tfg.spring.tfg.domain.Zapatilla;
import org.tfg.spring.tfg.repository.CarritoRepository;

import jakarta.servlet.http.HttpSession;

@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioService usuarioService;


    public Carrito updateSaveCrarito(Zapatilla zapatilla, HttpSession s){

        Carrito carrito = getCarritoIfExist(s);

        if(null != carrito){
            List<Zapatilla> zapatillasCarrito = carrito.getZapatillas();
            zapatillasCarrito.add(zapatilla);

            carrito.setZapatillas(zapatillasCarrito);
            return carritoRepository.save(carrito);
        }

        Usuario usuario = usuarioService.getAuthUser(s);

        if(null == usuario || null == usuario.getId()) return null; 

        Carrito newCarrito = new Carrito();

        newCarrito.setUsuario(usuario);
        newCarrito.setZapatillas(Arrays.asList(zapatilla));
        newCarrito.setIsBought(false);

        return carritoRepository.save(newCarrito);
    }

    public void finalizarCompra(HttpSession s){
    
        Carrito carrito = getCarritoIfExist(s);

        if(null == carrito) return;

        carrito.setIsBought(true);
        carritoRepository.save(carrito);
        
    }

    public void cancelarCompra(HttpSession s){
        Carrito carrito = getCarritoIfExist(s);

        if(null == carrito) return;

        carritoRepository.deleteById(carrito.getId());
    }

    public Carrito findCarritoByUsuarioId(HttpSession s){
        return getCarritoIfExist(s);
    }

    private Carrito getCarritoIfExist(HttpSession s){
        Usuario usuario = usuarioService.getAuthUser(s);

        if(null == usuario || null == usuario.getId()) return null; 

        Optional<Carrito> carrito = carritoRepository.findFirstByUsuarioIdAndIsBoughtFalse(usuario.getId());

        return carrito.isPresent()? carrito.get(): null;
    }

}
