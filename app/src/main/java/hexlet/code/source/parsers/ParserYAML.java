package hexlet.code.source.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class ParserYAML implements Parser {
    public Map<String, Object> parsFile(Path file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(file.toFile(), Map.class);
    }
}
