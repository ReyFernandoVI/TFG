package org.tfg.spring.tfg.domain;

import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numPedido; // Cambia el tipo de dato a int para el número aleatorio
                            // Cambia el tipo de dato a String para la ID del producto


    public Pedido(Integer numPedido) {
        this.numPedido = generateRandomNumber(); // Genera el número aleatorio al crear el Pedido
         // Establece el ID del producto como productoId
    }

    // Método para generar un número aleatorio entre 0 y 100000
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100001); // El límite superior es exclusivo, por eso se usa 100001
    }
}
