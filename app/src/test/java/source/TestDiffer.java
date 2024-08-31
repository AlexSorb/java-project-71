package source;

import hexlet.code.source.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class TestDiffer {

    String differs = "{\n"
            + "- follow: false\n"
            + "  host: hexlet.io\n"
            + "- proxy: 123.234.53.22\n"
            + "- timeout: 50\n"
            + "+ timeout: 20\n"
            + "+ verbose: true\n"
            + "}";

    @Test
    public void testGenerate() {
        Path file1 = Path.of("src/test/java/resources/File1.json").toAbsolutePath();
        Path file2 = Path.of("src/test/java/resources/File2.json").toAbsolutePath();
        String result = "";
        try {
            result = Differ.generate(file1, file2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
        assertEquals(differs, result);
    }
}
