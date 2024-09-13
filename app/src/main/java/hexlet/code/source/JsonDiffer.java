package hexlet.code.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;



public class JsonDiffer implements Differ {

    public String generate(Path filePath1, Path filePath2) throws IOException {


        // Преобразование отнасительных путей в абсолютные
        var normalizedPath1 = FileManager.normaolizePath(filePath1);
        var normalizedPath2 = FileManager.normaolizePath(filePath2);

        // Проверка на существование файлов для чтения
        if (Files.notExists(normalizedPath1) || Files.notExists(normalizedPath2)) {
            throw new FileNotFoundException("Файл для чтения не найден");
        }
        // ПРочитать Json
        var dataFile1 = Parsers.parserJson(normalizedPath1);
        var dataFile2 = Parsers.parserJson(normalizedPath2);

        // Сравнить данные
        var data = new Diff(dataFile1, dataFile2);

        // Сформировать результат
        StringBuilder result = new StringBuilder("{\n");

        var jsonReport = generateJsonReport(data);
        return jsonReport;
    }

    private static Map<String, List<String>> mergeData(Map<String, Object> dataFile1, Map<String, Object> dataFile2) {
        Map<String, List<String>> result = new HashMap<>();

        // Получить данные из обекта1
        for (var key : dataFile1.keySet()) {
            if (!result.containsKey(key)) {
                List<String> dataValue = new ArrayList<>();
                result.put(key, dataValue);
            }
            var value = result.get(key);
            value.add(dataFile1.get(key).toString());
        }

        // Получить данные из обекта2
        for (var key : dataFile2.keySet()) {
            if (!result.containsKey(key)) {
                List<String> dataValue = new ArrayList<>();
                result.put(key, dataValue);
            }
            var value = result.get(key);
            value.add(dataFile2.get(key).toString());
        }

        return result;
    }

    public String generateJsonReport(Diff diff) {
        StringBuilder result = new StringBuilder("{\n");
        result.append(diff.toString());
        result.append("}");
        return result.toString();
    }
}
