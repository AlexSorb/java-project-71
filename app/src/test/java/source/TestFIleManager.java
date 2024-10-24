package source;

import hexlet.code.FileManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Paths;

public class TestFIleManager {

    @Test
    public void testNormalizePath() {
        var notNormalizePath = Paths.get("./src/test/java/resources");
        var normalizeAbsolutePath = Paths.get("src/test/java/resources").toAbsolutePath();
        assertEquals(FileManager.normalizePath(notNormalizePath), normalizeAbsolutePath);
    }

    @Test
    public void testNull() {
        var thrownFirstArg = assertThrows(IllegalArgumentException.class, () -> {
            FileManager.normalizePath(null);
        });
        assertEquals("The file path cannot be empty!", thrownFirstArg.getMessage());
    }

}
