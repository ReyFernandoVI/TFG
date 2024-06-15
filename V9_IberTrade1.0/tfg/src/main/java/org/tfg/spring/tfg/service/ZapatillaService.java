package org.tfg.spring.tfg.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tfg.spring.tfg.domain.Zapatilla;
import org.tfg.spring.tfg.repository.CarritoZapatillasRepository;
import org.tfg.spring.tfg.repository.MarcaRepository;
import org.tfg.spring.tfg.repository.ModeloRepository;
import org.tfg.spring.tfg.repository.ZapatillaRepository;

import jakarta.transaction.Transactional;

@Service
public class ZapatillaService {

    @Autowired
    private ModeloRepository modeloRepository;
    
    @Autowired
    private ZapatillaRepository zapatillaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CarritoZapatillasRepository carritoZapatillasRepository;

    public List<Zapatilla> findAll(String palabraClave) {
        if(palabraClave!=null)
        {
            return zapatillaRepository.findAll(palabraClave);
        }
        return zapatillaRepository.findAll();
    }
    public void save(String nombre,double precio,String color, String talla, Integer stock, Long idMarca, Long idModelo, String nombreImagen) {
        Zapatilla zapatilla = new Zapatilla(nombre, precio, color, talla, stock);
        zapatilla.setMarcas(marcaRepository.getReferenceById(idMarca));
        zapatilla.setModelo(modeloRepository.getReferenceById(idModelo));
        zapatilla.setImagen(nombreImagen);
        zapatillaRepository.save(zapatilla);
     }
    public Zapatilla findById(Long idZapatilla) {
        return zapatillaRepository.findById(idZapatilla).get();
    }
    public Long findIdModelo(String nombre)
    {
      return modeloRepository.findByNombre(nombre).getId();
    }
    public Long findIdMarca(String nombre)
    {
      return marcaRepository.findByNombre(nombre).getId();
    }
    public Optional<Zapatilla> findByName(String nombreZapatilla) {
        // Suponiendo que tienes un repositorio `zapatillaRepository` con un método `findByName`
        return zapatillaRepository.findByNombre(nombreZapatilla);
    }

    public void updateStock(Zapatilla zapatilla, int cantidad) {
        zapatilla.setStock(zapatilla.getStock() + cantidad);
        zapatillaRepository.save(zapatilla);
    }
    public void update(Long idZapatilla, String nombre, double precio,String color, String talla, Integer stock, String nombreImagen, Long idMarca, Long idModelo) {
        Zapatilla zapatilla = zapatillaRepository.findById(idZapatilla).get();
        zapatilla.setNombre(nombre);
        zapatilla.setPrecio(precio);
        zapatilla.setColor(color);
        zapatilla.setTalla(talla);
        zapatilla.setStock(stock);
        zapatilla.setImagen(nombreImagen);
        zapatilla.setMarcas(marcaRepository.getReferenceById(idMarca));
        zapatilla.setModelo(modeloRepository.getReferenceById(idModelo));
        zapatillaRepository.save(zapatilla);
    }
    // public void delete(Long idZapatilla) {
    //     zapatillaRepository.delete(zapatillaRepository.getReferenceById(idZapatilla));
    // }

    @Transactional
    public void delete(Long zapatillaId) {
        Zapatilla zapatilla = zapatillaRepository.findById(zapatillaId)
                .orElseThrow(() -> new RuntimeException("Zapatilla no encontrada"));

        if (carritoZapatillasRepository.existsByZapatilla(zapatilla)) {
            carritoZapatillasRepository.deleteByZapatilla(zapatilla);
        }

        zapatillaRepository.delete(zapatilla);
    }

    public void init() {
        if (zapatillaRepository.count() == 0) {
            

            Zapatilla zapatilla = new Zapatilla();
            Zapatilla zapatilla1 = new Zapatilla();
            Zapatilla zapatilla2 = new Zapatilla();
            Zapatilla zapatilla3 = new Zapatilla();
            Zapatilla zapatilla4 = new Zapatilla();
            Zapatilla zapatilla5 = new Zapatilla();
            Zapatilla zapatilla6 = new Zapatilla();
            Zapatilla zapatilla7 = new Zapatilla();
            Zapatilla zapatilla8 = new Zapatilla();
            Zapatilla zapatilla9 = new Zapatilla();
            Zapatilla zapatilla10 = new Zapatilla();
            Zapatilla zapatilla11 = new Zapatilla();
            Zapatilla zapatilla12 = new Zapatilla();
            Zapatilla zapatilla13 = new Zapatilla();
            Zapatilla zapatilla14 = new Zapatilla();
            Zapatilla zapatilla15 = new Zapatilla();
            Zapatilla zapatilla16 = new Zapatilla();
            Zapatilla zapatilla17 = new Zapatilla();
            Zapatilla zapatilla18 = new Zapatilla();
            Zapatilla zapatilla19 = new Zapatilla();
            


            List <Zapatilla> zapatillasIni = new ArrayList<Zapatilla>();
        
            zapatilla.setNombre("Sky Blue");
            zapatilla.setPrecio(120);
            zapatilla.setColor("Azul");
            zapatilla.setTalla("42");
            zapatilla.setStock(2);
            zapatilla.setMarcas(marcaRepository.getReferenceById(1L));
            zapatilla.setModelo(modeloRepository.getReferenceById(1L));
            zapatilla.setImagen("AirMax1Cielo");

            zapatilla1.setNombre("Dragon Boost");
            zapatilla1.setPrecio(220);
            zapatilla1.setColor("Gris");
            zapatilla1.setTalla("41");
            zapatilla1.setStock(3);
            zapatilla1.setMarcas(marcaRepository.getReferenceById(2L));
            zapatilla1.setModelo(modeloRepository.getReferenceById(2L));
            zapatilla1.setImagen("UltraBoost3");

            zapatilla2.setNombre("Pumpkin Air");
            zapatilla2.setPrecio(160);
            zapatilla2.setColor("Naranja-Gris");
            zapatilla2.setTalla("45");
            zapatilla2.setStock(2);
            zapatilla2.setMarcas(marcaRepository.getReferenceById(1L));
            zapatilla2.setModelo(modeloRepository.getReferenceById(1L));
            zapatilla2.setImagen("AirMax90Naranjas");

            zapatilla3.setNombre("Green Valley");
            zapatilla3.setPrecio(130);
            zapatilla3.setColor("Verde");
            zapatilla3.setTalla("45");
            zapatilla3.setStock(1);
            zapatilla3.setMarcas(marcaRepository.getReferenceById(3L));
            zapatilla3.setModelo(modeloRepository.getReferenceById(3L));
            zapatilla3.setImagen("NbGreen");

            zapatilla4.setNombre("BasketLvl");
            zapatilla4.setPrecio(220);
            zapatilla4.setColor("Beige");
            zapatilla4.setTalla("45");
            zapatilla4.setStock(2);
            zapatilla4.setMarcas(marcaRepository.getReferenceById(4L));
            zapatilla4.setModelo(modeloRepository.getReferenceById(4L));
            zapatilla4.setImagen("YezzyNBA3");

            zapatilla5.setNombre("Gel Lyte Cookies");
            zapatilla5.setPrecio(100);
            zapatilla5.setColor("Marron");
            zapatilla5.setTalla("42");
            zapatilla5.setStock(1);
            zapatilla5.setMarcas(marcaRepository.getReferenceById(6L));
            zapatilla5.setModelo(modeloRepository.getReferenceById(6L));
            zapatilla5.setImagen("GelLyte");

            zapatilla6.setNombre("Jungle Casual");
            zapatilla6.setPrecio(80);
            zapatilla6.setColor("Gris");
            zapatilla6.setTalla("43");
            zapatilla6.setStock(4);
            zapatilla6.setMarcas(marcaRepository.getReferenceById(5L));
            zapatilla6.setModelo(modeloRepository.getReferenceById(5L));
            zapatilla6.setImagen("PumaDaily");

            zapatilla7.setNombre("Uncle Chuck");
            zapatilla7.setPrecio(60);
            zapatilla7.setColor("Blancas");
            zapatilla7.setTalla("38");
            zapatilla7.setStock(2);
            zapatilla7.setMarcas(marcaRepository.getReferenceById(7L));
            zapatilla7.setModelo(modeloRepository.getReferenceById(7L));
            zapatilla7.setImagen("Converse2");

            zapatilla8.setNombre("Radiator Springs");
            zapatilla8.setPrecio(110);
            zapatilla8.setColor("Rojo");
            zapatilla8.setTalla("40");
            zapatilla8.setStock(1);
            zapatilla8.setMarcas(marcaRepository.getReferenceById(8L));
            zapatilla8.setModelo(modeloRepository.getReferenceById(8L));
            zapatilla8.setImagen("CrocsCars");

            zapatilla9.setNombre("Skate Rules X Beetlejuice");
            zapatilla9.setPrecio(115);
            zapatilla9.setColor("Negro");
            zapatilla9.setTalla("44");
            zapatilla9.setStock(3);
            zapatilla9.setMarcas(marcaRepository.getReferenceById(9L));
            zapatilla9.setModelo(modeloRepository.getReferenceById(9L));
            zapatilla9.setImagen("Vans2");

            zapatilla10.setNombre("Annapurna's Gods");
            zapatilla10.setPrecio(230);
            zapatilla10.setColor("Verdes");
            zapatilla10.setTalla("43");
            zapatilla10.setStock(2);
            zapatilla10.setMarcas(marcaRepository.getReferenceById(10L));
            zapatilla10.setModelo(modeloRepository.getReferenceById(10L));
            zapatilla10.setImagen("Salomon3");

            zapatilla11.setNombre("Priv Coll");
            zapatilla11.setPrecio(350);
            zapatilla11.setColor("Blanco");
            zapatilla11.setTalla("41");
            zapatilla11.setStock(1);
            zapatilla11.setMarcas(marcaRepository.getReferenceById(11L));
            zapatilla11.setModelo(modeloRepository.getReferenceById(11L));
            zapatilla11.setImagen("OffWhite");

            zapatilla12.setNombre("Dev Edit");
            zapatilla12.setPrecio(5);
            zapatilla12.setColor("Gris");
            zapatilla12.setTalla("46");
            zapatilla12.setStock(3);
            zapatilla12.setMarcas(marcaRepository.getReferenceById(12L));
            zapatilla12.setModelo(modeloRepository.getReferenceById(12L));
            zapatilla12.setImagen("IberSneaks");

            zapatilla13.setNombre("Love Griffin");
            zapatilla13.setPrecio(180);
            zapatilla13.setColor("Salmon");
            zapatilla13.setTalla("37");
            zapatilla13.setStock(1);
            zapatilla13.setMarcas(marcaRepository.getReferenceById(2L));
            zapatilla13.setModelo(modeloRepository.getReferenceById(2L));
            zapatilla13.setImagen("PeterGrifin5");

            zapatilla14.setNombre("Family Guy");
            zapatilla14.setPrecio(100);
            zapatilla14.setColor("Azul");
            zapatilla14.setTalla("36");
            zapatilla14.setStock(1);
            zapatilla14.setMarcas(marcaRepository.getReferenceById(2L));
            zapatilla14.setModelo(modeloRepository.getReferenceById(2L));
            zapatilla14.setImagen("PeterGrifin2");

            zapatilla15.setNombre("Stan Griffin");
            zapatilla15.setPrecio(190);
            zapatilla15.setColor("Banco");
            zapatilla15.setTalla("42");
            zapatilla15.setStock(1);
            zapatilla15.setMarcas(marcaRepository.getReferenceById(2L));
            zapatilla15.setModelo(modeloRepository.getReferenceById(2L));
            zapatilla15.setImagen("PeterGrifin1");

            zapatilla16.setNombre("Lois Style");
            zapatilla16.setPrecio(100);
            zapatilla16.setColor("Azul");
            zapatilla16.setTalla("39");
            zapatilla16.setStock(1);
            zapatilla16.setMarcas(marcaRepository.getReferenceById(2L));
            zapatilla16.setModelo(modeloRepository.getReferenceById(2L));
            zapatilla16.setImagen("PeterGrifin7");

            zapatilla17.setNombre("DunkLow Cream");
            zapatilla17.setPrecio(120);
            zapatilla17.setColor("Crema");
            zapatilla17.setTalla("40");
            zapatilla17.setStock(2);
            zapatilla17.setMarcas(marcaRepository.getReferenceById(1L));
            zapatilla17.setModelo(modeloRepository.getReferenceById(1L));
            zapatilla17.setImagen("DunkCrema");

            zapatilla18.setNombre("JordanXCeltics");
            zapatilla18.setPrecio(400);
            zapatilla18.setColor("Verde-Blanco");
            zapatilla18.setTalla("44");
            zapatilla18.setStock(1);
            zapatilla18.setMarcas(marcaRepository.getReferenceById(1L));
            zapatilla18.setModelo(modeloRepository.getReferenceById(1L));
            zapatilla18.setImagen("Jordan1Verdes");

            zapatilla19.setNombre("Casual 50");
            zapatilla19.setPrecio(50);
            zapatilla19.setColor("Marron-Gris");
            zapatilla19.setTalla("37");
            zapatilla19.setStock(3);
            zapatilla19.setMarcas(marcaRepository.getReferenceById(3L));
            zapatilla19.setModelo(modeloRepository.getReferenceById(3L));
            zapatilla19.setImagen("NBDailyTown");

            zapatillasIni.add(zapatilla);
            zapatillasIni.add(zapatilla1);
            zapatillasIni.add(zapatilla2);
            zapatillasIni.add(zapatilla3);
            zapatillasIni.add(zapatilla4);
            zapatillasIni.add(zapatilla5);
            zapatillasIni.add(zapatilla6);
            zapatillasIni.add(zapatilla7);
            zapatillasIni.add(zapatilla8);
            zapatillasIni.add(zapatilla9);
            zapatillasIni.add(zapatilla10);
            zapatillasIni.add(zapatilla11);
            zapatillasIni.add(zapatilla12);
            zapatillasIni.add(zapatilla13);
            zapatillasIni.add(zapatilla14);
            zapatillasIni.add(zapatilla15);
            zapatillasIni.add(zapatilla16);
            zapatillasIni.add(zapatilla17);
            zapatillasIni.add(zapatilla18);
            zapatillasIni.add(zapatilla19);

            zapatillaRepository.saveAll(zapatillasIni);
        }
    }  

    

}
