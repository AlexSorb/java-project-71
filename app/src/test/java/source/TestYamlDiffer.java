package source;

import hexlet.code.source.Differ;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestYamlDiffer {
    private final String firstTestFilePath = "src/test/java/resources/TestYamlFile1.yml";
    private final String secondTestFilePath = "src/test/java/resources/TestYamlFile2.yml";
    private final String wrongTestFilePath = "src/test/resources/WrongYamlFile.yml";

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

        String difference = Differ.generate(firstTestFilePath, secondTestFilePath);
        assertEquals(difference, differs);
    }

    @Test
    public void testNotExistFile() {

        var thrownFirstArg = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate(wrongTestFilePath, secondTestFilePath);
        });
        assertEquals("Файл для чтения не найден", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(FileNotFoundException.class, () -> {
            Differ.generate(firstTestFilePath, wrongTestFilePath);
        });
        assertEquals("Файл для чтения не найден", thrownSecondArg.getMessage());
    }

//    // Тест на Null
//    @Test
//    public void testNull() {
//        var thrownFirstArg = assertThrows(IllegalArgumentException.class, () -> {
//            Differ.generate(null, secondTestFilePath);
//        });
//        assertEquals("The file path cannot be empty!", thrownFirstArg.getMessage());
//
//        var thrownSecondArg = assertThrows(IllegalArgumentException.class, () -> {
//            Differ.generate(firstTestFilePath, null);
//        });
//        assertEquals("The file path cannot be empty!", thrownSecondArg.getMessage());
//
//    }

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

        String difference = Differ.generate(firstTestFilePath, firstTestFilePath);
        assertEquals(difference, differs);
    }
}
