package hexlet.code.source.formatters;

import hexlet.code.source.Differ;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(Map<String, List<Object>> differenceMap) {
        StringBuilder result = new StringBuilder("{\n");
        differenceMap.forEach((key, value) -> {
            String addedString = "";

            if (value.size() != 3) {
                throw new IllegalArgumentException("Неподходящие данные");
            }

            if (value.getFirst().equals(Differ.DELETED) || value.getFirst().equals(Differ.CHANGED)) {
                addedString += "- " + key + ": " + value.get(1) + "\n";
            }

            if (value.getFirst().equals(Differ.ADDED) || value.getFirst().equals(Differ.CHANGED)) {
                addedString += "+ " + key + ": " + value.get(2) + "\n";
            }

            if (value.getFirst().equals(Differ.UNCHANGED)) {
                addedString += "  " + key + ": "  + value.get(1) + "\n";
            }

            result.append(addedString);
        });
        result.append("}");
        return result.toString();
    }
}
