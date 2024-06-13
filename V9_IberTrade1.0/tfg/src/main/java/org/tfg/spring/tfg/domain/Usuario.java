package org.tfg.spring.tfg.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    @Column(unique = true)
    private String dni;

    private String mail; 

    private String contraseña; 

    private LocalDate fechaAlta; 

    private Boolean admin;

    private Boolean vip; // Nuevo atributo VIP

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carrito> carritos;
    
    // Constructor y métodos actualizados
    public Usuario() {
        this.admin = false;
        this.vip = false; // Inicializado como no VIP
    }

    public Usuario(String nombre, String dni, String mail, String contraseña) {
        this.nombre = nombre;
        this.dni = dni;
        this.mail = mail;
        this.contraseña = contraseña;
        this.fechaAlta = LocalDate.now();
        this.admin = false; 
        this.vip = false; // Inicializado como no VIP
    }

    // Getters y setters
}
