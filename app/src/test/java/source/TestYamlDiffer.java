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

    private static Differ differ;

    @BeforeAll
    public static void genDiffer() {
        differ = new YamlDiffer();
    }

    @Test
    public void testGenerate() throws IOException {
        String differs = "{\n"
                + "  chars1: [a, b, c]\n"
                + "- chars2: [d, e, f]\n"
                + "+ chars2: false\n"
                + "- checked: false\n"
                + "+ checked: true\n"
                + "- default: null\n"
                + "+ default: [value1, value2]\n"
                + "- id: 45\n"
                + "+ id: null\n"
                + "- key1: value1\n"
                + "+ key2: value2\n"
                + "  numbers1: [1, 2, 3, 4]\n"
                + "- numbers2: [2, 3, 4, 5]\n"
                + "+ numbers2: [22, 33, 44, 55]\n"
                + "- numbers3: [3, 4, 5]\n"
                + "+ numbers4: [4, 5, 6]\n"
                + "+ obj1: {nestedKey=value, isNested=true}\n"
                + "- setting1: Some value\n"
                + "+ setting1: Another value\n"
                + "- setting2: 200\n"
                + "+ setting2: 300\n"
                + "- setting3: true\n"
                + "+ setting3: none\n"
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
                + "  chars1: [a, b, c]\n"
                + "  chars2: [d, e, f]\n"
                + "  checked: false\n"
                + "  default: null\n"
                + "  id: 45\n"
                + "  key1: value1\n"
                + "  numbers1: [1, 2, 3, 4]\n"
                + "  numbers2: [2, 3, 4, 5]\n"
                + "  numbers3: [3, 4, 5]\n"
                + "  setting1: Some value\n"
                + "  setting2: 200\n"
                + "  setting3: true\n"
                + "}";

        String difference = differ.generate(firstTestFilePath, firstTestFilePath);
        assertEquals(difference, differs);
    }
}
