package source;

import hexlet.code.source.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

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

    @Test
    public void testWrongPathFile() {
        Path file1 = Path.of("src/test/java/resources/File1.json").toAbsolutePath();
        Path wrongFile = Path.of("src/test/java/wrongFIle.json").toAbsolutePath();


        var thrownFirstArg = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate(wrongFile, file1);
        });
        assertEquals("Файл для чтения не найден", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate(file1, wrongFile);
        });
        assertEquals("Файл для чтения не найден", thrownSecondArg.getMessage());
    }

    @Test
    public void testRead() throws IOException {
        Path jsonFilePath = Paths.get("src/test/java/resources/File1.json").toAbsolutePath();
        var readingJson = Differ.readJson(jsonFilePath);
        var rightAnswer =  Map.of(
                "host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false
        );
        assertEquals(readingJson, rightAnswer);
    }
}
