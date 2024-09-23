package hexlet.code.source;

import lombok.Getter;

import java.util.Map;

//import java.util.*;

@Getter
public class Diff {

//    private final String ADD = "+";
//    private final String DEL = "-";
//    private final String UNC = " ";
//    private final String CHN = "-+";
//
//
//    private Map<String, Difference> fullDate;
//    private Set<String> fullKeys;
//    private Map<String, String> changeMap;
//    private Map<String, Object> firstData;
//    private Map<String, Object> secondData;
//
//


    public Diff(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {

//        fullKeys = new HashSet<>();
//        changeMap = new TreeMap<>();
//        fullDate = new TreeMap<>();
//        firstData = dataFile1;
//        secondData = dataFile2;
//
//        // Получить данные из обекта1
//        for (var key : dataFile1.keySet()) {
//            if (!fullDate.containsKey(key)) {
//                fullDate.put(key, new Difference());
//            }
//            var value = fullDate.get(key);
//            var addVal = dataFile1.get(key) == null ? "null" : dataFile1.get(key);
//            value.add(addVal);
//        }
//
//        // Получить данные из обекта2
//        for (var key : dataFile2.keySet()) {
//            if (!fullDate.containsKey(key)) {
//                List<Object> dataValue = new ArrayList<>();
//                fullDate.put(key, dataValue);
//            }
//            var value = fullDate.get(key);
//            var addVal = dataFile2.get(key) == null ? "null" : dataFile2.get(key);
//            value.add(addVal);
//        }
//
//
//        // New version
//
//
//
//
//        fullKeys.addAll(dataFile1.keySet());
//        fullKeys.addAll(dataFile2.keySet());
//
//        var deleteKeys = new TreeSet<>(fullKeys);
//        deleteKeys.removeAll(dataFile2.keySet());
//        deleteKeys.forEach(key ->{
//            changeMap.put(key, DEL);
//        });
//
//        var addKeys = new TreeSet<>(fullKeys);
//        addKeys.removeAll(dataFile1.keySet());
//        addKeys.forEach(key ->{
//            changeMap.put(key, ADD);
//        });
//
//        var changeKey = new TreeSet<>(fullKeys);
//        changeKey.removeAll(deleteKeys);
//        changeKey.removeAll(addKeys);
//        changeKey.forEach(key ->{
//            var firstFileValue = dataFile1.get(key) == null ? "null" : dataFile1.get(key);
//            var secondFileValue = dataFile2.get(key) == null ? "null" : dataFile2.get(key);
//            var state = firstFileValue.equals(secondFileValue) ? UNC : CHN;
//            changeMap.put(key, state);
//        });
//    }
//
//    public String toString() {
//        var result = new StringBuilder();
//        changeMap.forEach((key, value) ->{
//            String addStr = "";
//            if (value.equals(DEL) || value.equals(CHN)) {
//                addStr += DEL + " " + key + ": " + firstData.get(key) + "\n";
//            }
//            if (value.equals(ADD) || value.equals(CHN)) {
//                addStr += ADD + " " + key+ ": " + secondData.get(key) + "\n";
//            }
//            if (value.equals(UNC)) {
//                addStr += UNC + " " + key+ ": " + firstData.get(key) + "\n";
//            }
//            result.append(addStr);
//        });
//        return result.toString();
    }
}
