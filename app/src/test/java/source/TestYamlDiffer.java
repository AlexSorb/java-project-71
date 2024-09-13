package source;

import hexlet.code.source.Differ;
import hexlet.code.source.YamlDiffer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestYamlDiffer {
    private final Path firstTestFilePath = Path.of("src/test/java/resources/TestYamlFile1.yml").toAbsolutePath();
    private final Path secondTestFilePath = Path.of("src/test/java/resources/TestYamlFile2.yml").toAbsolutePath();
    private final Path wrongTestFilePath = Paths.get("src/test/resources/WrongYamlFile.yml").toAbsolutePath();
     //private final Path emptyTestFilePath = Paths.get("src/test/java/resources/EmptyTestFile.yaml").toAbsolutePath();

    private static Differ differ;

    @BeforeAll
    public static void genDiffer() {
        differ = new YamlDiffer();
    }

    @Test
    public void testGenerate() throws IOException {
        String differs = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";

        String difference = differ.generate(firstTestFilePath, secondTestFilePath);
        assertEquals(difference, differs);
    }

    @Test
    public void testNotExistFile() {

        var thrownFirstArg = assertThrows(FileNotFoundException.class, () -> {
            differ.generate(wrongTestFilePath, secondTestFilePath);
        });
        assertEquals("Файл для чтения не найден", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(FileNotFoundException.class, () -> {
            differ.generate(firstTestFilePath, wrongTestFilePath);
        });
        assertEquals("Файл для чтения не найден", thrownSecondArg.getMessage());
    }

    // Тест на Null
    @Test
    public void testNull() {
        var thrownFirstArg = assertThrows(IllegalArgumentException.class, () -> {
            differ.generate(null, secondTestFilePath);
        });
        assertEquals("The file path cannot be empty!", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(IllegalArgumentException.class, () -> {
            differ.generate(firstTestFilePath, null);
        });
        assertEquals("The file path cannot be empty!", thrownSecondArg.getMessage());

    }

    // Тест на одинаковый файл
    @Test
    public void testGenerateWithSomeFile() throws IOException {
        String differs = "{\n"
                + "  follow: false\n"
                + "  host: hexlet.io\n"
                + "  proxy: 123.234.53.22\n"
                + "  timeout: 50\n"
                + "}";

        String difference = differ.generate(firstTestFilePath, firstTestFilePath);
        assertEquals(difference, differs);
    }

    // Тест в сравнении с пустым файлом
//    @Test
//    public void testWithEmptyFile() throws IOException {
//        String differs = "{\n"
//                + "+ follow: false\n"
//                + "+ host: hexlet.io\n"
//                + "+ proxy: 123.234.53.22\n"
//                + "+ timeout: 50\n"
//                + "}";
//
//        var difference = differ.generate(emptyTestFilePath, secondTestFilePath);
//
//        assertEquals(difference, differs);
//    }
}
