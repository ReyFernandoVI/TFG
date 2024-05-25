package org.tfg.spring.tfg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tfg.spring.tfg.domain.Zapatilla;
import org.tfg.spring.tfg.repository.MarcaRepository;
import org.tfg.spring.tfg.repository.ModeloRepository;
import org.tfg.spring.tfg.repository.ZapatillaRepository;

@Service
public class ZapatillaService {

    @Autowired
    private ModeloRepository modeloRepository;
    
    @Autowired
    private ZapatillaRepository zapatillaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Zapatilla> findAll(String palabraClave) {
        if(palabraClave!=null)
        {
            return zapatillaRepository.findAll(palabraClave);
        }
        return zapatillaRepository.findAll();
    }
    public void save(String nombre,Integer precio,String color, String talla, Integer stock, Long idMarca, Long idModelo, String nombreImagen) {
        Zapatilla zapatilla = new Zapatilla(nombre, precio, color, talla, stock);
        zapatilla.setMarcas(marcaRepository.getReferenceById(idMarca));
        zapatilla.setModelo(modeloRepository.getReferenceById(idModelo));
        zapatilla.setImagen(nombreImagen);
        zapatillaRepository.save(zapatilla);
     }
    public Zapatilla findById(Long idZapatilla) {
        return zapatillaRepository.findById(idZapatilla).get();
    }

    public void update(Long idZapatilla, String nombre, Integer precio,String color, String talla, Integer stock, Long idMarca, Long idModelo) {
        Zapatilla zapatilla = zapatillaRepository.findById(idZapatilla).get();
        zapatilla.setNombre(nombre);
        zapatilla.setPrecio(precio);
        zapatilla.setColor(color);
        zapatilla.setTalla(talla);
        zapatilla.setStock(stock);
        zapatilla.setMarcas(marcaRepository.getReferenceById(idMarca));
        zapatilla.setModelo(modeloRepository.getReferenceById(idModelo));
        zapatillaRepository.save(zapatilla);
    }
    public void delete(Long idZapatilla) {
        zapatillaRepository.delete(zapatillaRepository.getReferenceById(idZapatilla));
    }

    

}
