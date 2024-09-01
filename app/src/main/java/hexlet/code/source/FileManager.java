package hexlet.code.source;

import java.nio.file.Path;

// Класс описывает работу с дерикотриями и прочтением данных из файла
public class FileManager {

    public static Path normaolizePath(Path noNormPath) {

        var absolutPath = noNormPath.toAbsolutePath();
        var normalizedPath = absolutPath.normalize();

        return normalizedPath;
    }
}
