package hexlet.code.source;

import hexlet.code.source.parsers.Parser;
import hexlet.code.source.parsers.ParserJson;
import hexlet.code.source.parsers.ParserYAML;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class Differ {

    private static final String ADD = "+";
    private static final String DEL = "-";
    private static final String UNC = " ";
    private static final String CHN = "-+";

    public static String generate(String filePath1, String filePath2) throws IOException {


        // Преобразование отнасительных путей в абсолютные
        var normalizedPath1 = FileManager.normaolizePath(Path.of(filePath1));
        var normalizedPath2 = FileManager.normaolizePath(Path.of(filePath2));

        // Проверка на существование файлов для чтения
        if (Files.notExists(normalizedPath1) || Files.notExists(normalizedPath2)) {
            throw new FileNotFoundException("Файл для чтения не найден");
        }
        // Выбрать парсер
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
        var jsonReport = stylish(listDifference);
        return jsonReport;
    }

    public static String stylish(List<Difference> differences) {
        StringBuilder result = new StringBuilder("{\n");
        differences.forEach(value -> {
            var currentState = value.getState();
            String addString = "";

            if (currentState.equals(DEL) || currentState.equals(CHN)) {
                addString += DEL + " " + value.getKey() + ": " + value.getOldValue() + "\n";
            }

            if (currentState.equals(ADD) || currentState.equals(CHN)) {
                addString += ADD + " " + value.getKey() + ": " + value.getNewValue() + "\n";
            }

            if (currentState.equals(UNC)) {
                addString += UNC + " " + value.getKey() + ": " + value.getOldValue() + "\n";
            }
            result.append(addString);
        });
        result.append("}");
        return result.toString();
    }
}
