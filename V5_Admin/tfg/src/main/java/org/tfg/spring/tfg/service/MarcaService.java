package org.tfg.spring.tfg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.tfg.spring.tfg.domain.Marca;
import org.tfg.spring.tfg.domain.Modelo;
import org.tfg.spring.tfg.repository.MarcaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    @Autowired
    private  MarcaRepository marcaRepository ;



    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca save(String nombre) {
        Marca marca = new Marca(nombre);
        return marcaRepository.save(marca);
    }

    public Marca findById(Long idMarca) {
        return marcaRepository.findById(idMarca).get();
    }

    public void update(Long idMarca, String nombre) {
        Marca marca = marcaRepository.findById(idMarca).get();
        marca.setId(idMarca);
        marca.setNombre(nombre);
         marcaRepository.save(marca);
        }
       

    public void delete(Long idMarca) {
      marcaRepository.delete(marcaRepository.getReferenceById(idMarca));
    }
}
