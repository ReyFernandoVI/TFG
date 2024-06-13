package org.tfg.spring.tfg.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Modelo {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

   /*  @OneToMany(mappedBy = "marcaZapatilla")
    private Collection<Marca> marcasZapatillas;*/


    public Modelo(){

        //this.marcasZapatillas = new ArrayList<>();

    }

    public Modelo(String nombre){
        this.nombre = nombre;
        //this.marcasZapatillas = new ArrayList<>();
    }

}
