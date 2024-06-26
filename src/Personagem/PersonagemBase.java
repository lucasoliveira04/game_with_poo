package Personagem;

import ConfigurationActions.MapActions.SkillsDefault;
import ConfigurationActions.MapActions.SkillsSpecial;
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
    private String habilidadeSpecial;
    private double vida = 20;
    private Map<String, Double> danoDeCadaHabilidade = new HashMap<>();
    private double danoTotalCausado;
    private boolean isDefender;

    // Construtor que inicializa danoDeCadaHabilidade com valores padrão
    public PersonagemBase(String nome, String habilidade1, String habilidade2, String habilidade3, String habilidadeSpecial) {
        this.nome = nome;
        this.habilidade1 = habilidade1;
        this.habilidade2 = habilidade2;
        this.habilidade3 = habilidade3;
        this.habilidadeSpecial = habilidadeSpecial;

        Map<String, Double> skillsDefault = SkillsDefault.getSkillsDefault();
        Map<String, Double> skillsSpecial = SkillsSpecial.getSkillsSpecial();

        danoDeCadaHabilidade.put(habilidade1, skillsDefault.getOrDefault(habilidade1, 0.0));
        danoDeCadaHabilidade.put(habilidade2, skillsDefault.getOrDefault(habilidade2, 0.0));
        danoDeCadaHabilidade.put(habilidade3, skillsDefault.getOrDefault(habilidade3, 0.0));
        danoDeCadaHabilidade.put(habilidadeSpecial, skillsSpecial.getOrDefault(habilidadeSpecial, 0.0));

        this.danoTotalCausado = 0.0;
    }

    public String getInformations() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Habilidade 1: ").append(habilidade1).append(" (Dano: ").append(danoDeCadaHabilidade.get(habilidade1)).append(")\n");
        sb.append("Habilidade 2: ").append(habilidade2).append(" (Dano: ").append(danoDeCadaHabilidade.get(habilidade2)).append(")\n");
        sb.append("Habilidade 3: ").append(habilidade3).append(" (Dano: ").append(danoDeCadaHabilidade.get(habilidade3)).append(")\n");
        sb.append("Habilidade Special: ").append(habilidadeSpecial).append("\n");
        return sb.toString();
    }

    public void incrementarDanoCausado(String habilidade) {
        double dano = danoDeCadaHabilidade.getOrDefault(habilidade, 0.0);
        if (isDefender) {
            dano /= 2;
        }
        danoTotalCausado += dano;
    }

    public void zerarDanoCausado() {
        danoTotalCausado = 0.0;
    }

    public double getDanoTotalCausado() {
        return danoTotalCausado;
    }

    public void setDanoTotalCausado(double danoTotalCausado) {
        this.danoTotalCausado = danoTotalCausado;
    }

    public void adicionarDanoCausado(double dano) {
        if (isDefender) {
            dano /= 2;
        }
        this.danoTotalCausado += dano;
    }

    public void receberDano(double dano) {
        if (isDefender) {
            dano /= 2;
        }
        this.vida -= dano;
        System.out.println(nome + " recebeu " + dano + " de dano. Vida restante: " + vida);
        isDefender = false;
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

    public String getHabilidadeSpecial() {
        return habilidadeSpecial;
    }

    public void setHabilidadeSpecial(String habilidadeSpecial) {
        this.habilidadeSpecial = habilidadeSpecial;
    }

    public boolean isDefender() {
        return isDefender;
    }

    public void setDefender(boolean defender) {
        isDefender = defender;
    }
}
