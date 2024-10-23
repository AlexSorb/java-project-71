package hexlet.code.formatters;

import hexlet.code.TreeBuilder;

import java.util.List;
import java.util.Map;


public class Plain {

    private static String getPrintString(Object value) {

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

    public static String formatPlain(Map<String, List<Object>> differenceMap) {
        StringBuilder result = new StringBuilder();
        differenceMap.forEach((key, value) -> {
            String addString = "Property '" + key + "' was ";

            var currentState = value.getFirst();

            if (currentState.equals(TreeBuilder.ADDED)) {
                Object newValue = getPrintString(value.getLast());
                addString += "added with value: " + newValue + "\n";
            }

            if (currentState.equals(TreeBuilder.DELETED)) {
                addString += "removed" + "\n";
            }

            if (currentState.equals(TreeBuilder.CHANGED)) {
                Object oldValue = getPrintString(value.get(1));
                Object newValue = getPrintString(value.getLast());
                addString += "updated. From " + oldValue + " to " + newValue + "\n";
            }

            if (currentState.equals(TreeBuilder.UNCHANGED)) {
                addString = "";
            }

            result.append(addString);
        });
        return result.toString().trim();
    }
}
