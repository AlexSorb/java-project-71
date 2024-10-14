package hexlet.code.source;

import hexlet.code.Formatter;
import hexlet.code.source.parsers.Parser;
import hexlet.code.source.parsers.ParserJson;
import hexlet.code.source.parsers.ParserYAML;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.ArrayList;


public class Differ {

    public static final String ADDED = "ADDED";
    public static final String DELETED = "DELETED";
    public static final String CHANGED = "CHANGED";
    public static final String UNCHANGED = "UNCHANGED";

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        var normalizedPath1 = FileManager.normaolizePath(Path.of(filePath1));
        var normalizedPath2 = FileManager.normaolizePath(Path.of(filePath2));


        if (Files.notExists(normalizedPath1) || Files.notExists(normalizedPath2)) {
            throw new FileNotFoundException("Файл для чтения не найден");
        }

        Parser parser = (FileManager.isJsonFile(normalizedPath1)) ? new ParserJson() : new ParserYAML();
        var dataFirst = parser.parsFile(normalizedPath1);
        var dataSecond = parser.parsFile(normalizedPath2);



        Map<String, List<Object>> differenceMap = new TreeMap<>();
        Set<String> keySet = new TreeSet<>(dataFirst.keySet());
        keySet.addAll(dataSecond.keySet());

        keySet.forEach(key -> {
            String stage = "";
            var dataFirstValue = dataFirst.get(key) == null ? "null" : dataFirst.get(key);
            var dataSecondValue = dataSecond.get(key) == null ? "null" : dataSecond.get(key);

            if (dataFirst.containsKey(key) && dataSecond.containsKey(key)) {

                stage = (dataFirstValue.equals(dataSecondValue)) ? UNCHANGED : CHANGED;

            } else {
                stage = dataSecond.containsKey(key) ? ADDED : DELETED;
            }

            List<Object> date = new ArrayList<>(3);
            date.add(stage);
            date.add(dataFirstValue);
            date.add(dataSecondValue);

            differenceMap.put(key, date);
        });

        return Formatter.getOrder(format, differenceMap);
    }

}
