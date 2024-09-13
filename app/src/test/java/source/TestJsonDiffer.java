package source;

import hexlet.code.source.Differ;
import hexlet.code.source.JsonDiffer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class TestJsonDiffer {

    String differs = "{\n"
            + " chars1: [a, b, c]"
            + "- chars2: [d, e, f]"
            + "+ chars2: false"
            + "- checked: false"
            + "+ checked: true"
            + "- default: null"
            + "+ default: [value1, value2]"
            + "- id: 45"
            + "+ id: null"
            + "- key1: value1"
            + "+ key2: value2"
            + "numbers1: [1, 2, 3, 4]"
            + "- numbers2: [2, 3, 4, 5]"
            + "+ numbers2: [22, 33, 44, 55]"
            + "- numbers3: [3, 4, 5]"
            + "+ numbers4: [4, 5, 6]"
            + "+ obj1: {nestedKey=value, isNested=true}"
            + "- setting1: Some value"
            + "+ setting1: Another value"
            + "- setting2: 200"
            + "+ setting2: 300"
            + "- setting3: true"
            + "+ setting3: none"
            + "}";


    private static Differ differ;

    @BeforeAll
    public static void genDiffer() {
        differ = new JsonDiffer();
    }


    @Test
    public void testGenerate() {
        Path file1 = Path.of("src/test/java/resources/File1.json").toAbsolutePath();
        Path file2 = Path.of("src/test/java/resources/File2.json").toAbsolutePath();
        String result = "";
        try {
            result = differ.generate(file1, file2);
        } catch (IOException e) {
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
            differ.generate(wrongFile, file1);
        });
        assertEquals("Файл для чтения не найден", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(FileNotFoundException.class, () -> {
            differ.generate(file1, wrongFile);
        });
        assertEquals("Файл для чтения не найден", thrownSecondArg.getMessage());
    }

}
