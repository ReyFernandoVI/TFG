package org.tfg.spring.tfg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tfg.spring.tfg.domain.Marca;
import org.tfg.spring.tfg.domain.Modelo;
import org.tfg.spring.tfg.repository.ModeloRepository;

@Service
public class ModeloService {
    
    @Autowired
    private ModeloRepository modeloRepository;
    
    public List<Modelo> findAll() {
        return modeloRepository.findAll();
    }
    public Modelo save(String nombre,Marca marcaId) {
        Modelo modelo = new Modelo(nombre,marcaId);
        return modeloRepository.save(modelo);
     }
    public Modelo findById(Long idModelo) {
        return modeloRepository.findById(idModelo).get();
    }

    public void update(Long idModelo, String nombre) {
        Modelo modelo = modeloRepository.findById(idModelo).get();
        modelo.setId(idModelo);
        modelo.setNombre(nombre);
        modeloRepository.save(modelo);
    }
    public void delete(Long idModelo) {
        modeloRepository.delete(modeloRepository.getReferenceById(idModelo));
    }

}
