package hexlet.code.source;

import java.io.IOException;
import java.nio.file.Path;

public interface Differ {
    String generate(Path filePath1, Path filePath2) throws IOException;
}
