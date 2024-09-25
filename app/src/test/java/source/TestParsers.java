package source;

import hexlet.code.source.parsers.Parser;
import hexlet.code.source.parsers.ParserJson;
import hexlet.code.source.parsers.ParserYAML;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestParsers {

    @Test
    public void testJsonParser() throws IOException {
        Path jsonFilePath = Paths.get("src/test/java/resources/File1.json").toAbsolutePath();
        Parser readingJson = new ParserJson();
        var data = readingJson.parsFile(jsonFilePath).toString();
        var rightAnswer = "{setting1=Some value, setting2=200, setting3=true, key1=value1, numbers1=[1, 2, 3, 4], "
                + "numbers2=[2, 3, 4, 5], id=45, default=null, checked=false, numbers3=[3, 4, 5], chars1=[a, b, c], "
                + "chars2=[d, e, f]}";
        assertEquals(rightAnswer, data);
    }


    @Test
    public void testYamlParser() throws IOException {
        Path yamlFile = Paths.get("src/test/java/resources/TestYamlFile1.yml").toAbsolutePath();
        String pars = "{setting1=Some value, setting2=200, setting3=true, key1=value1, numbers1=[1, 2, 3, 4], "
                + "numbers2=[2, 3, 4, 5], id=45, default=null, checked=false, numbers3=[3, 4, 5], chars1=[a, b, c], "
                + "chars2=[d, e, f]}";
        Parser readingJson = new ParserYAML();
        var data = readingJson.parsFile(yamlFile).toString();
        assertEquals(pars, data);

    }
}
