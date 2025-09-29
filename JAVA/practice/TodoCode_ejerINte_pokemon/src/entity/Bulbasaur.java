package entity;

import interfaces.IPlant;

public class Bulbasaur extends Pokemon implements IPlant {

    public Bulbasaur() {
    }

    @Override
    protected void attackTackle() {
        System.out.println("Hi, i'm bulbasaur, this is my attack of placaje");
    }

    @Override
    protected void attackScratch() {
        System.out.println("Hi, i'm bulbasaur, this is my attack of arañazo");
    }

    @Override
    protected void attackNibble() {
        System.out.println("Hi, i'm bulbasaur, this is my attack of Mordisco");
    }

    @Override
    public void attrackDrenaje() {
        System.out.println("Drenaje bulbasaur");
    }

    @Override
    public void attrackParalizar() {
        System.out.println("Paralizar bulbasaur");
    }
}