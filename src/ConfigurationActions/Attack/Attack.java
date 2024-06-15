package ConfigurationActions.Attack;

import ConfigurationActions.ActionsInterface;
import Personagem.PersonagemBase;

import java.util.logging.Logger;

public class Attack implements ActionsInterface {
    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private PersonagemBase personagemBase;
    private int ataquesPadraoRealizados;
    private boolean especialDesbloqueado;

    public Attack(PersonagemBase personagemBase) {
        this.personagemBase = personagemBase;
        this.ataquesPadraoRealizados = 0;
        this.especialDesbloqueado = false;
    }

    @Override
    public void action(String optionSkill) {
        if (especialDesbloqueado && isSpecialSkill(optionSkill)) {
            attackSpecial(optionSkill);
            reiniciarAtaquePadrao();
        } else {
            attackDefault(optionSkill);
            ataquesPadraoRealizados++;

            if (ataquesPadraoRealizados >= 3) {
                especialDesbloqueado = true;
                System.out.println("Você desbloqueou o ataque especial: " + personagemBase.getHabilidadeSpecial());
            }
        }
    }

    private void attackDefault(String habilidade) {
        habilidade = habilidade.toLowerCase();
        Double dano = personagemBase.getDanoDeCadaHabilidade().get(habilidade);

        if (dano != null) {
            System.out.println("Ataque com " + habilidade + " causou " + dano + " de dano.");
        } else {
            System.out.println("Habilidade " + habilidade + " não encontrada.");
        }
    }

    private void attackSpecial(String habilidade) {
        habilidade = habilidade.toLowerCase();
        if (isSpecialSkill(habilidade)) {
            Double dano = personagemBase.getDanoDeCadaHabilidade().get(habilidade);

            if (dano != null) {
                System.out.println("Ataque especial com " + habilidade + " causou " + dano + " de dano.");
            } else {
                System.out.println("Habilidade " + habilidade + " não encontrada.");
            }
        } else {
            System.out.println("Habilidade " + habilidade + " não é o ataque especial.");
        }
    }

    public boolean isEspecialDesbloqueado(){
        return especialDesbloqueado;
    }

    public void reiniciarAtaquePadrao(){
        ataquesPadraoRealizados = 0;
        especialDesbloqueado = false;
        System.out.println("Contador de ataques padrão reiniciado.");
    }

    public boolean isSpecialSkill(String habilidade){
        return habilidade.equalsIgnoreCase(personagemBase.getHabilidadeSpecial());
    }
}
