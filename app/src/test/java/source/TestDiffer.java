package source;

import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestDiffer {

    static final String STYLISH_REPORT_PATH = "src/test/java/resources/fixtures/Stylish";
    static final String PLAIN_REPORT_PATH = "src/test/java/resources/fixtures/Plain";
    static final String JSON_REPORT_PATH = "src/test/java/resources/fixtures/Json";


    private static String differsStylish;
    private static String differsPlain;
    private static String differsJson;

    private final String firstTestJsonFilePath = "src/test/java/resources/File1.json";
    private final String secondTestJsonFilePath = "src/test/java/resources/File2.json";
    private final String wrongFile = "src/test/java/wrongFIle.json";

    private final String firstTestYamlFilePath = "src/test/java/resources/TestYamlFile1.yml";
    private final String secondTestYamlFilePath = "src/test/java/resources/TestYamlFile2.yml";

    @BeforeAll
    public static void readFixtures() throws IOException {
        differsStylish = readReroptAsString(Path.of(STYLISH_REPORT_PATH));
        differsPlain = readReroptAsString(Path.of(PLAIN_REPORT_PATH));
        differsJson = readReroptAsString(Path.of(JSON_REPORT_PATH));

    }


    @Test
    public void testGenerateStylish() throws IOException {
        var format = "stylish";

        var differenceJson = Differ.generate(firstTestJsonFilePath, secondTestJsonFilePath, format);
        assertEquals(differsStylish, differenceJson);

        String differenceYaml = Differ.generate(firstTestYamlFilePath, secondTestYamlFilePath, format);
        assertEquals(differenceYaml, differsStylish);
    }

    @Test
    public void testGeneratePlain() throws IOException {
        var format = "plain";
        var differenceJson = Differ.generate(firstTestJsonFilePath, secondTestJsonFilePath, format);
        assertEquals(differsPlain, differenceJson, format);

        String differenceYaml = Differ.generate(firstTestYamlFilePath, secondTestYamlFilePath, format);
        assertEquals(differenceYaml, differsPlain);
    }

    @Test
    public void testGenerateJson() throws IOException {
        var format = "json";
        var differenceJson = Differ.generate(firstTestJsonFilePath, secondTestJsonFilePath, format);
        assertEquals(differsJson, differenceJson, format);

        String difference = Differ.generate(firstTestYamlFilePath, secondTestYamlFilePath, format);
        assertEquals(difference, differsJson);
    }

    @Test
    public void testWrongPathFile() {
        var format = "stylish";
        var thrownFirstArg = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate(wrongFile, firstTestJsonFilePath, format);
        });
        assertEquals("Файл для чтения не найден", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate(firstTestJsonFilePath, wrongFile, format);
        });
        assertEquals("Файл для чтения не найден", thrownSecondArg.getMessage());
    }

    @Test
    public void testGenerateWithSomeFile() throws IOException {
        String differs = readReroptAsString(Path.of("src/test/java/fixtures/Stylish_same_file"));
        var formant = "stylish";
        String differenceJson = Differ.generate(firstTestJsonFilePath, firstTestJsonFilePath, formant);
        assertEquals(differenceJson, differs);

        String differenceYaml = Differ.generate(firstTestYamlFilePath, firstTestYamlFilePath, formant);
        assertEquals(differenceYaml, differs);
    }

    @Test
    public void testWrongFormat() {
        var format = "wrong format";
        var thrownFirstArg = assertThrows(IllegalArgumentException.class, () -> {
            Differ.generate(firstTestJsonFilePath, secondTestJsonFilePath, format);
        });
        assertEquals("Не найден формат", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(IllegalArgumentException.class, () -> {
            Differ.generate(firstTestYamlFilePath, secondTestYamlFilePath, format);
        });
        assertEquals("Не найден формат", thrownSecondArg.getMessage());
    }


    public static String readReroptAsString(Path path) throws IOException {
        var normalizePath = path.normalize().toAbsolutePath();
        return Files.readString(normalizePath);
    }
}
