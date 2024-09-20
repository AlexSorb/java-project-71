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
        var rightAnswer = "{setting1=Some value, setting2=200, setting3=true, key1=value1, numbers1=[1, 2, 3, 4], numbers2=[2, 3, 4, 5], id=45, default=null, checked=false, numbers3=[3, 4, 5], chars1=[a, b, c], chars2=[d, e, f]}";
        assertEquals(rightAnswer, readingJson.toString());
    }


    @Test
    public void testYamlParser() {
        Path yamlFile = Paths.get("src/test/java/resources/TestYamlFile1.yml").toAbsolutePath();
        String pars = "{setting1=Some value, setting2=200, setting3=true, key1=value1, numbers1=[1, 2, 3, 4], numbers2=[2, 3, 4, 5], id=45, default=null, checked=false, numbers3=[3, 4, 5], chars1=[a, b, c], chars2=[d, e, f]}";
        try {
            assert Parsers.parserYaml(yamlFile) != null;
            assertEquals(pars, Parsers.parserYaml(yamlFile).toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
