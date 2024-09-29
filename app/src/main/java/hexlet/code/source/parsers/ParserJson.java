package hexlet.code.source.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class ParserJson implements Parser {

    public Map<String, Object> parsFile(Path path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path.toFile(), Map.class);
    }
}
