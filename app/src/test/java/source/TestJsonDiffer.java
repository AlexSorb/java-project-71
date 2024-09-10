package source;

import hexlet.code.source.JsonDiffer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class TestJsonDiffer {

    String differs = "{\n"
            + "- follow: false\n"
            + "  host: hexlet.io\n"
            + "- proxy: 123.234.53.22\n"
            + "- timeout: 50\n"
            + "+ timeout: 20\n"
            + "+ verbose: true\n"
            + "}";

    @Test
    public void testGenerate() {
        Path file1 = Path.of("src/test/java/resources/File1.json").toAbsolutePath();
        Path file2 = Path.of("src/test/java/resources/File2.json").toAbsolutePath();
        String result = "";
        try {
            result = JsonDiffer.generate(file1, file2);
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
            JsonDiffer.generate(wrongFile, file1);
        });
        assertEquals("Файл для чтения не найден", thrownFirstArg.getMessage());

        var thrownSecondArg = assertThrows(FileNotFoundException.class, () -> {
            JsonDiffer.generate(file1, wrongFile);
        });
        assertEquals("Файл для чтения не найден", thrownSecondArg.getMessage());
    }

    // Тесты стравнения yaml

    // Тестирование сравнения

    // Тестирование ошибки несуществования одного из фалов

}
