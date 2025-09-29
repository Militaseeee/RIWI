package entity;

import interfaces.IFire;

public class Charmander extends Pokemon implements IFire {

    public Charmander() {
    }

    @Override
    protected void attackTackle() {
        System.out.println("Hi, i'm Charmander, this is my attack of placaje");
    }

    @Override
    protected void attackScratch() {
        System.out.println("Hi, i'm Charmander, this is my attack of arañazo");
    }

    @Override
    protected void attackNibble() {
        System.out.println("Hi, i'm Charmander, this is my attack of Mordisco");
    }

    @Override
    public void attrackPunioFuego() {
        System.out.println("Puño Fuego Charmander");
    }

    @Override
    public void attrackLanzarLlamas() {
        System.out.println("Lanzar Llamas Charmander");
    }

    @Override
    public void attrackAscuas() {
        System.out.println("Ascuas Charmander");
    }
}
