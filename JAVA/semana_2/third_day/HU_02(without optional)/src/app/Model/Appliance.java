package app.Model;

public class Appliance extends Product{
    public Appliance(String nombre, double price) {
        super(nombre, price);
    }

    @Override
    public String getDescription() {

        return "Apliance";
    }
}
