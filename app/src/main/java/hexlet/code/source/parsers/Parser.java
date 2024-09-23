package hexlet.code.source.parsers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public interface Parser {
    Map<String, Object> parsFile(Path path) throws IOException;
}
