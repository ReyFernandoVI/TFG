package org.tfg.spring.tfg.domain;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Marca {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

   /*  @OneToMany(mappedBy = "marcaZapatilla")
    private Collection<Marca> marcasZapatillas;*/


    public Marca(){

        //this.marcasZapatillas = new ArrayList<>();

    }

    public Marca(String nombre){
        this.nombre = nombre;
        //this.marcasZapatillas = new ArrayList<>();
    }

}
