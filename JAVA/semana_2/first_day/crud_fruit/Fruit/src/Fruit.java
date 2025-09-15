import javax.swing.*;

public class Fruit {

    private int idFruit;
    private String name;
    private double weightKg;
    private String color;
    private double price;
    private String origin;
    private boolean isOrganic = false;

    private static  int counter = 0;

    public Fruit(String name, double weightKg, String color, double price, String origin, boolean isOrganic) {

        counter++;
        this.idFruit = counter;
        this.name = name;
        this.weightKg = weightKg;
        this.color = color;
        this.price = price;
        this.origin = origin;
        this.isOrganic = isOrganic;

    }

    public int getIdFruit() {
        return idFruit;
    }

    public void setIdFruit(int idFruit) {
        this.idFruit = idFruit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;

        if (weightKg < 0) {
            JOptionPane.showInternalMessageDialog(null, "Weight should be a positive number");
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;

        if (price < 0) {
            JOptionPane.showInternalMessageDialog(null, "price should be a positive number");
        }
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(boolean isOrganic) {
        this.isOrganic = isOrganic;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Fruit.counter = counter;
    }
}
