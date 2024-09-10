package source;

import hexlet.code.source.Differ;
import hexlet.code.source.JsonDiffer;
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

     private final Path firstTestFilePath = Path.of("src/test/java/resources/TestYamlFile1.yaml").toAbsolutePath();
     private final Path secondTestFilePath = Path.of("src/test/java/resources/TestYamlFile2.yaml").toAbsolutePath();
     private final Path wrongTestFilePath = Paths.get("src/test/resources/WrongYamlFile.yaml").toAbsolutePath();

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

        String difference = JsonDiffer.generateYaml(firstTestFilePath, secondTestFilePath);
        assertEquals(difference, differs);
    }

    @Test
    public void testNotExistFile() {

        var thrownFirstArg = assertThrows(FileNotFoundException.class, () -> {
            JsonDiffer.generateYaml(wrongTestFilePath, secondTestFilePath);
        });
        assertEquals("Файл для чтения не найден", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(FileNotFoundException.class, () -> {
            JsonDiffer.generateYaml(firstTestFilePath, wrongTestFilePath);
        });
        assertEquals("Файл для чтения не найден", thrownSecondArg.getMessage());
    }

    // Тест на Null
    @Test
    public void testNull() {

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

        String difference = JsonDiffer.generateYaml(firstTestFilePath, firstTestFilePath);
        assertEquals(difference, differs);
    }

    // Тест в сравнении с пустым файлом
    @Test
    public void testWithEmptyFile() {

    }
}
