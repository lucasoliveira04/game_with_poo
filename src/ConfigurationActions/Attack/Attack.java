package ConfigurationActions.Attack;

import ConfigurationActions.ActionsInterface;
import Personagem.PersonagemBase;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Attack implements ActionsInterface {
    private boolean turnoPlayer1;
    private PersonagemBase personagemBase;
    private boolean especialDesbloqueado = false;
    private boolean especialEmExecucao = false;
    private int ataquesPadraoRealizados = 0;
    private boolean defendendo = false;

    public Attack(PersonagemBase personagemBase) {
        this.personagemBase = personagemBase;
    }

    public void setTurnoPlayer1(boolean turnoPlayer1) {
        this.turnoPlayer1 = turnoPlayer1;
    }

    public void defender() {
        this.defendendo = true;
        if (turnoPlayer1) {
            System.out.println("Player 1 está defendendo no turno atual.");
        } else {
            System.out.println("Player 2 está defendendo no turno atual.");
        }
    }

    @Override
    public void action(String optionSkill, Attack oponenteAttack) {
        if (especialDesbloqueado && isSpecialSkill(optionSkill)) {
            attackSpecial(optionSkill, oponenteAttack);
            reiniciarAtaquePadrao();
        } else {
            attackDefault(optionSkill, oponenteAttack);
            ataquesPadraoRealizados++;
            if (ataquesPadraoRealizados >= 3) {
                especialDesbloqueado = true;
                System.out.println("Você desbloqueou o ataque especial: " + personagemBase.getHabilidadeSpecial());
            }
        }
        // Após a ação, redefinimos a defesa
        this.defendendo = false;
    }

    private void attackDefault(String habilidade, Attack oponenteAttack) {
        habilidade = habilidade.toLowerCase();
        Double dano = personagemBase.getDanoDeCadaHabilidade().get(habilidade);

        if (dano != null) {
            if (oponenteAttack.defendendo) {
                dano /= 2;
                System.out.println("Dano reduzido pela metade devido à defesa do oponente.");
            }
            oponenteAttack.personagemBase.receberDano(dano);
            System.out.println("Ataque com " + habilidade + " causou " + dano + " de dano.");
        } else {
            System.out.println("Habilidade inválida.");
        }
    }

    private boolean isSpecialSkill(String optionSkill) {
        return optionSkill.equalsIgnoreCase(personagemBase.getHabilidadeSpecial());
    }

    private void attackSpecial(String habilidade, Attack oponenteAttack) {
        habilidade = habilidade.toLowerCase();
        Double dano = personagemBase.getDanoDeCadaHabilidade().get(habilidade);

        if (dano != null) {
            if (oponenteAttack.defendendo) {
                dano /= 2;
                System.out.println("Dano reduzido pela metade devido à defesa do oponente.");
            }
            oponenteAttack.personagemBase.receberDano(dano);
            System.out.println("Ataque especial com " + habilidade + " causou " + dano + " de dano.");
            especialEmExecucao = true;
            // Temporizador para finalizar o estado especial após um período
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    especialEmExecucao = false;
                }
            }, 3000);
        } else {
            System.out.println("Habilidade especial inválida.");
        }
    }

    public boolean isEspecialEmExecucao() {
        return especialEmExecucao;
    }

    public boolean isEspecialDesbloqueado() {
        return especialDesbloqueado;
    }

    private void reiniciarAtaquePadrao() {
        ataquesPadraoRealizados = 0;
        especialDesbloqueado = false;
    }
}
