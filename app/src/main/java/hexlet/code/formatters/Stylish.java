package hexlet.code.formatters;

import hexlet.code.TreeBuilder;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String stylish(Map<String, List<Object>> differenceMap) {
        StringBuilder result = new StringBuilder("{\n");
        differenceMap.forEach((key, value) -> {
            String addedString = "";

            if (value.getFirst().equals(TreeBuilder.DELETED) || value.getFirst().equals(TreeBuilder.CHANGED)) {
                addedString += "  - " + key + ": " + value.get(1) + "\n";
            }

            if (value.getFirst().equals(TreeBuilder.ADDED) || value.getFirst().equals(TreeBuilder.CHANGED)) {
                addedString += "  + " + key + ": " + value.get(2) + "\n";
            }

            if (value.getFirst().equals(TreeBuilder.UNCHANGED)) {
                addedString += "    " + key + ": "  + value.get(1) + "\n";
            }

            result.append(addedString);
        });
        result.append("}");
        return result.toString();
    }
}
