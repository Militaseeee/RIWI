import controller.CoderController;
import database.ConfigDB;

import javax.swing.*;
import java.sql.Connection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Connection connection = ConfigDB.openConnection();

        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    1. Create coder
                    2. Get all coder
                    3. Update coder
                    4. Delete coder
                    5. Close
                    """);
            switch (option) {
                case "1":
                    CoderController.create();
                    break;
                case "2":
                    CoderController.getAll();
                    break;
                case "3":
                    CoderController.update();
                    break;
                case "4":
                    CoderController.delete();
                    break;
                case "5":
                    break;
            }

        } while (!option.equals("5"));

    }
}