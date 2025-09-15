import javax.swing.*;
import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Fruit {
    String name;
    String type;
    String color;
    String origin;
    double weight;
    double date;

    // constructor method
    Fruit (String nameFruit, String typeFruit, String colorFruit, String originFruit, double weightFruit, double dateFruit) {
        this.name = nameFruit;
        this.type = typeFruit;
        this.color = colorFruit;
        this.origin = originFruit;
        this.weight = weightFruit;
        this.date = dateFruit;
    }

    public void originCity () {
        JOptionPane.showInternalMessageDialog(null, "Made in Colombia");
        // System.out.println("Made in Colombia");
    }

    public void expirationDate () {
        JOptionPane.showInternalMessageDialog(null, "September 16 expires");
        // System.out.println("September 16 expires");
    }

}
