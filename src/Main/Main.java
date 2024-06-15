package Main;

import ConfigurationActions.Attack.Attack;
import Personagem.Heroes.Arqueiro;
import Personagem.Heroes.Bruxa;
import Personagem.PersonagemBase;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PersonagemBase personagemBase = escolherPersonagem();

        if (personagemBase == null) {
            System.out.println("Personagem inválido");
            System.exit(0);
        }

        Attack attack = new Attack(personagemBase);

        exibirInformacoes(personagemBase);
        escolherHabilidade(personagemBase, attack);

        System.out.println("Dano total causado: " + personagemBase.getDanoTotalCausado());
    }

    private static void escolherHabilidade(PersonagemBase personagemBase, Attack attack) {
        while (true) {
            if (!attack.isEspecialEmExecucao()) {
                exibirQuadroHabilidades(personagemBase, attack);

                int escolhaHabilidade = scanner.nextInt();
                scanner.nextLine();

                switch (escolhaHabilidade) {
                    case 1:
                        attack.action(personagemBase.getHabilidade1());
                        personagemBase.incrementarDanoCausado(personagemBase.getHabilidade1());
                        break;
                    case 2:
                        attack.action(personagemBase.getHabilidade2());
                        personagemBase.incrementarDanoCausado(personagemBase.getHabilidade2());
                        break;
                    case 3:
                        attack.action(personagemBase.getHabilidade3());
                        personagemBase.incrementarDanoCausado(personagemBase.getHabilidade3());
                        break;
                    case 4:
                        if (attack.isEspecialDesbloqueado()) {
                            attack.action(personagemBase.getHabilidadeSpecial());
                            personagemBase.incrementarDanoCausado(personagemBase.getHabilidadeSpecial());
                        } else {
                            System.out.println("Ataque especial ainda não desbloqueado.");
                        }
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Habilidade inválida");
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void exibirInformacoes(PersonagemBase personagemBase) {
        System.out.println("Nome: " + personagemBase.getNome());
        System.out.println("Habilidade 1: " + personagemBase.getHabilidade1() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade1()) + ")");
        System.out.println("Habilidade 2: " + personagemBase.getHabilidade2() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade2()) + ")");
        System.out.println("Habilidade 3: " + personagemBase.getHabilidade3() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade3()) + ")");
        System.out.println("Habilidade Special: " + personagemBase.getHabilidadeSpecial());
    }

    private static void exibirQuadroHabilidades(PersonagemBase personagemBase, Attack attack) {
        System.out.println("Escolha uma habilidade para atacar: ");
        System.out.println("+---------------------------------+");
        System.out.println("| 1 - " + personagemBase.getHabilidade1() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade1()) + ")");
        System.out.println("| 2 - " + personagemBase.getHabilidade2() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade2()) + ")");
        System.out.println("| 3 - " + personagemBase.getHabilidade3() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade3()) + ")");
        if (attack.isEspecialDesbloqueado()) {
            System.out.println("| 4 - " + personagemBase.getHabilidadeSpecial() + " (ESPECIAL)");
        }
        System.out.println("| 0 - Sair");
        System.out.println("+---------------------------------+");
    }

    private static PersonagemBase escolherPersonagem() {
        System.out.println("Escolha seu personagem: ");
        System.out.println("1 - Arqueiro");
        System.out.println("2 - Bruxa");
        int escolherPersonagem = scanner.nextInt();
        scanner.nextLine();

        switch (escolherPersonagem) {
            case 1:
                return new Arqueiro();
            case 2:
                return new Bruxa();
            default:
                return null;
        }
    }
}
