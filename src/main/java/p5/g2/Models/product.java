package p5.g2.models;
 

public class product {
    //Declaro los atributos de la clase product
    private String name;
    private double price;
    private String description;

    //Creo el contructor vacio
    public product() {
    }

    //Creo el contructor
    public product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    //Creo los metodos get y set de cada atributo
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}