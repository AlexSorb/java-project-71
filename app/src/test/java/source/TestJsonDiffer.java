package source;

import hexlet.code.source.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestJsonDiffer {

    String differsJson = "{\n"
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

    String differsPlain = "Property 'chars2' was updated. From [complex value] to false\n"
            + "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [complex value]\n"
            + "Property 'id' was updated. From 45 to null\n"
            + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: 'value2'\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was removed\n"
            + "Property 'numbers4' was added with value: [complex value]\n"
            + "Property 'obj1' was added with value: [complex value]\n"
            + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to 'none'";

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
        assertEquals(differsJson, result);
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

}
