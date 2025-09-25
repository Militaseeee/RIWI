package controller;

import entity.Coder;
import model.CoderModel;
import util.Validation;

import javax.swing.*;

// CONTROLLER -> LA COMUNICACIÓN CON EL USUARIO

public class CoderController {

    public static void create() {

        // Usamos el modelo
        CoderModel objCoderModel = new CoderModel();

        // Pedir los datos el usuario
        String name = JOptionPane.showInputDialog("Give me a name: ");
        Validation.validSearchInput(name);
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

    public static void update() {
        CoderModel coderModel = new CoderModel();

        getAll();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog("Enter the coder ID:"));
        Coder objCoder = coderModel.findById(idUpdate);

        if (objCoder == null) {
            JOptionPane.showMessageDialog(null, "Coder nor found");
        } else {
            String newName = JOptionPane.showInputDialog("Enter new name:", objCoder.getName());
            int newAge = Integer.parseInt(JOptionPane.showInputDialog("Enter new age:", objCoder.getAge()));
            String newClan = JOptionPane.showInputDialog("Enter new clan:", objCoder.getClan());

            // Actualizar el objeto con los nuevos datos (ID no cambia)
            objCoder.setName(newName);
            objCoder.setAge(newAge);
            objCoder.setClan(newClan);

            // Llamar al update en el modelo
            boolean updated = coderModel.update(objCoder);

            if (updated) {
                JOptionPane.showMessageDialog(null, "Coder updated: \n" + objCoder);
            }
        }
    }

    public static void delete() {
        CoderModel coderModel = new CoderModel();

        getAll();

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
