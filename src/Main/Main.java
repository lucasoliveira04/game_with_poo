package Main;

import ConfigurationActions.Actions;
import Personagem.Personagem;
import Personagem.Arqueiro;
import Personagem.Bruxa;

public class Main {
    public static void main(String[] args) {
        Actions actions = new Actions();
        describrePersonagens();
    }

    private static void describrePersonagens(){
        Personagem arqueiro = new Arqueiro();
        System.out.println(arqueiro.getInfo());

        Personagem bruxa = new Bruxa();
        System.out.println(bruxa.getInfo());
    }
}
