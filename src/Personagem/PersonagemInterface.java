package Personagem;

import java.util.List;

public interface PersonagemInterface {
    List<Integer> getId();

    void setId(List<Integer> id);

    String getNome();
    void setNome(String nome);

    double getHabilidadeDano(String habilidade);
    void setHabilidadeDano(String habilidade, double dano);

    double getDano();
    void setDano(double dano);

    String getHabilidade1();

    void setHabilidade1(String habilidade1);

    double getVida();
    void setVida(double vida);
}
