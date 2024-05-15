package org.tfg.spring.tfg.domain;

import java.time.LocalDate;
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

    private LocalDate fechCompra;

    private double precioPedido;// Para que podamos introducir numeros decimales con . Ej: 328.20€ 

    public Pedido(Integer numPedido, LocalDate fechCompra, double precioPedido) {
        this.numPedido = generateRandomNumber();; // Genera el número aleatorio al crear el Pedido
        this.fechCompra = fechCompra;
        this.precioPedido = precioPedido;
    }

    // Método para generar un número aleatorio entre 0 y 100000
    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(100001); // El límite superior es exclusivo, por eso se usa 100001
    }
}
