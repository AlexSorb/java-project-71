package hexlet.code.source;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


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

        var jsonReport = stylish(data);
        return jsonReport;
    }


    public static String stylish(Diff diff) {
        StringBuilder result = new StringBuilder("{\n");
        result.append(diff.toString());
        result.append("}");
        return result.toString();
    }
}
