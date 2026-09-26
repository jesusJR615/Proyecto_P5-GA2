package p5.g2.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import p5.g2.models.product;

@Controller
public class configController {
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

                return "productsList"; // Redirijo a la vista 'productsList'
                
            }
        }
        return "productsList";
    }

    @ModelAttribute ("products")
    public List<product> productModel(){
        return Arrays.asList(
            new product(1, "Televisor", 400.6, "Televisor de 55 pulgadas oled"),
            new product(2, "Iphone 18", 1200.3, "último telefono de Apple"),
            new product(3, "Lavadora", 300.0, "Lavadora de 7kg de carga "),
            new product(4, "Microondas", 85.2, "Microondas con funcion de horno")
        );
    }
    
}
