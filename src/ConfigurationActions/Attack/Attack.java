package ConfigurationActions.Attack;

import ConfigurationActions.ActionsInterface;
import Personagem.PersonagemBase;

import java.util.logging.Logger;

public class Attack implements ActionsInterface {
    private static final Logger LOGGER = Logger.getAnonymousLogger();
    private PersonagemBase personagemBase;

    public Attack(PersonagemBase personagemBase) {
        this.personagemBase = personagemBase;
    }

    @Override
    public void action(String optionSkill) {
        attackDefault(optionSkill);
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
}
