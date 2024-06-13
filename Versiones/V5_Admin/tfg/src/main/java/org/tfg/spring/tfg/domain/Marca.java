package org.tfg.spring.tfg.domain;

import org.springframework.ui.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @ManyToOne
    private Modelo modelo; // Cambia 'long' a 'Modelo'

    public Marca() {
        // Constructor vacío necesario para JPA
    }

    public Marca(String nombre) {
        this.nombre = nombre;
     
    }

    // Getters y setters (generados por Lombok @Data)

}
