package source;

import hexlet.code.source.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestJsonDiffer {

    static final String STYLISH_REPORT_PATH = "src/test/java/fixtures/Stylish";
    static final String PLAIN_REPORT_PATH = "src/test/java/fixtures/Plain";
    static final String JSON_REPORT_PATH = "src/test/java/fixtures/Json";

    static String differsStylish;
    static String differsPlain;
    static String differsJson;


    @BeforeAll
    public static void readFixtures() throws IOException {
        differsStylish = readReroptAsString(Path.of(STYLISH_REPORT_PATH));
        differsPlain = readReroptAsString(Path.of(PLAIN_REPORT_PATH));
        differsJson = readReroptAsString(Path.of(JSON_REPORT_PATH));

    }


    @Test
    public void testGenerateStylish() {
        var file1 = "src/test/java/resources/File1.json";
        var file2 = "src/test/java/resources/File2.json";
        var formant = "stylish";
        String result = "";
        try {
            result = Differ.generate(file1, file2, formant);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(differsStylish, result);
    }

    @Test
    public void testGeneratePlain() {
        var file1 = "src/test/java/resources/File1.json";
        var file2 = "src/test/java/resources/File2.json";
        var formant = "plain";
        String result = "";
        try {
            result = Differ.generate(file1, file2, formant);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(differsPlain, result, formant);
    }

    @Test
    public void testGenerateJson() {
        var file1 = "src/test/java/resources/File1.json";
        var file2 = "src/test/java/resources/File2.json";
        var formant = "json";
        String result = "";
        try {
            result = Differ.generate(file1, file2, formant);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(differsJson, result, formant);
    }

    @Test
    public void testWrongPathFile() {
        var file1 = "src/test/java/resources/File1.json";
        var wrongFile = "src/test/java/wrongFIle.json";
        var formant = "stylish";

        var thrownFirstArg = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate(wrongFile, file1, formant);
        });
        assertEquals("Файл для чтения не найден", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate(file1, wrongFile, formant);
        });
        assertEquals("Файл для чтения не найден", thrownSecondArg.getMessage());
    }


    @Test
    public void testGenerateWithoutFormat() {
        var file1 = "src/test/java/resources/File1.json";
        var file2 = "src/test/java/resources/File2.json";
        var formant = "stylish";
        String result = "";
        try {
            result = Differ.generate(file1, file2, formant);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(differsStylish, result);
    }


    public static String readReroptAsString(Path path) throws IOException {
        var normalizePath = path.normalize().toAbsolutePath();
        return Files.readString(normalizePath);
    }
}
