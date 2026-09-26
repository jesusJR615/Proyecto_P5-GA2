package p5.g2.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import p5.g2.models.product;

@Controller

public class configController {
    private List<product> newProducts = new ArrayList<>(); //Creo una lista de productos para insertar un producto desde 'productsSave'

    //Mustra la lista completa de todos los productos
    @GetMapping("/products/list")
    public String productsList(ModelMap model) {
        //Retorno el nombre de la plantilla Thymeleaf
        return "productsList"; 
    }

    //Mustra los detalles de un producto
    @GetMapping("/products/details/{id}")
    public String productsDetailsID(@PathVariable Integer id, ModelMap model){

        //Recorro todos los productos
        for(product p : productModel()){

            //Realizo la comparacion del producto por id
            if (p.getId() == id) {
                model.addAttribute("product", p);

                return "productsDetails"; // Redirijo a la vista 'productsList'
                
            }
        }
        return "productsList";
    }

    //Mostrar formulario del producto
     @GetMapping("/products/new")
         public String productsNew(ModelMap model) {
        model.addAttribute("product", new product());

        return "productsNew";
    }

    //Insertar produto
    @PostMapping("/products/save")
    public String productsSave(@ModelAttribute product product){
        newProducts.add(product); //Añadirmos un producto
        return "productsList";
    }


    @ModelAttribute ("products")
    //Creo una lista de productos
    public List<product> productModel(){
        //Creo otra lista de productos para poder insertar un nuevo producto
       List<product> products = new ArrayList<>(Arrays.asList(
            new product(1, "Televisor", 400.6, "Televisor de 55 pulgadas oled"),
            new product(2, "Iphone 18", 1200.3, "último telefono de Apple"),
            new product(3, "Lavadora", 300.0, "Lavadora de 7kg de carga "),
            new product(4, "Microondas", 85.2, "Microondas con funcion de horno")
        ));
        products.addAll(newProducts); //Añadimos todos los productos
        return products;

    }
    
}
