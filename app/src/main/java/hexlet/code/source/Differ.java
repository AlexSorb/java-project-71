package hexlet.code.source;

import hexlet.code.source.parsers.Parser;
import hexlet.code.source.parsers.ParserJson;
import hexlet.code.source.parsers.ParserYAML;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

        Map<String, Difference> result = new TreeMap<>();

        dataFile1.forEach((key, value) -> {
            var differ = new Difference();
            var addValue = value == null ? "null" : value;
            differ.setOldValue(addValue);
            result.put(key, differ);
        });

        dataFile2.forEach((key, value) -> {
            var differ = result.getOrDefault(key, new Difference());
            var addValue = value == null ? "null" : value;
            differ.setNewValue(addValue);
            result.put(key, differ);
        });

        result.values().forEach(value -> {
            var oldValue = value.getOldValue();
            var newValue = value.getNewValue();

            if (newValue == "null") {
                value.setState(DEL);
            }
            if (oldValue == "null") {
                value.setState(ADD);
            }

            if (oldValue != null && newValue != null && oldValue.equals(newValue)) {
                value.setState(UNC);
            } else {
                value.setState(CHN);
            }
        });

        var jsonReport = stylish(result);
        return jsonReport;
    }


    public static String stylish(Map<String, Difference> diff) {
        StringBuilder result = new StringBuilder("{\n");

        diff.forEach((key, value) -> {
            String addStr = "";
            if (value.getState().equals(DEL) || value.getState().equals(CHN)) {
                addStr += DEL + " " + key + ": " + value.getOldValue() + "\n";
            }
            if (value.getState().equals(ADD) || value.getState().equals(CHN)) {
                addStr += ADD + " " + key + ": " + value.getNewValue() + "\n";
            }
            if (value.getState().equals(UNC)) {
                addStr += UNC + " " + key + ": " + value.getOldValue() + "\n";
            }
            result.append(addStr);
        });
        result.append("}");
        return result.toString();
    }
}
