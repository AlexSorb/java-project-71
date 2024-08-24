package hexlet.code.source;

import java.nio.file.Path;
import java.nio.file.Paths;

// Класс описывает работу с дерикотриями и прочтением данных из файла
public class FileManager {

    public static Path normaolizePath(String noNormPath) {
        return Paths.get(noNormPath).normalize().toAbsolutePath();
    }

    public static String readData(Path file) {
        String data = "";
        return data;
    }
}
