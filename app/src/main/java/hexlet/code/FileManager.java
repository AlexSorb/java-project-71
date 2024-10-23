package hexlet.code;

import java.nio.file.Path;

public class FileManager {
    public static Path normalizePath(Path noNormPath) throws IllegalArgumentException {

        if (noNormPath == null) {
            throw new IllegalArgumentException("The file path cannot be empty!");
        }

        var absolutPath = noNormPath.toAbsolutePath();

        return absolutPath.normalize();
    }

    public static boolean isJsonFile(Path filePath) {
        var fileName = filePath.getFileName().toString();
        return fileName.endsWith(".json");
    }
}
