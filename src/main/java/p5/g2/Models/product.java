package p5.g2.models;
 

public class product {
    //Declaro los atributos de la clase product
    private int id;
    private String name;
    private double price;
    private String description;

    //Creo el contructor vacio
    public product() {
    }

    //Creo el contructor
    public product(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    //Creo los metodos get y set de cada atributo
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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