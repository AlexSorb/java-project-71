package hexlet.code.source;

import lombok.Getter;

import java.util.*;

@Getter
public class Diff {

    private final String ADD = "+";
    private final String DEL = "-";
    private final String BEF = "-";
    private final String AFT = "+";
    private final String UNC = " ";
    private final String CHN = "-+";


    private Map<String, List<Object>> change;
    private Map<String, String> differMap;
    private Set<String> fullKeys;
    private Set<String> deleteKeys;
    private Set<String> addKeys;
    private Set<String> changeKey;
    private Set<String> unChangeKey;


    Map<String, Object> firstData;
    Map<String, Object> secondData;
    Map<String, String> changeMap;


    public Diff(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {

        firstData = dataFile1;
        secondData = dataFile2;
        changeMap = new TreeMap<>();

        change = new TreeMap<>();
        differMap = new TreeMap<>();

        fullKeys = new TreeSet<>();
        fullKeys.addAll(dataFile1.keySet());
        fullKeys.addAll(dataFile2.keySet());

        deleteKeys = new TreeSet<>(fullKeys);
        deleteKeys.removeAll(dataFile2.keySet());

        addKeys = new TreeSet<>(fullKeys);
        addKeys.removeAll(dataFile1.keySet());

        changeKey = new TreeSet<>(fullKeys);
        changeKey.removeAll(deleteKeys);
        changeKey.removeAll(addKeys);

        unChangeKey = new TreeSet<>(changeKey);
        for (var key : unChangeKey) {
            var data1 = dataFile1.get(key) == null ? "null" : dataFile1.get(key);;
            var data2 = dataFile2.get(key) == null ? "null" : dataFile2.get(key);
            if (data1.toString().equals(data2.toString())) {
                changeKey.remove(key);
            }
        }
        unChangeKey.removeAll(changeKey);

        // Получить данные из обекта1
        for (var key : dataFile1.keySet()) {
            if (!change.containsKey(key)) {
                List<Object> dataValue = new ArrayList<>();
                change.put(key, dataValue);
            }
            var value = change.get(key);
            var addVal = dataFile1.get(key) == null ? "null" : dataFile1.get(key);
            value.add(addVal);
        }

        // Получить данные из обекта2
        for (var key : dataFile2.keySet()) {
            if (!change.containsKey(key)) {
                List<Object> dataValue = new ArrayList<>();
                change.put(key, dataValue);
            }
            var value = change.get(key);
            var addVal = dataFile2.get(key) == null ? "null" : dataFile2.get(key);
            value.add(addVal);
        }


        // New version
        fullKeys = new HashSet<>();
        fullKeys.addAll(dataFile1.keySet());
        fullKeys.addAll(dataFile2.keySet());

        deleteKeys = new TreeSet<>(fullKeys);
        deleteKeys.removeAll(dataFile2.keySet());
        deleteKeys.forEach(key ->{
            changeMap.put(key, DEL);
        });

        addKeys = new TreeSet<>(fullKeys);
        addKeys.removeAll(dataFile1.keySet());
        addKeys.forEach(key ->{
            changeMap.put(key, ADD);
        });

        changeKey = new TreeSet<>(fullKeys);
        changeKey.removeAll(deleteKeys);
        changeKey.removeAll(addKeys);
        changeKey.forEach(key ->{
            var firstFileValue = dataFile1.get(key);
            var secondFileValue = dataFile2.get(key);
            var state = firstFileValue.equals(secondFileValue) ? UNC : CHN;
            changeMap.put(key, state);
        });
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (var key : fullKeys) {
            if (deleteKeys.contains(key)) {
                result.append(DEL + " " + key + ": " + change.get(key).getFirst() + "\n");
            } else if (addKeys.contains(key)) {
                result.append(ADD + " " + key + ": " + change.get(key).getFirst() + "\n");
            } else if (changeKey.contains(key)) {
                result.append(BEF + " " + key + ": " + change.get(key).get(0) + "\n");
                result.append(AFT + " " + key + ": " + change.get(key).get(1) + "\n");
            } else {
                result.append(UNC + " " + key + ": " + change.get(key).getFirst() + "\n");
            }

        }
        return result.toString();
    }

    public String NewtoSting() {
        var result = new StringBuilder();
        changeMap.forEach((key, value) ->{
            String addStr = "";
            if (value.equals(DEL) || value.equals(CHN)) {
                addStr += DEL + " " + key + " " + firstData.get(key) + "\n";
            }
            if (value.equals(ADD) || value.equals(CHN)) {
                addStr += ADD + " " + key+ " " + secondData.get(key) + "\n";
            }
            result.append(addStr);
        });
        return result.toString();
    }
}
