package org.tfg.spring.tfg.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfg.spring.tfg.domain.Zapatilla;

@Repository
public interface ZapatillaRepository extends JpaRepository<Zapatilla,Long> {
    
}
