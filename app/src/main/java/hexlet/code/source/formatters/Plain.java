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
                var newValue = getPrintString(value.getNewValue());
                addString += "added with value: " + newValue + "\n";
            }

            if (currentState.equals(Differ.DEL)) {
                addString = "Property '" + value.getKey() + "' was ";
                addString += "removed" + "\n";
            }

            if (currentState.equals(Differ.CHN)) {
                addString = "Property '" + value.getKey() + "' was ";
                var newValue = getPrintString(value.getNewValue());
                var oldValue = getPrintString(value.getOldValue());
                addString += "updated. From " + oldValue + " to " + newValue + "\n";
            }

            result.append(addString);
        });
        return result.toString().trim();
    }

    public static String getPrintString(Object value) {

        String result = value.toString();

        if (value instanceof String) {
            result = "'" + value + "'";
        }

        if (value.equals("null")) {
            result = "null";
        }

        if (value.toString().startsWith("{") || value.toString().startsWith("[")) {
            result = "[complex value]";
        }
        return result;
    }
}
