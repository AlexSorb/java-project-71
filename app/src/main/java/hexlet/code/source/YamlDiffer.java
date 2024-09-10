package hexlet.code.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamlDiffer implements Differ {

    public String generate(Path firstFilePath, Path secondFilePath2) throws IOException {

        // Преобразование относительного пути в абсолютный
        var firstNormalizedFilePath = FileManager.normaolizePath(firstFilePath);
        var secondNormalizedFilePath = FileManager.normaolizePath(secondFilePath2);

        // Проверка на существование файлов для чтения
        if (Files.notExists(firstNormalizedFilePath) || Files.notExists(secondNormalizedFilePath)) {
            throw new FileNotFoundException("Файл для чтения не найден");
        }

        var firstFileDataYaml = Parsers.parserYaml(firstNormalizedFilePath);
        var secondFileDataYaml = Parsers.parserYaml(secondNormalizedFilePath);

        Map<String, List<String>> fullData = new HashMap<>();

        // Получить данные из обекта1
        for (var key : firstFileDataYaml.keySet()) {
            if (!fullData.containsKey(key)) {
                List<String> dataValue = new ArrayList<>();
                fullData.put(key, dataValue);
            }
            var value = fullData.get(key);
            value.add(firstFileDataYaml.get(key).toString());
        }

        // Получить данные из обекта2
        for (var key : secondFileDataYaml.keySet()) {
            if (!fullData.containsKey(key)) {
                List<String> dataValue = new ArrayList<>();
                fullData.put(key, dataValue);
            }
            var value = fullData.get(key);
            value.add(secondFileDataYaml.get(key).toString());
        }

        // Сформировать результат
        StringBuilder result = new StringBuilder("{\n");

        var sortedKeySet = fullData.keySet().stream().sorted().toList(); // Сортировка набора ключей
        for (var key : sortedKeySet) {
            var value = fullData.get(key);

            String add = "";
            if (value.size() < 2) {
                add = key + ": " + value.get(0).toString();
                add = secondFileDataYaml.containsKey(key) ? "+ " + add : "- " + add;
            } else {
                String firstValue = value.get(0).toString();
                String lastValue = value.get(1).toString();
                add = firstValue.equals(lastValue) ? "  " + key + ": " + firstValue
                        : "- " + key + ": " + firstValue + "\n" + "+ " + key + ": " + lastValue;
            }

            result.append(add)
                    .append("\n");
        }

        result.append("}");
        return result.toString();
    }

}
