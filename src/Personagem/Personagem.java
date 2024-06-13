package Personagem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Personagem implements PersonagemInterface {
    private List<Integer> id;
    private String nome;
    private String habilidade1;
    private String habilidade2;
    private String habilidade3;
    private double dano;
    private double vida;
    private Map<String, Double> habilidadesDanos = new HashMap<>();

    public Personagem(String nome){
        this.nome = nome;
        inicializarHabilidades();
    }

    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("Nome do personagem: ").append(nome).append("\n");

        sb.append("Habilidade 1: ").append(getHabilidade1()).append(", Dano: ").append(getHabilidadeDano("flexa")).append("\n");
        sb.append("Habilidade 2: ").append(getHabilidade2()).append(", Dano: ").append(getHabilidadeDano("soco")).append("\n");
        sb.append("Habilidade 3: ").append(getHabilidade3()).append(", Dano: ").append(getHabilidadeDano("chute")).append("\n");
        sb.append("Dano total: ").append(getDanoTotal()).append("\n");
        return sb.toString();
    }

    public double getDanoTotal(){
        return habilidadesDanos.values().stream()
                .mapToDouble(Double::doubleValue).sum();
    }

    protected void inicializarHabilidades(){
        habilidadesDanos.put("soco", 5.0);
        habilidadesDanos.put("chute", 5.0);
        habilidadesDanos.put("flexa", 10.0);
        habilidadesDanos.put("feitiço", 5.0);
        habilidadesDanos.put("ataque com a vassoura", 5.0);
        habilidadesDanos.put("regeneração", 0.0);
    }

    @Override
    public double getHabilidadeDano(String habilidade) {
        return habilidadesDanos.getOrDefault(habilidade, 0.0);
    }

    @Override
    public void setHabilidadeDano(String habilidade, double dano) {
        habilidadesDanos.put(habilidade, dano);
    }

    @Override
    public double getDano() {
        return dano;
    }

    @Override
    public void setDano(double dano) {
        this.dano = dano;
    }

    @Override
    public List<Integer> getId() {
        return id;
    }

    @Override
    public void setId(List<Integer> id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getHabilidade1() {
        return habilidade1;
    }

    @Override
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
}
