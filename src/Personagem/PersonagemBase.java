package Personagem;

import ConfigurationActions.MapActions.SkillsDefault;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class PersonagemBase {
    private String nome;
    private String descricao;
    private String habilidade1;
    private String habilidade2;
    private String habilidade3;
    private double vida;
    private Map<String, Double> danoDeCadaHabilidade = new HashMap<>();

    // Construtor que inicializa danoDeCadaHabilidade com valores padrão
    public PersonagemBase(String nome, String habilidade1, String habilidade2, String habilidade3) {
        this.nome = nome;
        this.habilidade1 = habilidade1;
        this.habilidade2 = habilidade2;
        this.habilidade3 = habilidade3;

        Map<String, Double> skillsDefault = SkillsDefault.getSkillsDefault();
        danoDeCadaHabilidade.put(habilidade1, skillsDefault.getOrDefault(habilidade1, 0.0));
        danoDeCadaHabilidade.put(habilidade2, skillsDefault.getOrDefault(habilidade2, 0.0));
        danoDeCadaHabilidade.put(habilidade3, skillsDefault.getOrDefault(habilidade3, 0.0));
    }

    public String getInformations() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Habilidade 1: ").append(habilidade1).append(" (Dano: ").append(danoDeCadaHabilidade.get(habilidade1)).append(")\n");
        sb.append("Habilidade 2: ").append(habilidade2).append(" (Dano: ").append(danoDeCadaHabilidade.get(habilidade2)).append(")\n");
        sb.append("Habilidade 3: ").append(habilidade3).append(" (Dano: ").append(danoDeCadaHabilidade.get(habilidade3)).append(")\n");
        return sb.toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getHabilidade1() {
        return habilidade1;
    }

    public void setHabilidade1(String habilidade1) {
        this.habilidade1 = habilidade1;
    }

    public String getHabilidade2() {
        return habilidade2;
    }

    public void setHabilidade2(String habilidade2) {
        this.habilidade2 = habilidade2;
    }

    public String getHabilidade3() {
        return habilidade3;
    }

    public void setHabilidade3(String habilidade3) {
        this.habilidade3 = habilidade3;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public Map<String, Double> getDanoDeCadaHabilidade() {
        return danoDeCadaHabilidade;
    }

    public void setDanoDeCadaHabilidade(Map<String, Double> danoDeCadaHabilidade) {
        this.danoDeCadaHabilidade = danoDeCadaHabilidade;
    }
}
