package org.tfg.spring.tfg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfg.spring.tfg.domain.TipoUsu;

@Repository
public interface TipoUsuRepository extends JpaRepository<TipoUsu, Long>{
    public List<TipoUsu> findByNombre(String nombre);
}
