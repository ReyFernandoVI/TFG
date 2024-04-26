package org.tfg.spring.tfg.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Zapatilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Integer precio;

    private String color;

    private String talla;

    @ManyToOne
    private Marca marcaZapatillas;

    
    // ==================

    public Zapatilla(String nombre,Integer precio,String color, String talla) {
        this.nombre = nombre;
        this.precio = precio;
        this.color = color;
        this.talla = talla;
    }
}
