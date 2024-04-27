import hexlet.code.source.FileManager;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenFileTest {

    @Test
    public void testOpenFile() {
        String relativePath = "./File1";
        String absolutePath = "/home/alex/java-project-71/app/File1";
        var currentPath = Paths.get(absolutePath);
        assertEquals(FileManager.normaolizePath(relativePath).toString(), currentPath.toString());
    }
}
