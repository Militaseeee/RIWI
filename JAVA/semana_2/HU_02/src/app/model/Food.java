package app.model;

public class Food extends Product{
    public Food(String nombre, double price) {
        super(nombre, price);
    }

    @Override
    public String getDescription() {
        return "This is a food made in Colombia";
    }
}
