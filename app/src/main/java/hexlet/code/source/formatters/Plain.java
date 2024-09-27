package hexlet.code.source.formatters;

import hexlet.code.source.Differ;
import hexlet.code.source.Difference;

import java.util.List;

public class Plain {
    public static String plain(List<Difference> differences) {
        StringBuilder result = new StringBuilder();
        differences.forEach(value -> {
            String addString = "";
            var currentState = value.getState();

            if (currentState.equals(Differ.ADD)) {
                addString = "Property '" + value.getKey() + "' was ";
                addString += "added with value: " + value.getNewValue() + "\n";
            }

            if (currentState.equals(Differ.DEL)) {
                addString = "Property '" + value.getKey() + "' was ";
                addString += "removed\n";
            }

            if (currentState.equals(Differ.CHN)) {
                addString = "Property '" + value.getKey() + "' was ";
                addString += "updated. From " + value.getOldValue() + " to " + value.getNewValue() + "\n";
            }

            result.append(addString);
        });
        return result.toString();
    }
}
