package org.tfg.spring.tfg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tfg.spring.tfg.domain.Marca;
import org.tfg.spring.tfg.domain.Zapatilla;
import org.tfg.spring.tfg.repository.MarcaRepository;
import org.tfg.spring.tfg.repository.ZapatillaRepository;

@Service
public class ZapatillaService {
    
    @Autowired
    private ZapatillaRepository zapatillaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Zapatilla> findAll() {
        return zapatillaRepository.findAll();
    }
    public Zapatilla save(String nombre,Integer precio,String color, String talla) {
        return zapatillaRepository.save(new Zapatilla(nombre, precio, color, talla));
     }
    public Zapatilla findById(Long idZapatilla) {
        return zapatillaRepository.findById(idZapatilla).get();
    }

    public void update(Long idZapatilla, String nombre, Integer precio,String color, String talla) {
        Zapatilla zapatilla = zapatillaRepository.findById(idZapatilla).get();
        zapatilla.setNombre(nombre);
        zapatilla.setPrecio(precio);
        zapatilla.setColor(color);
        zapatilla.setTalla(talla);
       zapatillaRepository.save(zapatilla);
    }
    public void delete(Long idZapatilla) {
        zapatillaRepository.delete(zapatillaRepository.getReferenceById(idZapatilla));
    }

    public void asignarMarcaZapatilla(Long idZapatilla, Long idMarca) {
        Zapatilla zapatilla = zapatillaRepository.getReferenceById(idZapatilla);
        Marca marca  = marcaRepository.getReferenceById(idMarca);
        zapatilla.setMarcaZapatillas(marca);
        zapatillaRepository.save(zapatilla);
    }

}
