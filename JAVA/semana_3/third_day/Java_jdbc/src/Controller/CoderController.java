package Controller;

import Entity.Coder;
import Model.CoderModel;

import javax.swing.*;

public class CoderController {

    public static void create() {
        // Usamos el modelo
        CoderModel objCoderModel = new CoderModel();

        // Pedir los datos el usuario
        String name = JOptionPane.showInputDialog("Give me a name: ");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Give me an age: "));
        String clan = JOptionPane.showInputDialog("Give me a clan: ");

        Coder objCoder = new Coder();
        objCoder.setName(name);
        objCoder.setAge(age);
        objCoder.setClan(clan);

        // Llamando
        objCoder = (Coder) objCoderModel.insert(objCoder);

        JOptionPane.showMessageDialog(null, objCoder.toString());


    }

    public static void getAll() {
        CoderModel coderModel = new CoderModel();
        String listCoders = "List coders";
        for (Object i : coderModel.findAll()) {
            Coder objCoder = (Coder) i;
            listCoders += objCoder.toString() + "\n";
        }

        JOptionPane.showMessageDialog(null, listCoders);
    }

    public static void delete() {
        CoderModel coderModel = new CoderModel();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog("Enter the coder ID:"));
        Coder objCoder = coderModel.findById(idDelete);

        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "Coder nor found");
        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?");
            if (confirm == 0) coderModel.delete(objCoder);
        }
    }
}
