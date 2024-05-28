package org.tfg.spring.tfg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfg.spring.tfg.domain.Marca;


@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>{
    public List<Marca> findByNombre(String nombre);
}
