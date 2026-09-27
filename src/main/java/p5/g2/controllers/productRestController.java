package p5.g2.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p5.g2.models.product;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/products")
public class productRestController {
    // Mostramos todos los productos que hay en la lista
    @GetMapping
    public List<product> get() {
        return products;
    }

    //Muestra un producto y sus detalles, por su ID
    @GetMapping("/{id}")
    public ResponseEntity<product> get(@PathVariable int id) {
        for (product p : products) {
            if (p.getId() == id) {
                return ResponseEntity.ok(p); // Recoge el status, en este caso HTTP 200
            }                                // Debido a que el producto a mostrar si que existe
        }
        return ResponseEntity.notFound().build(); // Recoge el status, en este caso HTTP 404
                                                  // Debido a que el producto a mostrar no existe
    }

    //Inserta un nuevo producto a la lista ya existente
    @PostMapping
    public ResponseEntity<product> post(@RequestBody product product) {
        product.setId(nextId++); //Asigna un ID que se autoincrementa
        products.add(product); //Añade el producto a la lista
        return ResponseEntity.status(HttpStatus.CREATED).body(product); //Recoge el status, en este caso HTTP 201
                                                                        // debido a qeu se a añadido con exito 
    }

    @PutMapping("/{id}")
    public ResponseEntity<product> put(@PathVariable int id, @RequestBody product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                product.setId(id);
                products.set(i, product);
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // Elimina un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        boolean removed = products.removeIf(p -> p.getId() == id);  // Elimina un producto si este existe
        if (removed) {
            return ResponseEntity.noContent().build(); // Recoge el status, en este caso HTTP 204
                                                       // Debido a que el producto se ha eliminado 
        }
        return ResponseEntity.notFound().build(); // Recoge el status, en este caso HTTP 404
                                                  // Debido a que el producto a eliminar no existía
    }


    // Es una lista de productos, que actua como base de datos en memoria.
    private List<product> products = new ArrayList<>(Arrays.asList(
            new product(1, "Televisor", 400.6, "Televisor de 55 pulgadas oled"),
            new product(2, "Iphone 18", 1200.3, "Último teléfono de Apple"),
            new product(3, "Lavadora", 300.0, "Lavadora de 7kg de carga"),
            new product(4, "Microondas", 85.2, "Microondas con función de horno")));
    // Es el contador de IDs para poder tener un ID autoincremental        
    private int nextId = 5;
}