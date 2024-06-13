package ConfigurationActions;

import Personagem.Personagem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementação das ações que um personagem pode executar, como ataque, defesa e habilidades especiais.
 */
public class Actions implements ActionsInterface {
    private static final Logger LOGGER = Logger.getLogger(Actions.class.getName());

    // Mapeamento de habilidades por personagem
    private static final Map<String, String[]> habilidadeProPersonagem = new HashMap<>();

    // Mapeamento de danos das habilidades
    private static final Map<String, Double> habilidadesDanos = new HashMap<>();

    static {
        // Inicialização do mapeamento de habilidades por personagem
        habilidadeProPersonagem.put("arqueiro", new String[]{"flexa", "soco", "chute"});
        habilidadeProPersonagem.put("bruxa", new String[]{"feitiço", "ataque com a vassoura", "regeneração"});
    }

    static {
        // Inicialização do mapeamento de danos das habilidades
        habilidadesDanos.put("soco", 5.0);
        habilidadesDanos.put("chute", 5.0);
        habilidadesDanos.put("flexa", 10.0);
        habilidadesDanos.put("feitiço", 5.0);
        habilidadesDanos.put("ataque com a vassoura", 5.0);
        habilidadesDanos.put("regeneração", 0.0);
    }

    /**
     * Método para realizar um ataque com base nas habilidades do personagem.
     *
     * @param damage    Dano do ataque
     * @param personagem Personagem que realiza o ataque
     */
    @Override
    public void attack(double damage, Personagem personagem) {
        String nome = personagem.getNome().toLowerCase();

        // Obtém as habilidades do personagem
        String[] habilidades = habilidadeProPersonagem.get(nome);
        if (habilidades != null) {
            for (String habilidade : habilidades) {
                // Verifica qual habilidade está sendo executada
                if ("feitiço".equalsIgnoreCase(habilidade)) {
                    startFeitico(personagem); // Inicia o feitiço
                } else if ("regeneração".equalsIgnoreCase(habilidade)) {
                    // Lógica para regeneração (se existir implementação)
                    personagem.setHabilidadeDano("regeneração", 0.0); // Sem dano
                } else {
                    // Define o dano da habilidade específica
                    personagem.setHabilidadeDano(habilidade, habilidadesDanos.getOrDefault(habilidade, 0.0));
                }
            }
        } else {
            LOGGER.log(Level.WARNING, "Opção inválida para personagem");
        }
    }

    /**
     * Método privado para iniciar o feitiço de uma bruxa, que aumenta o dano ao longo do tempo.
     *
     * @param personagem Bruxa que está iniciando o feitiço
     */
    private void startFeitico(Personagem personagem) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        try {
            // Aumenta o dano do feitiço a cada segundo até atingir 10 segundos
            scheduler.scheduleAtFixedRate(() -> {
                double currentDamage = personagem.getHabilidadeDano("feitiço");
                if (currentDamage < 10.0) { // Durante 10 segundos
                    double newDamage = currentDamage + 1.0;
                    personagem.setHabilidadeDano("feitiço", newDamage);
                    System.out.println(newDamage); // Exibe o novo dano (para fins de depuração)
                }
            }, 0, 1, TimeUnit.SECONDS);

            // Encerra o feitiço após 10 segundos
            scheduler.schedule(() -> {
                LOGGER.log(Level.INFO, "Tempo de habilidade da " + personagem.getNome() + " excedeu 10 segundos. Encerrando habilidade.");

                scheduler.shutdown();
            }, 10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Implementação da lógica de defesa do personagem.
     *
     * @param damage    Dano recebido
     * @param personagem Personagem que executa a defesa
     */
    @Override
    public void defense(double damage, Personagem personagem) {

    }

    /**
     * Implementação da lógica da habilidade especial do personagem.
     *
     * @param damage    Dano da habilidade especial
     * @param personagem Personagem que executa a habilidade especial
     */
    @Override
    public void special(double damage, Personagem personagem) {

    }
}
