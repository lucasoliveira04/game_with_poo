package Personagem;

import java.util.Map;

public class Bruxa extends Personagem {
    public Bruxa() {
        super("Bruxa");
        setNome("bruxa");
        setHabilidade1("feitiço");
        setHabilidade2("ataque com a vassoura");
        setHabilidade3("regeneração");
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome do personagem: ").append(getNome()).append("\n");


        sb.append("Habilidade 1: ").append(getHabilidade1()).append(", Dano: ").append(getHabilidadeDano(getHabilidade1())).append("\n");
        sb.append(" (Invoca um feitiço poderoso que causa ").append(getHabilidadeDano(getHabilidade1())).append(" de dano inicial.\n");
        sb.append("  O dano aumenta progressivamente de 5 até 15 ao longo de 10 segundos, causando 1 de dano adicional por segundo)\n");


        sb.append("Habilidade 2: ").append(getHabilidade2()).append(", Dano: ").append(getHabilidadeDano(getHabilidade2())).append("\n");


        sb.append("Habilidade 3: regeneração, Dano: 0.0\n");

        sb.append("Dano total: ").append("Dano total: " + (15 + 5)).append("\n");

        return sb.toString();
    }

}
