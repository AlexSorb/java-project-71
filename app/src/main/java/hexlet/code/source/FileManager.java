package hexlet.code.source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// Класс описывает работу с дерикотриями и прочтением данных из файла
public class FileManager {

    public static String readFile(String path) throws IOException {
        var pathToFile = Paths.get(path);
        String dataInFile ="";
        dataInFile = Files.readString(pathToFile);
        System.out.println(dataInFile);
        return dataInFile;
    }
    public static Path normaolizePath(String noNormPath) {
        return Paths.get(noNormPath).normalize().toAbsolutePath();
    }
}
