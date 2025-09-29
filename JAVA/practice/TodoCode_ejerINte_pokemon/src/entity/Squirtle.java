package entity;

import interfaces.IWater;

public class Squirtle extends Pokemon implements IWater {

    public Squirtle() {
    }

    @Override
    protected void attackTackle() {
        System.out.println("Hi, i'm squirtle, this is my attack of Mordisco");
    }

    @Override
    public void attackScratch() {
        System.out.println("Hi, i'm squirtle, this is my attack of Mordisco");
    }

    @Override
    protected void attackNibble() {
        System.out.println("Hi, i'm squirtle, this is my attack of Mordisco");
    }

    @Override
    public void attrackHidroBomba() {
        System.out.println("Hidro Bomba squirtle");
    }

    @Override
    public void attrackBurbuja() {
        System.out.println("Burbuja squirtle");
    }

    @Override
    public void attrackPistolaAgua() {
        System.out.println("Pistola Agua squirtle");
    }
}
