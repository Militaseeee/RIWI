package app.model;

public class Appliance extends Product{
    public Appliance(String nombre, double price) {
        super(nombre, price);
    }

    @Override
    public String getDescription() {

        return "This contains the best technology on the market";
    }
}
