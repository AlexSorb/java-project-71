package hexlet.code.source;

import hexlet.code.Formatter;
import hexlet.code.source.parsers.Parser;
import hexlet.code.source.parsers.ParserJson;
import hexlet.code.source.parsers.ParserYAML;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


public class Differ {

    public static final String ADD = "ADD";
    public static final String DEL = "DEL";
    public static final String UNC = "UNC";
    public static final String CHN = "CHN";

    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        var normalizedPath1 = FileManager.normaolizePath(Path.of(filePath1));
        var normalizedPath2 = FileManager.normaolizePath(Path.of(filePath2));


        if (Files.notExists(normalizedPath1) || Files.notExists(normalizedPath2)) {
            throw new FileNotFoundException("Файл для чтения не найден");
        }

        Parser parser = (FileManager.isJsonFile(normalizedPath1)) ? new ParserJson() : new ParserYAML();


        var dataFile1 = parser.parsFile(normalizedPath1);
        var dataFile2 = parser.parsFile(normalizedPath2);

        Map<String, Difference> differenceTreeMap = new TreeMap<>();

        dataFile1.forEach((key, value) -> {
            var currentDifference = new Difference();
            currentDifference.setKey(key);
            var addValue = value == null ? "null" : value;
            currentDifference.setOldValue(addValue);
            currentDifference.setState(DEL);
            differenceTreeMap.put(key, currentDifference);
        });

        dataFile2.forEach((key, value) -> {
            var currentDifference = differenceTreeMap.getOrDefault(key, new Difference());
            var addValue = value == null ? "null" : value;
            currentDifference.setKey(key);
            currentDifference.setNewValue(addValue);

            var addStage = ADD;
            if (currentDifference.getOldValue() != null && currentDifference.getNewValue() != null) {
                addStage = (currentDifference.getOldValue().equals(currentDifference.getNewValue()))
                        ? UNC : CHN;
            }
            currentDifference.setState(addStage);
            differenceTreeMap.put(key, currentDifference);
        });


        var listDifference = new ArrayList<>(differenceTreeMap.values());
        return Formatter.getOrder(format, listDifference);
    }

}
