package app;
import app.Model.Fruit;
import app.Service.FruitService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static app.Service.FruitService.*;

public class Main {
    private static FruitService fruitService;
    // private static FruitService service;
    // public Main(FruitService service) {
    //     this.service = service;
    // }

    public static void main(String[] args) {

        boolean finishProgram = true;

        do {
            String opcStr = JOptionPane.showInputDialog(null, "----------- Menu ----------- \n"+
                    "1. Crear fruta\n" +
                    "2. Listar todas\n" +
                    "3. Buscar (por id o por nombre parcial)\n" +
                    "4. Actualizar (cambiar uno o más atributos)\n" +
                    "5. Eliminar (por id)\n" +
                    "6. Salir\n", "Fruit Menu c:", JOptionPane.PLAIN_MESSAGE);
            switch (opcStr) {
                case "1":
                    createFruit();
                    // service.createFruit();

                    break;
                case "2":
                    fruitList();
                    // service.fruitList();
                    break;
                case "3":
                    searchFruit();
                    // service.searchFruit();
                    break;
                case "4":
                    updateFruit();
                    // service.updateFruit();
                    break;
                case "5":
                    deleteFruit();
                    // service.deleteFruit();
                    break;
                case "6":
                    finishProgram = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Incorrect option", "error", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        } while(finishProgram);
    }
}