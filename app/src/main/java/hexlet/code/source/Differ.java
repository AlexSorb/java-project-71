package hexlet.code.source;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
//import java.util.*;

public class Differ {

    public static Map<String, Object> readJson(Path pathToFile) throws IOException {
        Map<String, Object> result;
        ObjectMapper objectMapper = new ObjectMapper();
        result = objectMapper.readValue(new File(pathToFile.toString()), Map.class);
        return result;
    }


    /*
    * Функция принимате результаты парсинга двух файлов. Результаты парсинга представлены ввиде двух Map с парами ключ
    * значение.
    *
    *  Реализайия 1
    *
    *   Оформляем Строку с названием "result" для хранения результат
    *   Инициализируем объект для хранения ключей всех объектов, чтобы не пропустить объекты добавленные только во
    *   вторую мапу
    *
     *   Для каждого из ключей провереям присутсвует ли он в первой мапе и во втрой мапе
    *           инциализируем переменную containingInBothMaps значением, присутвует ли ключь в обеих мапах
    *           Иницализипровать переменную stack в которой будут храниться список изменеий
    *
    *           Если containingInBothMaps - true данный ключь есть в обеих мапах
    *                 значения разные добавить в stack значение -+
    *                 значения одинаковы добавить в stack значение " "
     *          Если containingInBothMaps - false данный ключь присутсвут только в одноий из мап
     *                  если ключ присутствует в первой мапе - в stack добовляем "-" и ключ
     *                  иначе - в stack добовляем "+" и ключ
     *         затем
     *          для каждого элемента в stack
     *          Если встречаем "-"  добовляем в result "- " + ключ + значение из первой мапы
     *          Если втречаем  "+" добовляем в result "+ " + ключ + значение из втрогй мапы
     *  после обхода всех ключей добовляем }
     *
     * Выдает результат вывода ввиде строки.
    */


    public static String generate(Map<String, Object> first, Map<String, Object> second) throws RuntimeException {

        StringBuilder result = new StringBuilder("{\n");
        Set<String> setKey = new TreeSet<>(first.keySet());
        setKey.addAll(second.keySet());

        for (var key : setKey) {
            var containingInBothMaps = first.containsKey(key) && second.containsKey(key);
            String stack = "";

            if (containingInBothMaps) {
                stack = first.get(key).equals(second.get(key)) ? " " + stack : "-+" + stack;
            } else {
                stack = first.containsKey(key) ? "-" + stack : "+" + stack;
            }

            for (var cheng : stack.toCharArray()) {
                switch (cheng) {
                    case ' ':
                        result.append("  ").append(key).append(": ").append(first.get(key).toString()).append("\n");
                        break;
                    case '+':
                        result.append("+ ").append(key).append(": ").append(second.get(key).toString()).append("\n");
                        break;
                    case '-':
                        result.append("- ");
                        result.append(key);
                        result.append(": ");
                        result.append(first.get(key).toString());
                        result.append("\n");
                        break;
                    default:
                        result.append("Error!");
                }
            }
        }
        result.append("}");
        return result.toString();
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


        Map<String, List<Object>> data = new HashMap<>();

        // Получить данные из обекта1
        for (var key : dataFile1.keySet()) {
            if (!data.containsKey(key)) {
                List<Object> dataValue = new ArrayList<>();
                data.put(key, dataValue);
            }
            var value = data.get(key);
            value.add(dataFile1.get(key));
        }

        // Получить данные из обекта2
        for (var key : dataFile2.keySet()) {
            if (!data.containsKey(key)) {
                List<Object> dataValue = new ArrayList<>();
                data.put(key, dataValue);
            }
            var value = data.get(key);
            value.add(dataFile1.get(key));
        }



        // Сформировать результат
        StringBuilder result = new StringBuilder("{\n");

        for (var key : data.keySet()) {
            var value = data.get(key);
            String add = "";
            if (value.size() <= 2) {
                String temp = key + " " + value;
                add = dataFile2.containsKey(key) ? "+ " + temp : "- " + temp;
            } else {
                boolean isEquals = true;
                for (int i = 0; i < value.size(); i++) {
                    if (i == 0) {
                        continue;
                    }
                    var lastValue = value.get(i - 1);
                    var currentValue = value.get(i);
                    if (!currentValue.equals(lastValue)) {
                        isEquals = false;
                        break;
                    }
                }


                if (isEquals) {
                    add = "  " + key + " " + value.get(1);
                } else {
                    for (int i = 0; i < value.size() - 1; i++) {
                        add += "- " + "  " + key + " " + value.get(i) + "\n";
                    }
                    add += "+ " + "  " + key + " " + value.get(value.size() - 1) + "\n";
                }
            }

            result.append(add)
                    .append("\n");
        }

        result.append("}");
        return result.toString();
    }

}
