import entity.Bulbasaur;
import entity.Charmander;
import entity.Pikachu;
import entity.Squirtle;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //Creo objetos
        Squirtle squirtle = new Squirtle();
        Charmander charmander = new Charmander();
        Bulbasaur bulbasaur = new Bulbasaur();
        Pikachu pikachu = new Pikachu();

        squirtle.attackScratch();
        charmander.attrackLanzarLlamas();
        bulbasaur.attrackDrenaje();
        pikachu.attrackImpactrueno();

    }
}