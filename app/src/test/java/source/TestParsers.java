package source;

import hexlet.code.source.Parsers;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParsers {

    @Test
    public void testJsonParser() throws IOException {
        Path jsonFilePath = Paths.get("src/test/java/resources/File1.json").toAbsolutePath();
        var readingJson = Parsers.parserJson(jsonFilePath);
        var rightAnswer =  Map.of(
                "host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false
        );
        assertEquals(readingJson, rightAnswer);
    }


    @Test
    public void testYamlParser() {
        Path yamlFile = Paths.get("src/test/java/resources/TestYamlFile1.yml").toAbsolutePath();
        String pars = "{host=hexlet.io, " + "timeout=50, " + "proxy=123.234.53.22, " + "follow=false}";

        try {
            assert Parsers.parserYaml(yamlFile) != null;
            assertEquals(pars, Parsers.parserYaml(yamlFile).toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
