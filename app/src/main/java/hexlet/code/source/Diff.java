package hexlet.code.source;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public Diff(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        change = new HashMap<>();
        differMap = new HashMap<>();


        // Получить данные из обекта1
        for (var key : dataFile1.keySet()) {
            if (!change.containsKey(key)) {
                List<Object> dataValue = new ArrayList<>();
                change.put(key, dataValue);
            }
            var value = change.get(key);
            value.add(dataFile1.get(key));
            differMap.put(key, UNC);
        }

        // Получить данные из обекта2
        for (var key : dataFile2.keySet()) {
            if (!change.containsKey(key)) {
                List<Object> dataValue = new ArrayList<>();
                change.put(key, dataValue);
                differMap.put(key, ADD);
            }
            var value = change.get(key);
            value.add(dataFile2.get(key));
            if (differMap.containsKey(key)) {
                differMap.put(key, CHN);
            }
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (var key : change.keySet()) {
            String addString = "";
            if (differMap.get(key).equals(CHN)) {
                int size = change.get(key).size();
                for (int i = 0; i < size - 1; i++) {
                    addString += BEF + " " + key+ ": " + change.get(key).get(i) + "\n";
                }
                addString += BEF + " " + key+ ": " + change.get(key).get(size - 1) + "\n";
            } else {
                addString += differMap.get(key) + " "+ key+ ": " + change.get(key) + "\n";
            }
            result.append(addString);
        }

        return result.toString();
    }
}
