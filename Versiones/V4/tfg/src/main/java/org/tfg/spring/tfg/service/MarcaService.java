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

    private final MarcaRepository marcaRepository;

    @Autowired
    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Marca save(String nombre, Model modeloId) {
        Marca marca = new Marca(nombre, modeloId);
        return marcaRepository.save(marca);
    }

    public Optional<Marca> findById(Long idMarca) {
        return marcaRepository.findById(idMarca);
    }

    public Marca update(Long idMarca, String nombre) {
        Optional<Marca> optionalMarca = marcaRepository.findById(idMarca);
        if (optionalMarca.isPresent()) {
            Marca marca = optionalMarca.get();
            marca.setNombre(nombre);
            return marcaRepository.save(marca);
        }
        throw new RuntimeException("Marca con ID " + idMarca + " no encontrada");
    }

    public void delete(Long idMarca) {
        if (marcaRepository.existsById(idMarca)) {
            marcaRepository.deleteById(idMarca);
        } else {
            throw new RuntimeException("Marca con ID " + idMarca + " no encontrada");
        }
    }
}
