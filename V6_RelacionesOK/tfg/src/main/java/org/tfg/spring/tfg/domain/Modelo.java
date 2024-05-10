package org.tfg.spring.tfg.domain;

import java.util.ArrayList;
import java.util.List;

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
public class Modelo {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

   @ManyToOne
    private Marca marca;
    
    @OneToMany(mappedBy = "modelo")
    private List<Zapatilla> zapatillas;

    public Modelo(String nombre,Marca marca){
        this.nombre = nombre;
        this.marca=marca;
        this.zapatillas = new ArrayList<>();
    }

}
