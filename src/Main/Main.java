package Main;

import ConfigurationActions.Attack.Attack;
import Personagem.Heroes.Arqueiro;
import Personagem.Heroes.Bruxa;
import Personagem.PersonagemBase;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final double PONTUACAO_ALVO = escolherPersonagem().getVida();

    public static void main(String[] args) {
        System.out.println("Player 1, escolha seu personagem:");
        PersonagemBase player1 = escolherPersonagem();
        if (player1 == null) {
            System.out.println("Personagem inválido para Player 1");
            System.exit(0);
        }

        System.out.println("Player 2, escolha seu personagem:");
        PersonagemBase player2 = escolherPersonagem();
        if (player2 == null) {
            System.out.println("Personagem inválido para Player 2");
            System.exit(0);
        }

        Attack attack1 = new Attack(player1);
        Attack attack2 = new Attack(player2);

        System.out.println("Player 1:");
        exibirInformacoesFormatadas(player1);
        System.out.println("Player 2:");
        exibirInformacoesFormatadas(player2);

        boolean jogoEmAndamento = true;
        boolean turnoPlayer1 = true;

        while (jogoEmAndamento) {
            if (turnoPlayer1) {
                System.out.println("Turno do Player 1:");
                attack1.setTurnoPlayer1(true);
                escolherHabilidade(player1, attack1, attack2);
                System.out.println("\nDano total causado pelo Player 1: " + player1.getDanoTotalCausado());
                if (player1.getDanoTotalCausado() >= PONTUACAO_ALVO) {
                    System.out.println("Player 1 venceu com " + player1.getDanoTotalCausado() + " de dano!");
                    jogoEmAndamento = false;
                }
                turnoPlayer1 = false;
            } else {
                System.out.println("Turno do Player 2:");
                attack2.setTurnoPlayer1(false);
                escolherHabilidade(player2, attack2, attack1);
                System.out.println("\nDano total causado pelo Player 2: " + player2.getDanoTotalCausado());
                if (player2.getDanoTotalCausado() >= PONTUACAO_ALVO) {
                    System.out.println("Player 2 venceu com " + player2.getDanoTotalCausado() + " de dano!");
                    jogoEmAndamento = false;
                }
                turnoPlayer1 = true;
            }

            System.out.println("\n-------------------------------------------\n");
        }

        System.out.println("Jogo terminou!");
        System.out.println("Dano total causado pelo Player 1: " + player1.getDanoTotalCausado());
        System.out.println("Dano total causado pelo Player 2: " + player2.getDanoTotalCausado());
    }

    private static void escolherHabilidade(PersonagemBase personagemBase, Attack attack, Attack oponenteAttack) {
        if (!attack.isEspecialEmExecucao()) {
            exibirQuadroHabilidades(personagemBase, attack);

            int escolhaHabilidade = scanner.nextInt();
            scanner.nextLine();

            switch (escolhaHabilidade) {
                case 1:
                    attack.action(personagemBase.getHabilidade1(), oponenteAttack);
                    personagemBase.incrementarDanoCausado(personagemBase.getHabilidade1());
                    break;
                case 2:
                    attack.action(personagemBase.getHabilidade2(), oponenteAttack);
                    personagemBase.incrementarDanoCausado(personagemBase.getHabilidade2());
                    break;
                case 3:
                    attack.action(personagemBase.getHabilidade3(), oponenteAttack);
                    personagemBase.incrementarDanoCausado(personagemBase.getHabilidade3());
                    break;
                case 4:
                    if (attack.isEspecialDesbloqueado()) {
                        attack.action(personagemBase.getHabilidadeSpecial(), oponenteAttack);
                        personagemBase.incrementarDanoCausado(personagemBase.getHabilidadeSpecial());
                    } else {
                        System.out.println("Ataque especial ainda não desbloqueado.");
                    }
                    break;
                case 5:
                    attack.defender();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
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

    private static void exibirInformacoesFormatadas(PersonagemBase personagemBase) {
        System.out.println("+---------------------------------+");
        System.out.println("| Nome: " + personagemBase.getNome());
        System.out.println("| Habilidade 1: " + personagemBase.getHabilidade1() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade1()) + ")");
        System.out.println("| Habilidade 2: " + personagemBase.getHabilidade2() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade2()) + ")");
        System.out.println("| Habilidade 3: " + personagemBase.getHabilidade3() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade3()) + ")");
        System.out.println("| Habilidade Special: " + personagemBase.getHabilidadeSpecial());
        System.out.println("+---------------------------------+");
    }

    private static void exibirQuadroHabilidades(PersonagemBase personagemBase, Attack attack) {
        System.out.println("+---------------------------------+");
        System.out.println("| Escolha uma habilidade:         |");
        System.out.println("| 1. " + personagemBase.getHabilidade1() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade1()) + ")");
        System.out.println("| 2. " + personagemBase.getHabilidade2() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade2()) + ")");
        System.out.println("| 3. " + personagemBase.getHabilidade3() + " (Dano: " + personagemBase.getDanoDeCadaHabilidade().get(personagemBase.getHabilidade3()) + ")");
        System.out.println("| 4. " + personagemBase.getHabilidadeSpecial() + " (Especial)");
        System.out.println("| 5. Defender");
        System.out.println("| 0. Sair");
        System.out.println("+---------------------------------+");

        if (attack.isEspecialDesbloqueado()) {
            System.out.println("Ataque especial desbloqueado!");
        }
    }

    private static PersonagemBase escolherPersonagem() {
        System.out.println("Escolha um personagem:");
        System.out.println("1. Arqueiro");
        System.out.println("2. Bruxa");

        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1:
                return new Arqueiro();
            case 2:
                return new Bruxa();
            default:
                return null;
        }
    }
}
