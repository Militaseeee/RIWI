package app.Model;

public class Food extends Product{
    public Food(String nombre, double price) {
        super(nombre, price);
    }

    @Override
    public String getDescription() {
        return "Food";
    }
}
