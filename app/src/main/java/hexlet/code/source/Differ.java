package hexlet.code.source;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.ArrayList;
//import java.util.*;

public class Differ {

    public static Map<String, Object> readJson(Path pathToFile) throws IOException {
        Map<String, Object> result;
        ObjectMapper objectMapper = new ObjectMapper();
        result = objectMapper.readValue(new File(pathToFile.toString()), Map.class);
        return result;
    }


    public static String generate(Path filePath1, Path filePath2) throws FileNotFoundException {


        // Преобразование отнасительных путей в абсолютные
        var normalizedPath1 = filePath1.isAbsolute() ? filePath1 : filePath1.toAbsolutePath();
        var normalizedPath2 = filePath2.isAbsolute() ? filePath2 : filePath2.toAbsolutePath();

        // Проверка на существование файлов для чтения
        if (Files.notExists(normalizedPath1) || Files.notExists(normalizedPath2)) {
            throw new FileNotFoundException("Файл для чтения не найден");
        }

//        // Чтение данных из файлов
//        List<String> dataFile1;
//        List<String> dataFile2;
//        try {
//            dataFile1 = Files.readAllLines(normalizedPath1);
//            dataFile2 = Files.readAllLines(normalizedPath2);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // ПРочитать Json
        var om = new ObjectMapper();

        Map<String, Object> dataFile1;
        Map<String, Object> dataFile2;

        try {
            dataFile1 = om.readValue(normalizedPath1.toFile(), Map.class);
            dataFile2 = om.readValue(normalizedPath2.toFile(), Map.class);
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

}
