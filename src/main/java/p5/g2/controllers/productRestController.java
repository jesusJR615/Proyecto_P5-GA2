package p5.g2.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import p5.g2.models.product;

@RestController
// REST Controller (/api/products)
@RequestMapping("/api/products")
public class productRestController {

    // GET /api/products → lista de productos.
    @GetMapping
    public List<product> get() {
        // Try Catch para comprobar que la lista que se está pidiendo existe o está
        // vacía.
        try {
            if (products == null || products.isEmpty()) {
                return null; // En caso de que no exista, no devuelve nada
            }
            return products; // Si existe, muestra la lista.
        } catch (Exception e) {
            return null;
        }
    }

    // GET /api/products/{id} → devuelve un producto específico.
    @GetMapping("/{id}")
    public product get(@PathVariable int id) {
        // Try Catch para comprobar que el producto con el "Id" especificado existe.
        try {
            for (product p : products) { // Recorremos el for para buscar el ID
                if (p.getId() == id) {
                    return p; // Si existe, se muestra el producto con el "Id" especificado
                }
            }
            return null; // En caso de que no exista, no devuelve nada
        } catch (Exception e) {
            return null;
        }
    }

    // POST /api/products → agrega un producto (en JSON).
    @PostMapping
    public product post(@RequestBody product product) {
        // Try Catch para comprobar que el formato JSON se cumple para poder añadir un
        // producto a la lista
        try {
            if (product == null || product.getName() == null || product.getName().trim().isEmpty()
                    || product.getPrice() <= 0) {
                return null; // Si el formato no se cumple, devuelve un null
            }
            product.setId(nextId++); // Si se cumple, se añade el producto
            products.add(product);
            return product; // Automáticamente devuelve el producto para que sepamos que se ha añadido
        } catch (Exception e) {
            return null; // Si no se cumple, salta el catch
        }
    }

    // PUT /api/products/{id} → modifica un producto.
    @PutMapping("/{id}")
    public product put(@PathVariable int id, @RequestBody product product) {
        // Try Catch para comprobar que el formato JSON se cumple para poder modificar
        // un producto
        try {
            if (product == null || product.getName() == null || product.getName().trim().isEmpty()
                    || product.getPrice() <= 0) {
                return null; // Si el formato no se cumple, devuelve un null
            }
            for (int i = 0; i < products.size(); i++) { // Recorremos el for para buscar el ID del producto a modificar
                if (products.get(i).getId() == id) {
                    product.setId(id);
                    products.set(i, product);
                    return product; // Si existe, se muestra el producto modificado
                }
            }
            return null; // En caso de que no exista, no devuelve nada
        } catch (Exception e) {
            return null; // Si no se cumple, salta el catch
        }
    }

    // DELETE /api/products/{id} → elimina un producto.
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        // Try Catch para comprobar que el producto con el "Id" especificado existe
        // antes de eliminarlo
        try {
            products.removeIf(p -> p.getId() == id);
        } catch (Exception e) {
            // Silencioso
        }
    }

    // Lista de productos en memoria (base de datos simulada)
    private List<product> products = new ArrayList<>(Arrays.asList(
            new product(1, "Televisor", 400.6, "Televisor de 55 pulgadas OLED"),
            new product(2, "Iphone 18", 1200.3, "Último teléfono de Apple"),
            new product(3, "Lavadora", 300.0, "Lavadora de 7 kg de carga"),
            new product(4, "Microondas", 85.2, "Microondas con función de horno")));

    // Contador para IDs autoincrementales
    private int nextId = 5;
}
