package org.tfg.spring.tfg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tfg.spring.tfg.domain.TipoUsu;
import org.tfg.spring.tfg.repository.TipoUsuRepository;

@Service
public class TipoUsuService {
    
    @Autowired
    private TipoUsuRepository tipousuRepository;

    public List<TipoUsu> findAll() {
        return tipousuRepository.findAll();
    }

    public List<TipoUsu> findByNombre(String nombre) {
        return tipousuRepository.findByNombre(nombre);
    }

    public TipoUsu save(String nombre) {
        return tipousuRepository.save(new TipoUsu(nombre));
    }

    public TipoUsu findById(Long idtipousu) {
        return tipousuRepository.findById(idtipousu).get();
    }
    public void update(Long idtipousu, String nombre) {
        TipoUsu tipousu = tipousuRepository.findById(idtipousu).get();
        tipousu.setNombre(nombre);
        tipousuRepository.save(tipousu);
    }

    public void delete(Long idtipousu) {
        tipousuRepository.delete(tipousuRepository.getReferenceById(idtipousu));
    }
}
