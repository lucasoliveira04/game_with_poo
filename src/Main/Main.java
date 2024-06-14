package Main;

import ConfigurationActions.Attack.Attack;
import Personagem.Heroes.Arqueiro;
import Personagem.PersonagemBase;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Escolher personagem
        System.out.println("Escolha seu personagem:");
        System.out.println("1 - Arqueiro");
        int escolhaPersonagem = scanner.nextInt();
        scanner.nextLine(); // consumir a linha pendente

        PersonagemBase personagem = null;

        switch (escolhaPersonagem) {
            case 1:
                personagem = new Arqueiro();
                break;
            default:
                System.out.println("Personagem inválido.");
                System.exit(1);
        }

        Attack attack = new Attack(personagem);

        // Exibir informações do personagem
        System.out.println(personagem.getInformations());

        // Loop para escolher habilidades
        while (true) {
            System.out.println("Escolha uma habilidade para atacar:");
            System.out.println("1 - " + personagem.getHabilidade1());
            System.out.println("2 - " + personagem.getHabilidade2());
            System.out.println("3 - " + personagem.getHabilidade3());
            System.out.println("0 - Sair");

            int escolhaHabilidade = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaHabilidade) {
                case 1:
                    attack.action(personagem.getHabilidade1());
                    break;
                case 2:
                    attack.action(personagem.getHabilidade2());
                    break;
                case 3:
                    attack.action(personagem.getHabilidade3());
                    break;
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Habilidade inválida.");
            }
        }
    }
}
