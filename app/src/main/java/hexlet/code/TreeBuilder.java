package hexlet.code;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;

public class TreeBuilder {
    public static final String ADDED = "ADDED";
    public static final String DELETED = "DELETED";
    public static final String CHANGED = "CHANGED";
    public static final String UNCHANGED = "UNCHANGED";

    public static Map<String, List<Object>> getTreeDifference(Map<String, Object> firstDataMap,
                                                              Map<String, Object> secondDataMap) {

        Map<String, List<Object>> result = new TreeMap<>();
        Set<String> keySet = new TreeSet<>(firstDataMap.keySet());
        keySet.addAll(secondDataMap.keySet());

        keySet.forEach(key -> {
            String stage;
            var dataFirstValue = firstDataMap.get(key) == null ? "null" : firstDataMap.get(key);
            var dataSecondValue = secondDataMap.get(key) == null ? "null" : secondDataMap.get(key);

            if (firstDataMap.containsKey(key) && secondDataMap.containsKey(key)) {

                stage = (dataFirstValue.equals(dataSecondValue)) ? UNCHANGED : CHANGED;

            } else {
                stage = secondDataMap.containsKey(key) ? ADDED : DELETED;
            }

            List<Object> date = new ArrayList<>();
            date.add(stage);
            date.add(dataFirstValue);
            date.add(dataSecondValue);

            result.put(key, date);
        });
        return result;
    }
}
