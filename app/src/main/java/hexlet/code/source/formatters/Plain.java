package hexlet.code.source.formatters;

import hexlet.code.source.Differ;

import java.util.List;
import java.util.Map;


public class Plain {

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

    public static String plain(Map<String, List<Object>> differenceMap) {
        StringBuilder result = new StringBuilder();
        differenceMap.forEach((key, value) -> {
            String addString = "Property '" + key + "' was ";
            if (value.size() != 3) {
                throw new IllegalArgumentException("Неподходящие данные");
            }

            var currentState = value.getFirst();

            if (currentState.equals(Differ.ADDED)) {
                Object newValue = getPrintString(value.getLast());
                addString += "added with value: " + newValue + "\n";
            }

            if (currentState.equals(Differ.DELETED)) {
                addString += "removed" + "\n";
            }

            if (currentState.equals(Differ.CHANGED)) {
                Object oldValue = getPrintString(value.get(1));
                Object newValue = getPrintString(value.getLast());
                addString += "updated. From " + oldValue + " to " + newValue + "\n";
            }

            if (currentState.equals(Differ.UNCHANGED)) {
                addString = "";
            }

            result.append(addString);
        });
        return result.toString().trim();
    }
}
