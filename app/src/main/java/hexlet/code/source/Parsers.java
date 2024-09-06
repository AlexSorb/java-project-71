package hexlet.code.source;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Parsers {

    public static Map<String, Object> parserJson(Path path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var jsonMap = mapper.readValue(path.toFile(), Map.class);
        return jsonMap;
    }

    public static Map<String, Object> parserYaml(Path file) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        var yamlMap = mapper.readValue(file.toFile(), Map.class);
        return yamlMap;
    }
}
