package hexlet.code.source;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Differ  {

    public static Map<String, Object> readJson(Path path) throws IOException {
        Map<String, Object> result;
        ObjectMapper om = new ObjectMapper();
        result = om.readValue(path.toFile(), Map.class);
        return result;
    }


    public static String generate(Path filePath1, Path filePath2) throws FileNotFoundException {


        // Преобразование отнасительных путей в абсолютные
        var normalizedPath1 = FileManager.normaolizePath(filePath1);
        var normalizedPath2 = FileManager.normaolizePath(filePath2);

        // Проверка на существование файлов для чтения
        if (Files.notExists(normalizedPath1) || Files.notExists(normalizedPath2)) {
            throw new FileNotFoundException("Файл для чтения не найден");
        }
        // ПРочитать Json
        Map<String, Object> dataFile1;
        Map<String, Object> dataFile2;

        try {
            dataFile1 = readJson(normalizedPath1);
            dataFile2 = readJson(normalizedPath2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Сравнить данные


        Map<String, List<String>> data = new HashMap<>();

        // Получить данные из обекта1
        for (var key : dataFile1.keySet()) {
            if (!data.containsKey(key)) {
                List<String> dataValue = new ArrayList<>();
                data.put(key, dataValue);
            }
            var value = data.get(key);
            value.add(dataFile1.get(key).toString());
        }

        // Получить данные из обекта2
        for (var key : dataFile2.keySet()) {
            if (!data.containsKey(key)) {
                List<String> dataValue = new ArrayList<>();
                data.put(key, dataValue);
            }
            var value = data.get(key);
            value.add(dataFile2.get(key).toString());
        }


        // Сформировать результат
        StringBuilder result = new StringBuilder("{\n");

        var sortedKeySet = data.keySet().stream().sorted().toList(); // Сортировка набора ключей
        for (var key : sortedKeySet) {
            var value = data.get(key);
            String add = "";
            if (value.size() < 2) {
                String temp = key + ": " + value.get(0).toString();
                add = dataFile2.containsKey(key) ? "+ " + temp : "- " + temp;
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

    // Чтение Yaml из файла
    public static Map<String, Object> readYaml(Path file) {
        return null;
    }
    public static String generateYaml(Path filePath1, Path filePath2) {
        return "";
    }
}
