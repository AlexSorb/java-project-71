package hexlet.code.source;

import java.nio.file.Path;

// Класс описывает работу с дерикотриями и прочтением данных из файла
public class FileManager {

    public static Path normaolizePath(Path noNormPath) {

        if (noNormPath == null) {
            throw new IllegalArgumentException("The file path cannot be empty!");
        }

        var absolutPath = noNormPath.toAbsolutePath();
        var normalizedPath = absolutPath.normalize();

        return normalizedPath;
    }
}
