package ConfigurationActions;

import Personagem.Personagem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Actions implements ActionsInterface {
    private static final Logger LOGGER = Logger.getLogger(Actions.class.getName());

    private static final Map<String, Double> habilidadesDanos = new HashMap<>();
    private static final Map<String, String[]> habilidadeProPersonagem = new HashMap<>();

    static {
        habilidadeProPersonagem.put("arqueiro", new String[]{"flexa", "soco", "chute"});
        habilidadeProPersonagem.put("bruxa", new String[]{"feitiço", "ataque com a vassoura", "regeneração"});
    }

    static {
        habilidadesDanos.put("soco", 5.0);
        habilidadesDanos.put("chute", 5.0);
        habilidadesDanos.put("flexa", 10.0);
        habilidadesDanos.put("feitiço", 5.0);
        habilidadesDanos.put("ataque com a vassoura", 5.0);
        habilidadesDanos.put("regeneração", 0.0);
    }

    @Override
    public void attack(double damage, Personagem personagem) {
        String nome = personagem.getNome().toLowerCase();

        String[] habilidades = habilidadeProPersonagem.get(nome);
        if (habilidades != null) {
            for (String habilidade : habilidades) {
                if ("feitiço".equalsIgnoreCase(habilidade)) {
                    startFeitico(personagem);
                } else if ("regeneração".equalsIgnoreCase(habilidade)) {
                    // Lógica para regeneração (se existir implementação)
                    personagem.setHabilidadeDano("regeneração", 0.0); // Sem dano
                } else {
                    personagem.setHabilidadeDano(habilidade, habilidadesDanos.getOrDefault(habilidade, 0.0));
                }
            }
        } else {
            LOGGER.log(Level.WARNING, "Opção inválida para personagem");
        }
    }

    private void startFeitico(Personagem personagem) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        try {
            scheduler.scheduleAtFixedRate(() -> {
                double currentDamage = personagem.getHabilidadeDano("feitiço");
                if (currentDamage < 10.0) { // Durante 10 segundos
                    double newDamage = currentDamage + 1.0;
                    personagem.setHabilidadeDano("feitiço", newDamage);
                    System.out.println(newDamage);
                }
            }, 0, 1, TimeUnit.SECONDS);

            scheduler.schedule(() -> {
                LOGGER.log(Level.INFO, "Tempo de habilidade da " + personagem.getNome() + " excedeu 10 segundos. Encerrando habilidade.");

                scheduler.shutdown();
            }, 10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void defense(double damage, Personagem personagem) {
        // Implemente a lógica de defesa se necessário
    }

    @Override
    public void special(double damage, Personagem personagem) {
        // Implemente a lógica de habilidade especial se necessário
    }
}
