package org.tfg.spring.tfg.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

   @ManyToOne
    private Marca marca; 

    public Modelo(){

        //this.marcasZapatillas = new ArrayList<>();

    }

    public Modelo(String nombre,Marca marca){
        this.nombre = nombre;
        this.marca=marca;
        //this.marcasZapatillas = new ArrayList<>();
    }

}
