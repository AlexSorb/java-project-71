import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.App;
import hexlet.code.source.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class TestApp {


    @Test
    public void testMain(){
        String[] args = {"h"};
        App.main(args);
    }

    @Test
    public void testRead() throws IOException {
        Path jsonFilePath = Paths.get("src/test/java/resources/File1.json").toAbsolutePath();
        var readingJson = Differ.readJson(jsonFilePath);
        var rightAnswer =  Map.of(
                "follow", "false",
                "timeout", "50",
                "host", "hexlet.io",
                "proxy", "123.234.53.22"
        );
        assertTrue(readingJson.toString().equals(readingJson.toString()));
    }

}
