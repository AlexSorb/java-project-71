package hexlet.code.source.formatters;

import hexlet.code.source.Differ;
import hexlet.code.source.Difference;

import java.util.List;

public class Stylish {
    public static String stylish(List<Difference> differences) {
        StringBuilder result = new StringBuilder("{\n");
        differences.forEach(value -> {
            var currentState = value.getState();
            String addString = "";

            if (currentState.equals(Differ.DEL) || currentState.equals(Differ.CHN)) {
                addString += "- " + value.getKey() + ": " + value.getOldValue() + "\n";
            }

            if (currentState.equals(Differ.ADD) || currentState.equals(Differ.CHN)) {
                addString += "+ " + value.getKey() + ": " + value.getNewValue() + "\n";
            }

            if (currentState.equals(Differ.UNC)) {
                addString += "  " + value.getKey() + ": " + value.getOldValue() + "\n";
            }
            result.append(addString);
        });
        result.append("}");
        return result.toString();
    }
}
