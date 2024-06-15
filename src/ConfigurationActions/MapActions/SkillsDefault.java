package ConfigurationActions.MapActions;

import java.util.HashMap;
import java.util.Map;

public class SkillsDefault {
    private static final Map<String, Double> skillsDefault = new HashMap<>();

    private static final String SOCO = "soco".toLowerCase();
    private static final String CHUTE = "chute".toLowerCase();
    private static final String CABECADA = "cabecada".toLowerCase();
    private static final String FLEXA = "flexa".toLowerCase();
    private static final String MAGIC = "pocao".toLowerCase();

    static {
        skillsDefault.put(SOCO, 5.0);
        skillsDefault.put(CHUTE, 5.0);
        skillsDefault.put(CABECADA, 7.0);
        skillsDefault.put(FLEXA, 8.0);
        skillsDefault.put(MAGIC, 4.0);
    }

    public static Map<String, Double> getSkillsDefault() {
        return skillsDefault;
    }
}

