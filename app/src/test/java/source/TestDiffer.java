package source;

import hexlet.code.source.Differ;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class TestDiffer {

    @Test
    public void TestGenerate() {
        Path file1 = Path.of("/home/alex/java-project-71/app/src/test/java/resources/File1.json");
        Path file2 = Path.of("/home/alex/java-project-71/app/src/test/java/resources/File2.json");
        String result = "";
        try {
            result = Differ.generate(file1, file2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result);
    }
}
