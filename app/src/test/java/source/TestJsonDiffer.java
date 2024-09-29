package source;

import hexlet.code.source.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestJsonDiffer {

    String differsStylish = "{\n"
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

    String differsJson = "[{\"key\":\"chars1\",\"oldValue\":[\"a\",\"b\",\"c\"],\"newValue\":[\"a\",\"b\",\"c\"],"
            + "\"state\":\"UNC\"},"
            + "{\"key\":\"chars2\",\"oldValue\":[\"d\",\"e\",\"f\"],\"newValue\":false,\"state\":\"CHN\"},"
            + "{\"key\":\"checked\",\"oldValue\":false,\"newValue\":true,\"state\":\"CHN\"},"
            + "{\"key\":\"default\",\"oldValue\":\"null\",\"newValue\":[\"value1\",\"value2\"],\"state\":\"CHN\"},"
            + "{\"key\":\"id\",\"oldValue\":45,\"newValue\":\"null\",\"state\":\"CHN\"},"
            + "{\"key\":\"key1\",\"oldValue\":\"value1\",\"newValue\":null,\"state\":\"DEL\"},"
            + "{\"key\":\"key2\",\"oldValue\":null,\"newValue\":\"value2\",\"state\":\"ADD\"},"
            + "{\"key\":\"numbers1\",\"oldValue\":[1,2,3,4],\"newValue\":[1,2,3,4],\"state\":\"UNC\"},"
            + "{\"key\":\"numbers2\",\"oldValue\":[2,3,4,5],\"newValue\":[22,33,44,55],\"state\":\"CHN\"},"
            + "{\"key\":\"numbers3\",\"oldValue\":[3,4,5],\"newValue\":null,\"state\":\"DEL\"},"
            + "{\"key\":\"numbers4\",\"oldValue\":null,\"newValue\":[4,5,6],\"state\":\"ADD\"},"
            + "{\"key\":\"obj1\",\"oldValue\":null,\"newValue\":{\"nestedKey\":\"value\",\"isNested\":true},"
            + "\"state\":\"ADD\"},"
            + "{\"key\":\"setting1\",\"oldValue\":\"Some value\",\"newValue\":\"Another value\",\"state\":\"CHN\"},"
            + "{\"key\":\"setting2\",\"oldValue\":200,\"newValue\":300,\"state\":\"CHN\"},"
            + "{\"key\":\"setting3\",\"oldValue\":true,\"newValue\":\"none\",\"state\":\"CHN\"}]";

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

}
