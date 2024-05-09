package org.tfg.spring.tfg.domain;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @ManyToOne
    private Modelo modelo; // Cambia 'long' a 'Modelo'

    @JsonIgnore
    @OneToMany(mappedBy = "marcas")
    private Collection<Zapatilla> marcasZapatillas;


    public Marca(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters (generados por Lombok @Data)

}
