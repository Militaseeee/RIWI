package entity;

import interfaces.IElectric;

public class Pikachu extends Pokemon implements IElectric {

    public Pikachu() {
    }

    @Override
    protected void attackTackle() {
        System.out.println("Hi, i'm Pikachu, this is my attack of placaje");
    }

    @Override
    protected void attackScratch() {
        System.out.println("Hi, i'm Pikachu, this is my attack of arañazo");
    }

    @Override
    protected void attackNibble() {
        System.out.println("Hi, i'm Pikachu, this is my attack of Mordisco");
    }

    @Override
    public void attrackImpactrueno() {
        System.out.println("Impactrueno Pikachu");
    }

    @Override
    public void attrackpuniotrueno() {
        System.out.println("Puño trueno Pikachu");
    }
}
