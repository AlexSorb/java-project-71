package hexlet.code.source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// Класс описывает работу с дерикотриями и прочтением данных из файла
public class FileManager {

    public static Path normaolizePath(Path noNormPath) {
        return noNormPath.normalize().toAbsolutePath();
    }

    public static String readData(Path file) {
        List<String> dataFile;
        try {
            dataFile = Files.readAllLines(FileManager.normaolizePath(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dataFile.toString();
    }
}
