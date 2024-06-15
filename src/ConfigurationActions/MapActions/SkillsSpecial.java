package ConfigurationActions.MapActions;

import java.util.HashMap;
import java.util.Map;

public class SkillsSpecial {
    private static final Map<String, Double> skillsSpecial = new HashMap<>();
    
    private static final String SPECIAL_ARQUEIRO = "flexa_tripla".toLowerCase();
    private static final String SPECIAL_BRUXA = "veneno".toLowerCase();

    static {
        skillsSpecial.put(SPECIAL_ARQUEIRO, 24.0);
        skillsSpecial.put(SPECIAL_BRUXA, 20.0);
    }
    public static Map<String, Double> getSkillsSpecial() {
        return skillsSpecial;
    }

}
