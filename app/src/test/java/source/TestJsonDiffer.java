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


    private static String differsStylish;
    private static String differsPlain;
    private static String differsJson;

    private final String file1 = "src/test/java/resources/File1.json";
    private final String file2 = "src/test/java/resources/File2.json";
    private final String wrongFile = "src/test/java/wrongFIle.json";

    private final String firstTestFilePath = "src/test/java/resources/TestYamlFile1.yml";
    private final String secondTestFilePath = "src/test/java/resources/TestYamlFile2.yml";
    private final String wrongTestFilePath = "src/test/resources/WrongYamlFile.yml";

    @BeforeAll
    public static void readFixtures() throws IOException {
        differsStylish = readReroptAsString(Path.of(STYLISH_REPORT_PATH));
        differsPlain = readReroptAsString(Path.of(PLAIN_REPORT_PATH));
        differsJson = readReroptAsString(Path.of(JSON_REPORT_PATH));

    }


    @Test
    public void testGenerateStylish() throws IOException {
        var format = "stylish";

        var differenceJson = Differ.generate(file1, file2, format);
        assertEquals(differsStylish, differenceJson);

        String differenceYaml = Differ.generate(firstTestFilePath, secondTestFilePath, format);
        assertEquals(differenceYaml, differsStylish);
    }

    @Test
    public void testGeneratePlain() throws IOException {
        var format = "plain";
        var differenceJson = Differ.generate(file1, file2, format);
        assertEquals(differsPlain, differenceJson, format);

        String differenceYaml = Differ.generate(firstTestFilePath, secondTestFilePath, format);
        assertEquals(differenceYaml, differsPlain);
    }

    @Test
    public void testGenerateJson() throws IOException {
        var format = "json";
        var differenceJson = Differ.generate(file1, file2, format);
        assertEquals(differsJson, differenceJson, format);

        String difference = Differ.generate(firstTestFilePath, secondTestFilePath, format);
        assertEquals(difference, differsJson);
    }

    @Test
    public void testWrongPathFile() {
        var format = "stylish";
        var thrownFirstArg = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate(wrongFile, file1, format);
        });
        assertEquals("Файл для чтения не найден", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate(file1, wrongFile, format);
        });
        assertEquals("Файл для чтения не найден", thrownSecondArg.getMessage());
    }

    @Test
    public void testGenerateWithSomeFile() throws IOException {
        String differs = readReroptAsString(Path.of("src/test/java/fixtures/Stylish_same_file"));
        var formant = "stylish";
        String differenceJson = Differ.generate(file1, file1, formant);
        assertEquals(differenceJson, differs);

        String differenceYaml= Differ.generate(firstTestFilePath, firstTestFilePath, formant);
        assertEquals(differenceYaml, differs);
    }


    public static String readReroptAsString(Path path) throws IOException {
        var normalizePath = path.normalize().toAbsolutePath();
        return Files.readString(normalizePath);
    }
}
