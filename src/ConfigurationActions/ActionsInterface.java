package ConfigurationActions;

import Personagem.Personagem;
import Personagem.Personagem;

public interface ActionsInterface {
    void attack(double damage, Personagem personagem);
    void defense(double damage, Personagem personagem);
    void special(double damage, Personagem personagem);
}
