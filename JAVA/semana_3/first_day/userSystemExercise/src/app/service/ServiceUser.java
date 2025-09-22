package app.service;

import app.security.IAuthenticable;

import javax.swing.*;

public class ServiceUser implements IUser, IAuthenticable {
    @Override
    public void saveData() {

        String typeEmail = JOptionPane.showInputDialog("Type the name of the product:");
    }

    @Override
    public void updateData() {

    }

    @Override
    public void createUser() {

    }

    @Override
    public void loginUser() {

    }
}
