package org.tfg.spring.tfg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfg.spring.tfg.domain.Modelo;


@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long>{
  public List<Modelo> findByNombre(String nombre);
}
