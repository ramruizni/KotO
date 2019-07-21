package names;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NameGenerator {

    private Map<String, String> map = new HashMap<>();
    private static NameGenerator shared;

    private NameGenerator() {
        // hiding default
    }

    public static NameGenerator getInstance() {
        if (shared == null) {
            shared = new NameGenerator();
        }
        return shared;
    }

    private String generateNewId() {
        char[] alphabet = Constants.ALPHABET;
        char[] digits = Constants.DIGITS;
        Random r = new Random();

        StringBuilder id = new StringBuilder();
        id.append("_");

        for (int i = 0; i < 2; i++) {
            id.append(digits[r.nextInt(digits.length)]);
            id.append(alphabet[r.nextInt(alphabet.length)]);
        }
        return id.toString();
    }

    public String getNewName(String name) {
        // this special case could be improved
        if (name.equals("main")) return name;
        if (!map.containsKey(name)) {
            map.put(name, generateNewId());
        }
        return map.get(name);
    }

    public Map<String, String> getMap() {
        return map;
    }
}
